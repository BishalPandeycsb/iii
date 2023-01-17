package com.example.invisibleillnesses.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.invisibleillnesses.Adapter.HelpingHandAdapter;
import com.example.invisibleillnesses.Adapter.LearningAdapter;
import com.example.invisibleillnesses.Model.HelpingHandModel;
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

public class AdminLearningActivity extends AppCompatActivity {

    FirebaseFirestore fStore;
    HashMap<String, String> hashMap;
    RecyclerView recyclerView;
    private List<Learning> list;
    LearningAdapter learningAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_learning);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Learning");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fStore = FirebaseFirestore.getInstance();
        hashMap = new HashMap<>();

        recyclerView = findViewById(R.id.learning_recycler_view);

        list = new ArrayList<>();


        getEventData();

    }


    public void getEventData() {
        fStore.collection("learning").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {

                    Learning learning = new Learning(snapshot.getString("id"), snapshot.getString("first_name"), snapshot.getString("last_name"),
                            snapshot.getString("address"),
                            snapshot.getString("email"),
                            snapshot.getString("phone_number"),
                            snapshot.getString("suburb"),
                            snapshot.getString("explain"),
                            snapshot.getString("birthday"),
                            snapshot.getString("child_help"),
                            snapshot.getString("comment"),
                            snapshot.getString("gender"),
                            snapshot.getString("who_help"));
                    list.add(learning);
                    learningAdapter = new LearningAdapter(getBaseContext(), list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setAdapter(learningAdapter);

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