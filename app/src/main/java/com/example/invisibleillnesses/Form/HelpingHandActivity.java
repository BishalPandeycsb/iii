package com.example.invisibleillnesses.Form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.invisibleillnesses.Admin.EditEventActivity;
import com.example.invisibleillnesses.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HelpingHandActivity extends AppCompatActivity {

    EditText firstName, lastName, address, email, phone_number, membershipNumber, typeOfBenefit, untitled;
    RadioGroup vaccinated, financial, employed, centerLink, assistance;
    Toolbar toolbar;
    Button submit;
    private AwesomeValidation awesomeValidation;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "InvisibleIllinessPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helping_hand);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Helping Hand");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        fStore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        firstName = findViewById(R.id.helping_first_name);
        lastName = findViewById(R.id.helping_last_name);
        address = findViewById(R.id.helping_address);
        email = findViewById(R.id.helping_email);
        phone_number = findViewById(R.id.helping_phone_no);
        membershipNumber = findViewById(R.id.helping_membership_number);
        typeOfBenefit = findViewById(R.id.helping_benefit);
        untitled = findViewById(R.id.helping_untitled);


        vaccinated = findViewById(R.id.helping_vaccinated);
        financial = findViewById(R.id.helping_financial);
        employed = findViewById(R.id.helping_employed);
        centerLink = findViewById(R.id.helping_centre_link);
        assistance = findViewById(R.id.helping_assistance);

        submit = findViewById(R.id.helping_submit);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.helping_first_name, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.helping_last_name, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.helping_address, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.helping_email, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.helping_phone_no, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.helping_membership_number, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.helping_benefit, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.helping_untitled, RegexTemplate.NOT_EMPTY, R.string.valid_required);


        vaccinated.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton
                        radioButton
                        = (RadioButton) radioGroup.findViewById(i);
            }
        });

        financial.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton
                        radioButton
                        = (RadioButton) radioGroup.findViewById(i);
            }
        });

        employed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton
                        radioButton
                        = (RadioButton) radioGroup.findViewById(i);
            }
        });

        centerLink.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton
                        radioButton
                        = (RadioButton) radioGroup.findViewById(i);
            }
        });

        assistance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton
                        radioButton
                        = (RadioButton) radioGroup.findViewById(i);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    SubmitHelpingHand();
                }
            }
        });

    }

    public void SubmitHelpingHand() {
        progressDialog.show();
        try {
            String first_name_value = firstName.getText().toString();
            String last_name_value = lastName.getText().toString();
            String address_value = address.getText().toString();
            String email_value = email.getText().toString();
            String phone_number_value = phone_number.getText().toString();
            String member_ship_number_value = membershipNumber.getText().toString();
            String type_of_benefit_value = typeOfBenefit.getText().toString();
            String untitled_value = untitled.getText().toString();

            int financialSelectedId = financial.getCheckedRadioButtonId();
            RadioButton financialButton = (RadioButton) financial.findViewById(financialSelectedId);


            int vaccinatedSelectedId = vaccinated.getCheckedRadioButtonId();
            RadioButton vaccinatedButton = (RadioButton) vaccinated.findViewById(vaccinatedSelectedId);

            int employedSelectedId = employed.getCheckedRadioButtonId();
            RadioButton employedButton = (RadioButton) employed.findViewById(employedSelectedId);

            int centerLinkSelectedId = centerLink.getCheckedRadioButtonId();
            RadioButton centerLinkButton = (RadioButton) centerLink.findViewById(centerLinkSelectedId);

            int assistanceSelectedId = assistance.getCheckedRadioButtonId();
            RadioButton assistanceButton = (RadioButton) assistance.findViewById(assistanceSelectedId);

            String id = UUID.randomUUID().toString();


            Map<String, Object> helpingInfo = new HashMap<>();
            helpingInfo.put("id", id);
            helpingInfo.put("first_name", first_name_value);
            helpingInfo.put("last_name", last_name_value);
            helpingInfo.put("address", address_value);
            helpingInfo.put("email", email_value);
            helpingInfo.put("phone_number", phone_number_value);
            helpingInfo.put("member_ship_number", member_ship_number_value);
            helpingInfo.put("type_of_benefit", type_of_benefit_value);
            helpingInfo.put("untitled", untitled_value);
            helpingInfo.put("radio_financial", financialButton.getText());
            helpingInfo.put("radio_vaccinated", vaccinatedButton.getText());
            helpingInfo.put("radio_employed", employedButton.getText());
            helpingInfo.put("radio_centerLink", centerLinkButton.getText());
            helpingInfo.put("radio_assistance", assistanceButton.getText());


            fStore.collection("helping_hand").document(id)
                    .set(helpingInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            finish();
                            Toast.makeText(HelpingHandActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(HelpingHandActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (Exception e) {

        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}