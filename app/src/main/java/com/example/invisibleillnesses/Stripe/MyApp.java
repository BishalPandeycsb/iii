package com.example.invisibleillnesses.Stripe;

import android.app.Application;

import com.stripe.android.PaymentConfiguration;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PaymentConfiguration.init(
                getApplicationContext(),
                "pk_live_UOSEkqzd2Qx8gELpukD5wYGc00PYT6ElJl"
        );
    }
}
