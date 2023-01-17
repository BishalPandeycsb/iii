package com.example.invisibleillnesses.Pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.invisibleillnesses.Form.HelpingHandActivity;
import com.example.invisibleillnesses.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductDetailActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView title, des, shortDes, refund, size, designer, weekend;
    String productViewId, productViewName, productViewPrice, productViewDesigner, productViewSize, productViewRefundable, productViewWeekend, productViewShortDes, productViewDes, productViewImg;
    ImageView imageView;
    Button addToCart;
    RadioGroup radioGroup;
    public static final String MyPREFERENCES = "InvisibleIllinessPrefs";
    String userId, userEmail;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        productViewName = getIntent().getStringExtra("name");
        toolbar.setTitle(productViewName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        title = findViewById(R.id.product_detail_title);
        des = findViewById(R.id.product_detail_des);
        shortDes = findViewById(R.id.product_detail_short_des);
        refund = findViewById(R.id.product_detail_refund);
        size = findViewById(R.id.product_detail_size);
        designer = findViewById(R.id.product_detail_designer);
        weekend = findViewById(R.id.product_detail_weekend);
        imageView = findViewById(R.id.product_detail_image);
        radioGroup = findViewById(R.id.product_detail_date);
        addToCart = findViewById(R.id.add_to_cart);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userid", null);
        userEmail = sharedPreferences.getString("email", null);

        productViewId = getIntent().getStringExtra("id");
        productViewPrice = getIntent().getStringExtra("price");
        productViewDesigner = getIntent().getStringExtra("designer");
        productViewSize = getIntent().getStringExtra("size");
        productViewRefundable = getIntent().getStringExtra("refundable");
        productViewWeekend = getIntent().getStringExtra("weekend");
        productViewShortDes = getIntent().getStringExtra("shortDes");
        productViewDes = getIntent().getStringExtra("des");
        productViewImg = getIntent().getStringExtra("photo");

        fStore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        title.setText(productViewName);
        designer.setText(productViewDesigner);
        size.setText(productViewSize);
        refund.setText(productViewRefundable);
        weekend.setText(productViewWeekend);
        shortDes.setText(productViewShortDes);
        des.setText(productViewDes);
        Picasso.get().load(productViewImg).into(imageView);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton
                        radioButton
                        = (RadioButton) radioGroup.findViewById(i);
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCartPage();
            }
        });

    }

    public void addToCartPage() {
        int radioSelectedId = radioGroup.getCheckedRadioButtonId();
        if (radioSelectedId > 0) {


            RadioButton dateButton = (RadioButton) radioGroup.findViewById(radioSelectedId);


            String id = UUID.randomUUID().toString();
            Map<String, Object> helpingInfo = new HashMap<>();
            helpingInfo.put("id", id);
            helpingInfo.put("product_id", productViewId);
            helpingInfo.put("product_name", productViewName);
            helpingInfo.put("product_price", productViewPrice);
            helpingInfo.put("product_designer", productViewDesigner);
            helpingInfo.put("product_view_size", productViewSize);
            helpingInfo.put("product_view_refund", productViewRefundable);
            helpingInfo.put("product_weekend", productViewWeekend);
            helpingInfo.put("product_view_short_des", productViewShortDes);
            helpingInfo.put("product_view_des", productViewDes);
            helpingInfo.put("product_view_image", productViewImg);
            helpingInfo.put("user_id", userId);
            helpingInfo.put("user_email", userEmail);
            helpingInfo.put("product_choose_date", dateButton.getText());


            fStore.collection("product_book").document(id)
                    .set(helpingInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            finish();
                            Toast.makeText(ProductDetailActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ProductDetailActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Please Select day", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}