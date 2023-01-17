package com.example.invisibleillnesses.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.invisibleillnesses.Adapter.EventOrderAdapter;
import com.example.invisibleillnesses.Adapter.ProductOrderAdapter;
import com.example.invisibleillnesses.Model.EventOrderModel;
import com.example.invisibleillnesses.Model.ProductOrderModel;
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

public class EventOrderActivity extends AppCompatActivity {

    FirebaseFirestore fStore;
    HashMap<String, String> hashMap;
    RecyclerView recyclerView;
    private List<EventOrderModel> list;
    EventOrderAdapter eventOrderAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_order);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Event Book");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fStore = FirebaseFirestore.getInstance();
        hashMap = new HashMap<>();

        fStore = FirebaseFirestore.getInstance();
        hashMap = new HashMap<>();

        recyclerView = findViewById(R.id.event_order_recycler_view);

        list = new ArrayList<>();

        getEventData();
    }

    public void getEventData() {
        fStore.collection("event_book").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {


                    EventOrderModel eventOrderModel = new EventOrderModel(
                            snapshot.getString("id"),
                            snapshot.getString("first_name"),
                            snapshot.getString("last_name"),
                            snapshot.getString("street_name"),
                            snapshot.getString("apartment_status"),
                            snapshot.getString("suburb_value"),
                            snapshot.getString("post_code"),
                            snapshot.getString("phone_value"),
                            snapshot.getString("email_address"),
                            snapshot.getString("event_id"),
                            snapshot.getString("event_name"),
                            snapshot.getString("event_price"),
                            snapshot.getString("event_location"),
                            snapshot.getString("event_date"),
                            snapshot.getString("event_des"),
                            snapshot.getString("event_photo")
                    );
                    list.add(eventOrderModel);
                    eventOrderAdapter = new EventOrderAdapter(getBaseContext(), list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setAdapter(eventOrderAdapter);

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