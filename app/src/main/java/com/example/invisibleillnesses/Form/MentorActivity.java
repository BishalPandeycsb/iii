package com.example.invisibleillnesses.Form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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

public class MentorActivity extends AppCompatActivity {

    EditText fullName, suburb, email, phone_number, comment;

    Toolbar toolbar;
    Button submit;
    private AwesomeValidation awesomeValidation;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        String headerTitle = getIntent().getStringExtra("titleName");
        toolbar.setTitle(headerTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fStore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        fullName = findViewById(R.id.mentoring_full_name);
        suburb = findViewById(R.id.mentoring_suburb);
        email = findViewById(R.id.mentoring_email);
        phone_number = findViewById(R.id.mentoring_phone_no);
        comment = findViewById(R.id.mentoring_message);


        submit = findViewById(R.id.mentoring_button);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.mentoring_full_name, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.mentoring_suburb, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.mentoring_email, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.mentoring_phone_no, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(this, R.id.learning_comment, RegexTemplate.NOT_EMPTY, R.string.valid_required);

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


            String first_name_value = fullName.getText().toString();
            String suburb_value = suburb.getText().toString();
            String email_value = email.getText().toString();
            String phone_number_value = phone_number.getText().toString();
            String comment_value = comment.getText().toString();


            String id = UUID.randomUUID().toString();


            Map<String, Object> helpingInfo = new HashMap<>();
            helpingInfo.put("id", id);
            helpingInfo.put("first_name", first_name_value);
            helpingInfo.put("suburb", suburb_value);
            helpingInfo.put("email", email_value);
            helpingInfo.put("phone_number", phone_number_value);
            helpingInfo.put("comment", comment_value);


            fStore.collection("mentor_data").document(id)
                    .set(helpingInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            finish();
                            Toast.makeText(MentorActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(MentorActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (Exception e) {
            Log.i("error", e.getMessage().toString());
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}