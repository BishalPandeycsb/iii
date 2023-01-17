package com.example.invisibleillnesses.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.invisibleillnesses.Model.CartModel;
import com.example.invisibleillnesses.R;
import com.example.invisibleillnesses.Stripe.EventStripeCheckoutActivity;
import com.example.invisibleillnesses.Stripe.StripeCheckoutActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.UUID;

public class EventFormActivity extends AppCompatActivity {

    private AwesomeValidation awesomeValidation;
    EditText first_name, last_name, street_name, apartment_suite, suburb, state, post_code, phone, email_address;
    Button orderSubmitBtn;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;
    Toolbar toolbar;
    String totalPrice;
    String eventViewId, eventViewName, eventViewPrice, eventViewLocation, eventViewDes, eventViewDate, eventViewImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Address Form");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        first_name = findViewById(R.id.product_buying_first_name);
        last_name = findViewById(R.id.product_buying_last_name);
        street_name = findViewById(R.id.product_buying_address);
        apartment_suite = findViewById(R.id.product_buying_apartment_suite);
        suburb = findViewById(R.id.product_buying_suburb);
        state = findViewById(R.id.product_buying_state);
        post_code = findViewById(R.id.product_buying_post_code);
        phone = findViewById(R.id.product_buying_phone);
        email_address = findViewById(R.id.product_buying_email);
        orderSubmitBtn = findViewById(R.id.order_submit);
        fStore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");


        eventViewId = getIntent().getStringExtra("id");
        eventViewName = getIntent().getStringExtra("name");
        eventViewPrice = getIntent().getStringExtra("price");
        eventViewLocation = getIntent().getStringExtra("location");
        eventViewDate = getIntent().getStringExtra("date");
        eventViewDes = getIntent().getStringExtra("des");
        eventViewImg = getIntent().getStringExtra("photo");


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.product_buying_first_name, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_last_name, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_address, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_apartment_suite, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_suburb, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_state, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_post_code, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_phone, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.product_buying_email, RegexTemplate.NOT_EMPTY, R.string.valid_required);


        orderSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    orderFormSubmit();
                }

            }
        });
    }

    public void orderFormSubmit() {
        try {

            String firstName = first_name.getText().toString();
            String lastName = last_name.getText().toString();
            String streetName = street_name.getText().toString();
            String apartmentStatus = apartment_suite.getText().toString();
            String suburbValue = suburb.getText().toString();
            String postcode = post_code.getText().toString();
            String phoneValue = phone.getText().toString();
            String emailAddress = email_address.getText().toString();
            String id = UUID.randomUUID().toString();

            Intent i = new Intent(EventFormActivity.this, EventStripeCheckoutActivity.class);
            i.putExtra("id", id);
            i.putExtra("first_name", firstName);
            i.putExtra("last_name", lastName);
            i.putExtra("street_name", streetName);
            i.putExtra("apartment_status", apartmentStatus);
            i.putExtra("suburb_value", suburbValue);
            i.putExtra("post_code", postcode);
            i.putExtra("phone_value", phoneValue);
            i.putExtra("email_address", emailAddress);
            i.putExtra("event_id", eventViewId);
            i.putExtra("event_name", eventViewName);
            i.putExtra("event_price", eventViewPrice);
            i.putExtra("event_location", eventViewLocation);
            i.putExtra("event_date", eventViewDate);
            i.putExtra("event_des", eventViewDes);
            i.putExtra("event_photo", eventViewImg);

            startActivity(i);


        } catch (Exception e) {

        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}