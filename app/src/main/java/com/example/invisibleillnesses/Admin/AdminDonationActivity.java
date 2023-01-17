package com.example.invisibleillnesses.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.invisibleillnesses.Adapter.DonationAdapter;
import com.example.invisibleillnesses.Adapter.EventAdapter;
import com.example.invisibleillnesses.Adapter.LearningAdapter;
import com.example.invisibleillnesses.Model.DonationModel;
import com.example.invisibleillnesses.Model.EventModel;
import com.example.invisibleillnesses.Model.Learning;
import com.example.invisibleillnesses.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminDonationActivity extends AppCompatActivity {

    FirebaseFirestore fStore;
    HashMap<String, String> hashMap;
    RecyclerView recyclerView;
    private List<DonationModel> list;
    DonationAdapter donationAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_donation);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Donation");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fStore = FirebaseFirestore.getInstance();
        hashMap = new HashMap<>();

        recyclerView = findViewById(R.id.donation_recycler_view);

        list = new ArrayList<>();


        getEventData();
    }

    public void getEventData() {
        fStore.collection("donation_order").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    DonationModel donationModel = new DonationModel(
                            snapshot.getString("id"),
                            snapshot.getString("first_name"),
                            snapshot.getString("last_name"),
                            snapshot.getString("street_name"),
                            snapshot.getString("apartment_status"),
                            snapshot.getString("suburb_value"),
                            snapshot.getString("post_code"),
                            snapshot.getString("phone_value"),
                            snapshot.getString("email_address"),
                            snapshot.getString("donation_price"));
                    list.add(donationModel);
                    donationAdapter = new DonationAdapter(getBaseContext(), list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setAdapter(donationAdapter);
                }
//                mentoringAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}