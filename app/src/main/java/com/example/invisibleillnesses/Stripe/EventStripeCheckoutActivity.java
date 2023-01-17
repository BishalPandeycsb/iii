package com.example.invisibleillnesses.Stripe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.invisibleillnesses.MainActivity;
import com.example.invisibleillnesses.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EventStripeCheckoutActivity extends AppCompatActivity {

    Button button;
    String SECRET_KEY = "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";
    String PUBLISH_KEY = "pk_live_UOSEkqzd2Qx8gELpukD5wYGc00PYT6ElJl";
    PaymentSheet paymentSheet;
    String customerId;
    String ephericalKey;
    String ClientSecret;
    ImageView imageView;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;

    Map<String, Object> productInfo;

    String id, firstName, lastName, streetName, apartmentStatus, suburbValue, postcode, phoneValue, emailAddress, eventViewId, eventViewName, eventViewPrice, eventViewLocation, eventViewDes, eventViewDate, eventViewImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stripe_checkout);

        PaymentConfiguration.init(this, PUBLISH_KEY);
        button = findViewById(R.id.product_pay_button);
        button.setEnabled(false);
        paymentSheet = new PaymentSheet(this, paymentSheetResult -> {
            onPaymentResult(paymentSheetResult);
        });

        fStore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        imageView = findViewById(R.id.product_image);
        Picasso.get().load("https://us.123rf.com/450wm/rawpixel/rawpixel1904/rawpixel190401610/120560752-corporate-businessman-giving-a-presentation-to-a-large-audience.jpg?ver=6").into(imageView);

        id = getIntent().getStringExtra("id");
        firstName = getIntent().getStringExtra("first_name");
        lastName = getIntent().getStringExtra("last_name");
        streetName = getIntent().getStringExtra("street_name");
        apartmentStatus = getIntent().getStringExtra("apartment_status");
        suburbValue = getIntent().getStringExtra("suburb_value");
        postcode = getIntent().getStringExtra("post_code");
        phoneValue = getIntent().getStringExtra("phoneValue");
        emailAddress = getIntent().getStringExtra("email_address");

        eventViewId = getIntent().getStringExtra("event_id");
        eventViewName = getIntent().getStringExtra("event_name");
        eventViewPrice = getIntent().getStringExtra("event_price");
        eventViewLocation = getIntent().getStringExtra("event_location");
        eventViewDate = getIntent().getStringExtra("event_date");
        eventViewDes = getIntent().getStringExtra("event_des");
        eventViewImg = getIntent().getStringExtra("event_photo");

        productInfo = new HashMap<>();
        productInfo.put("id", id);
        productInfo.put("first_name", firstName);
        productInfo.put("last_name", lastName);
        productInfo.put("street_name", streetName);
        productInfo.put("apartment_status", apartmentStatus);
        productInfo.put("suburb_value", suburbValue);
        productInfo.put("post_code", postcode);
        productInfo.put("phone_value", phoneValue);
        productInfo.put("email_address", emailAddress);
        productInfo.put("event_id", eventViewId);
        productInfo.put("event_name", eventViewName);
        productInfo.put("event_price", eventViewPrice);
        productInfo.put("event_location", eventViewLocation);
        productInfo.put("event_date", eventViewDate);
        productInfo.put("event_des", eventViewDes);
        productInfo.put("event_img", eventViewImg);

        Log.i("payment_event",eventViewPrice);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentFlow();
            }
        });


        StringRequest request = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/customers", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    customerId = jsonObject.getString("id");
                    getImphericalId(customerId);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EventStripeCheckoutActivity.this, "Test Error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + SECRET_KEY);
                return header;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(EventStripeCheckoutActivity.this);
        requestQueue.add(request);

    }

    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {
        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            progressDialog.show();
            fStore.collection("event_book").document(id)
                    .set(productInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            Intent i = new Intent(EventStripeCheckoutActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                            Toast.makeText(getApplicationContext(), "Payment Complete", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                        }
                    });
        } else if (paymentSheetResult instanceof PaymentSheetResult.Canceled) {

            Toast.makeText(this, "Payment canceled", Toast.LENGTH_SHORT).show();
        } else if (paymentSheetResult instanceof PaymentSheetResult.Failed) {
            Throwable error = ((PaymentSheetResult.Failed) paymentSheetResult).getError();
            Toast.makeText(this, "Payment failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void getImphericalId(String customerIdValue) {
        StringRequest emprequest = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/ephemeral_keys", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    ephericalKey = jsonObject.getString("id");
                    getClientSecretKeyId(customerId, ephericalKey);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EventStripeCheckoutActivity.this, "Test Error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + SECRET_KEY);
                header.put("Stripe-Version", "2020-08-27");
                return header;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> paramsData = new HashMap<>();
                paramsData.put("customer", customerIdValue);
                return paramsData;
            }
        };

        RequestQueue requestQueueEmp = Volley.newRequestQueue(EventStripeCheckoutActivity.this);
        requestQueueEmp.add(emprequest);
    }

    public void getClientSecretKeyId(String clientIdValue, String emphericalIdValue) {
        StringRequest emprequest = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/payment_intents", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    ClientSecret = jsonObject.getString("client_secret");
                    button.setEnabled(true);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EventStripeCheckoutActivity.this, "Test Error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + SECRET_KEY);
                return header;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> paramsData = new HashMap<>();
                paramsData.put("customer", clientIdValue);
                paramsData.put("amount", eventViewPrice + "00");
                paramsData.put("currency", "usd");
                paramsData.put("automatic_payment_methods[enabled]", "true");

                return paramsData;
            }
        };

        RequestQueue requestQueueEmp = Volley.newRequestQueue(EventStripeCheckoutActivity.this);
        requestQueueEmp.add(emprequest);
    }

    public void PaymentFlow() {
        paymentSheet.presentWithPaymentIntent(ClientSecret, new PaymentSheet.Configuration("AbcCompany", new PaymentSheet.CustomerConfiguration(customerId, ephericalKey)));
    }


}