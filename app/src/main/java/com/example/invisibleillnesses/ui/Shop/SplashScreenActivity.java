package com.example.invisibleillnesses.ui.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.invisibleillnesses.R;
import com.example.invisibleillnesses.ui.Donation.DonationFragment;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView containerforchange;
    private TextView donatenow;

    Animation topAnimantion, bottomanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        containerforchange = findViewById(R.id.containerforchange);
        donatenow =findViewById(R.id.donatenow);

        topAnimantion = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        containerforchange.setAnimation(topAnimantion);
        donatenow.setAnimation(bottomanimation);

        int SPLASH_SCREEN = 4300;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, DonationFragment.class);
                startActivity(intent);
                finish();
            }

        }, SPLASH_SCREEN);

    }
}
