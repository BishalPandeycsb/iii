package com.example.invisibleillnesses.Pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.invisibleillnesses.MainActivity;
import com.example.invisibleillnesses.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {

    EditText emailField,passwordField;
    TextView forgetPassword,signUp;
    Button button;
    AwesomeValidation awesomeValidation;
    ImageView showPsw;
    SharedPreferences sharedPreferences;

    private String LogTag = "Error";
    private String fmcToken;

    ProgressDialog progressDialog;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    public static final String MyPREFERENCES = "InvisibleIllinessPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        emailField = findViewById(R.id.email_form);
        passwordField = findViewById(R.id.password_form);
        forgetPassword = findViewById(R.id.forget_password);
        signUp = findViewById(R.id.signup_account);
        button = findViewById(R.id.login_account);
        showPsw = findViewById(R.id.show_pass_btn);

        fAuth= FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.email_form, RegexTemplate.NOT_EMPTY, R.string.valid_name);

        awesomeValidation.addValidation(this, R.id.password_form, RegexTemplate.NOT_EMPTY, R.string.valid_password);

        showPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordField.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    ((ImageView) (view)).setImageResource(R.drawable.show_password_24);

                    //Show Password
                    passwordField.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ((ImageView) (view)).setImageResource(R.drawable.hide_password_off_24);

                    //Hide Password
                    passwordField.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fintent = new Intent(SigninActivity.this, ForgetPasswordActivity.class);
                startActivity(fintent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (awesomeValidation.validate()) {
                    try {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {

                    }
                    progressDialog.show();
                    String userEmail = emailField.getText().toString();
                    String userPassword = passwordField.getText().toString();


                    fAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            String uid = authResult.getUser().getUid();


                            DocumentReference df = fStore.collection("Users").document(uid);
                            //extract the data from the document
                            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    //identify the user access level
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("rememberMe", true);
                                    editor.putString("username", documentSnapshot.getString("fullname"));
                                    editor.putString("email", userEmail);
                                    editor.putString("userid",authResult.getUser().getUid());
                                    editor.putString("userRole",documentSnapshot.getString("role"));
                                    editor.commit();
                                    progressDialog.dismiss();


                                    Toast.makeText(SigninActivity.this, "You are Sign in", Toast.LENGTH_LONG).show();

                                    if (documentSnapshot.getString("role").equals("admin")) {
                                        //user is admin
                                        Intent intentSignup = new Intent(SigninActivity.this, AdminActivity.class)
                                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intentSignup);
                                        finish();
                                    }
                                    if (documentSnapshot.getString("role").equals("user")) {
                                        Intent intentSignup = new Intent(SigninActivity.this, MainActivity.class)
                                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intentSignup);
                                        finish();
                                    }
                                }
                            });



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(SigninActivity.this,"Failed to Sign in",Toast.LENGTH_SHORT).show();
                        }
                    });





                }
            }
        });
    }
}