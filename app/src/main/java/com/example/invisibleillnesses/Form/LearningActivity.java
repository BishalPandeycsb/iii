package com.example.invisibleillnesses.Form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
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
import com.example.invisibleillnesses.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LearningActivity extends AppCompatActivity {

    EditText firstName, lastName, address, suburb, email, phone_number, explainYou, birthday, child_help, comment;
    RadioGroup gender, who_are_you;
    Toolbar toolbar;
    Button submit;
    private AwesomeValidation awesomeValidation;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Learning");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fStore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        firstName = findViewById(R.id.learning_first_name);
        lastName = findViewById(R.id.learning_last_name);
        address = findViewById(R.id.learning_address);
        suburb = findViewById(R.id.learning_suburb);
        email = findViewById(R.id.learning_email);
        phone_number = findViewById(R.id.learning_phone_no);
        explainYou = findViewById(R.id.learning_other_explain);
        birthday = findViewById(R.id.learning_birthday);
        child_help = findViewById(R.id.learning_child_help);
        comment = findViewById(R.id.learning_comment);

        gender = findViewById(R.id.learning_gender);
        who_are_you = findViewById(R.id.learning_who);

        submit = findViewById(R.id.learning_button);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.learning_first_name, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.learning_last_name, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.learning_address, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.learning_suburb, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.learning_email, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.learning_phone_no, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.learning_other_explain, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.learning_birthday, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.learning_child_help, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.learning_comment, RegexTemplate.NOT_EMPTY, R.string.valid_required);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton
                        radioButton
                        = (RadioButton) radioGroup.findViewById(i);
            }
        });

        who_are_you.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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

        try {
            progressDialog.show();
            String first_name_value = firstName.getText().toString();
            String last_name_value = lastName.getText().toString();
            String address_value = address.getText().toString();
            String email_value = email.getText().toString();
            String phone_number_value = phone_number.getText().toString();
            String suburb_value = suburb.getText().toString();
            String explain_value = explainYou.getText().toString();
            String birthday_value = birthday.getText().toString();
            String child_help_value = child_help.getText().toString();
            String comment_value = comment.getText().toString();


            int genderSelectedId = gender.getCheckedRadioButtonId();
            RadioButton genderButton = (RadioButton) gender.findViewById(genderSelectedId);


            int whoSelectedId = who_are_you.getCheckedRadioButtonId();
            RadioButton whoButton = (RadioButton) who_are_you.findViewById(whoSelectedId);


            String id = UUID.randomUUID().toString();



            Map<String, Object> helpingInfo = new HashMap<>();
            helpingInfo.put("id", id);
            helpingInfo.put("first_name", first_name_value);
            helpingInfo.put("last_name", last_name_value);
            helpingInfo.put("address", address_value);
            helpingInfo.put("email", email_value);
            helpingInfo.put("phone_number", phone_number_value);
            helpingInfo.put("suburb", suburb_value);
            helpingInfo.put("explain", explain_value);
            helpingInfo.put("birthday", birthday_value);
            helpingInfo.put("child_help", child_help_value);
            helpingInfo.put("comment", comment_value);
            helpingInfo.put("gender", genderButton.getText());
            helpingInfo.put("who_help", whoButton.getText());


            fStore.collection("learning").document(id)
                    .set(helpingInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            finish();
                            Toast.makeText(LearningActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(LearningActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
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