package com.example.invisibleillnesses;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.invisibleillnesses.Pages.AdminActivity;
import com.example.invisibleillnesses.Pages.SigninActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashScreenActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "InvisibleIllinessPrefs";
    private Handler mWaitHandler = new Handler();
    Boolean rememberMe;
    String roleDefine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        rememberMe = sharedPreferences.getBoolean("rememberMe", false);
        roleDefine = sharedPreferences.getString("userRole",null);


        mWaitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (rememberMe) {

                        if(roleDefine.equals("admin")){
                            Intent userIntent = new Intent(SplashScreenActivity.this, AdminActivity.class);
                            startActivity(userIntent);
                            finish();
                        }

                        if (roleDefine.equals("user")) {
                            Intent intentSignup = new Intent(SplashScreenActivity.this, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intentSignup);
                            finish();
                        }




                    } else {
                        Intent loginIntent = new Intent(SplashScreenActivity.this, SigninActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5000);
    }
}