package com.example.invisibleillnesses.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.invisibleillnesses.Adapter.HelpingHandAdapter;
import com.example.invisibleillnesses.Adapter.MentoringAdapter;
import com.example.invisibleillnesses.Model.HelpingHandModel;
import com.example.invisibleillnesses.Model.Mentoring;
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

public class AdminHelpingHandActivity extends AppCompatActivity {

    FirebaseFirestore fStore;
    HashMap<String, String> hashMap;
    RecyclerView recyclerView;
    private List<HelpingHandModel> list;
    HelpingHandAdapter helpingHandAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_helping_hand);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Helping Hand");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fStore = FirebaseFirestore.getInstance();
        hashMap = new HashMap<>();

        recyclerView = findViewById(R.id.helping_hand_recycler_view);

        list = new ArrayList<>();


        getEventData();

    }

    public void getEventData() {
        fStore.collection("helping_hand").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    HelpingHandModel helpingHandModel = new HelpingHandModel(snapshot.getString("id"), snapshot.getString("first_name"), snapshot.getString("last_name"),  snapshot.getString("address"),snapshot.getString("email"), snapshot.getString("phone_number"),snapshot.getString("member_ship_number"),snapshot.getString("type_of_benefit"),snapshot.getString("untitled"),snapshot.getString("radio_financial"),snapshot.getString("radio_vaccinated"),snapshot.getString("radio_employed"),snapshot.getString("radio_centerLink"),snapshot.getString("radio_assistance"));
                    list.add(helpingHandModel);
                    helpingHandAdapter = new HelpingHandAdapter(getBaseContext(), list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setAdapter(helpingHandAdapter);

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