package com.example.invisibleillnesses.Stripe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.example.invisibleillnesses.Model.CartModel;
import com.example.invisibleillnesses.Pages.ProductDetailActivity;
import com.example.invisibleillnesses.Pages.ProductFormActivity;
import com.example.invisibleillnesses.Pages.StripePaymentActivity;
import com.example.invisibleillnesses.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.okhttp.ResponseBody;
import com.squareup.picasso.Picasso;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StripeCheckoutActivity extends AppCompatActivity {

    private static final String TAG = "CheckoutActivity";

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
    ArrayList<CartModel> cartModels;

    String id, firstName, lastName, streetName, apartmentStatus, suburbValue, postcode, phoneValue, emailAddress, donationPrice;


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
        Picasso.get().load("https://www.seedprod.com/wp-content/uploads/2020/01/how-to-create-an-online-order-form.jpg").into(imageView);
        id = UUID.randomUUID().toString();
        firstName = getIntent().getStringExtra("first_name");
        lastName = getIntent().getStringExtra("last_name");
        streetName = getIntent().getStringExtra("street_name");
        apartmentStatus = getIntent().getStringExtra("apartment_status");
        suburbValue = getIntent().getStringExtra("suburb_value");
        postcode = getIntent().getStringExtra("post_code");
        phoneValue = getIntent().getStringExtra("phone_value");
        emailAddress = getIntent().getStringExtra("email_address");
        donationPrice = getIntent().getStringExtra("total_price");
        cartModels = getIntent().getParcelableArrayListExtra("cart");

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
        productInfo.put("total_price", donationPrice);
        productInfo.put("cart", cartModels);


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
                Toast.makeText(StripeCheckoutActivity.this, "Test Error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + SECRET_KEY);
                return header;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(StripeCheckoutActivity.this);
        requestQueue.add(request);

    }

    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {
        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            progressDialog.show();
            fStore.collection("product_order").document(id)
                    .set(productInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            progressDialog.dismiss();
                            Intent i = new Intent(StripeCheckoutActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                            deleteIdValue();
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
                Toast.makeText(StripeCheckoutActivity.this, "Test Error", Toast.LENGTH_SHORT).show();

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

        RequestQueue requestQueueEmp = Volley.newRequestQueue(StripeCheckoutActivity.this);
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
                Toast.makeText(StripeCheckoutActivity.this, "Test Error", Toast.LENGTH_SHORT).show();

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
                paramsData.put("amount", donationPrice + "00");
                paramsData.put("currency", "usd");
                paramsData.put("automatic_payment_methods[enabled]", "true");

                return paramsData;
            }
        };

        RequestQueue requestQueueEmp = Volley.newRequestQueue(StripeCheckoutActivity.this);
        requestQueueEmp.add(emprequest);
    }

    public void PaymentFlow() {
        paymentSheet.presentWithPaymentIntent(ClientSecret, new PaymentSheet.Configuration("AbcCompany", new PaymentSheet.CustomerConfiguration(customerId, ephericalKey)));
    }

    public void deleteIdValue() {
        if (cartModels.size() > 0) {
            for (int iValue = 0; iValue < cartModels.size(); iValue++) {

                String uuid = UUID.randomUUID().toString();
                Map<String, Object> helpingInfo = new HashMap<>();
                helpingInfo.put("id", uuid);
                helpingInfo.put("product_id", cartModels.get(iValue).getProduct_id());
                helpingInfo.put("product_name", cartModels.get(iValue).getProduct_name());
                helpingInfo.put("product_price", cartModels.get(iValue).getProduct_price());
                helpingInfo.put("product_designer", cartModels.get(iValue).getProduct_designer());
                helpingInfo.put("product_view_size", cartModels.get(iValue).getProduct_view_size());
                helpingInfo.put("product_view_refund", cartModels.get(iValue).getProduct_view_refund());
                helpingInfo.put("product_weekend", cartModels.get(iValue).getProduct_weekend());
                helpingInfo.put("product_view_short_des", cartModels.get(iValue).getProduct_view_short_des());
                helpingInfo.put("product_view_des", cartModels.get(iValue).getProduct_view_des());
                helpingInfo.put("product_view_image", cartModels.get(iValue).getProduct_view_image());
                helpingInfo.put("user_id", cartModels.get(iValue).getUser_id());
                helpingInfo.put("user_email", cartModels.get(iValue).getUser_email());
                helpingInfo.put("product_choose_date", cartModels.get(iValue).getProduct_choose_date());
                helpingInfo.put("product_order_id", id);

                fStore.collection("product_order_items").document(uuid)
                        .set(helpingInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });

                fStore.collection("product_book").document(cartModels.get(iValue).getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }


        }
    }
}