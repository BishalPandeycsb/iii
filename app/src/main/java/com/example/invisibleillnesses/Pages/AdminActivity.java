package com.example.invisibleillnesses.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.invisibleillnesses.Admin.AddProductActivity;
import com.example.invisibleillnesses.Admin.AdminDonationActivity;
import com.example.invisibleillnesses.Admin.AdminEventActivity;
import com.example.invisibleillnesses.Admin.AdminHelpingHandActivity;
import com.example.invisibleillnesses.Admin.AdminLearningActivity;
import com.example.invisibleillnesses.Admin.AdminMentoringActivity;
import com.example.invisibleillnesses.Admin.AdminProductActivity;
import com.example.invisibleillnesses.Admin.AdminProductOrderActivity;
import com.example.invisibleillnesses.Admin.EventOrderActivity;
import com.example.invisibleillnesses.Dialog.LogoutDialog;
import com.example.invisibleillnesses.Form.MentorActivity;
import com.example.invisibleillnesses.R;

public class AdminActivity extends AppCompatActivity {

    CardView productCardView, eventCardView, mentoringCardView, helpingHandCardView, learningCardView, hairDresserBox, nailTechBox, productOrderBox, donationBox, eventBookCardView;
    Button logOutBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);
        productCardView = findViewById(R.id.product_view_box);
        eventCardView = findViewById(R.id.event_view_box);
        mentoringCardView = findViewById(R.id.mentoring_admin_box);
        helpingHandCardView = findViewById(R.id.helping_hand_admin_box);
        learningCardView = findViewById(R.id.learning_box);
        logOutBox = findViewById(R.id.logout_button);
        hairDresserBox = findViewById(R.id.hairdresser_box);
        nailTechBox = findViewById(R.id.nail_tech_box);
        donationBox = findViewById(R.id.admin_donation_box);
        productOrderBox = findViewById(R.id.product_show_box);
        eventBookCardView = findViewById(R.id.admin_event_book_box);


        productCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AdminProductActivity.class);
                startActivity(i);
            }
        });

        eventCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AdminEventActivity.class);
                startActivity(i);
            }
        });

        mentoringCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AdminMentoringActivity.class);
                i.putExtra("titleName", "Mentoring");
                startActivity(i);
            }
        });

        helpingHandCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AdminHelpingHandActivity.class);
                startActivity(i);
            }
        });

        learningCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AdminLearningActivity.class);
                startActivity(i);
            }
        });

        hairDresserBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AdminMentoringActivity.class);
                i.putExtra("titleName", "Hair Dresser");
                startActivity(i);
            }
        });

        nailTechBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AdminMentoringActivity.class);
                i.putExtra("titleName", "Nail Tech");
                startActivity(i);
            }
        });

        donationBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AdminDonationActivity.class);
                startActivity(i);
            }
        });

        productOrderBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, AdminProductOrderActivity.class);
                startActivity(i);
            }
        });

        eventBookCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminActivity.this, EventOrderActivity.class);
                startActivity(i);
            }
        });

        logOutBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogoutDialog logoutDialog = new LogoutDialog();
                logoutDialog.show(getSupportFragmentManager(), "Logout Dialog Box");
            }
        });
    }
}