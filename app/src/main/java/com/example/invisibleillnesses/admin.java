package com.example.invisibleillnesses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.invisibleillnesses.Pages.SigninActivity;

public class admin extends AppCompatActivity {
    private ImageView garments, services, add1;
    private Button LogoutBtn, CheckOrdersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

    LogoutBtn = (Button) findViewById(R.id.admin_logout_btn);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(admin.this, SigninActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    });
    CheckOrdersBtn = (Button) findViewById(R.id.check_orders_btn);


        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(admin.this,admin.class);
            startActivity(intent);
        }
    });



    garments = (ImageView) findViewById(R.id.garments);
    services = (ImageView) findViewById(R.id.services);
    add1 = (ImageView) findViewById(R.id.add1);


        garments.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            Intent intent = new Intent(admin.this, com.example.invisibleillnesses.admin.class);
            intent.putExtra("category", "tShirts");
            startActivity(intent);
        }
    });
        services.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            Intent intent = new Intent(admin.this, com.example.invisibleillnesses.admin.class);
            intent.putExtra("category", "Sports tShirts");
            startActivity(intent);
        }
    });


        add1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            Intent intent = new Intent(admin.this, com.example.invisibleillnesses.admin.class);
            intent.putExtra("category", "Female Dresses");
            startActivity(intent);
        }
    });

}
}