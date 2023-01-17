package com.example.invisibleillnesses;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.invisibleillnesses.Pages.SigninActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    EditText email, password;
    Button btn_signup;
    ImageView icon_fb, icon_meta, icon_link;
    TextView click_Login;
    Boolean valid= true;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fAuth= FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();
        email=findViewById(R.id.fill_email);
        password=findViewById(R.id.fill_password);
        btn_signup=findViewById(R.id.btn_signup);
        click_Login=findViewById(R.id.click_Login);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(email);
                checkField(password);
                if(valid){
                    //start the user signup process
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser Users= fAuth.getCurrentUser();
                            Toast.makeText(Signup.this, "You are Signed Up, Please Log In", Toast.LENGTH_LONG).show();
                            DocumentReference df= fStore.collection("Users").document(Users.getUid());
                            Map<String,Object> userInfo = new HashMap<>();
                            userInfo.put("Email",email.getText().toString());
                            userInfo.put("isUser","1");
                            // userInfo.put("FullName", fullName.getText().toString());
                            //specify if the user is admin
                            df.set(userInfo);
                            startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Signup.this,"Failed to Sign Up",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
        click_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SigninActivity.class));
            }
        });
        icon_fb = (ImageView) findViewById(R.id.icon_fb);
        icon_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new  Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/invisibleillnessesinc/"));
                startActivity(intent);
            }
        });
        icon_meta = (ImageView) findViewById(R.id.icon_meta);
        icon_meta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new  Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.instagram.com/invisibleillneseswa/"));
                startActivity(intent);
            }
        });
        icon_link = (ImageView) findViewById(R.id.icon_link);
        icon_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new  Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/in/invisibleillnesses/"));
                startActivity(intent);
            }
        });


    }

    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        }else {
            valid=true;
        }
        return valid;
    }
}
