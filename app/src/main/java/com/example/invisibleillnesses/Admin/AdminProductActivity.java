package com.example.invisibleillnesses.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.invisibleillnesses.Adapter.ProductAdapter;
import com.example.invisibleillnesses.Dialog.LogoutDialog;
import com.example.invisibleillnesses.Model.ProductModel;
import com.example.invisibleillnesses.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminProductActivity extends AppCompatActivity {

    FirebaseFirestore fStore;
    HashMap<String, String> hashMap;
    RecyclerView recyclerView;
    private List<ProductModel> list;
    ProductAdapter productAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Product");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        fStore = FirebaseFirestore.getInstance();
        hashMap = new HashMap<>();

        recyclerView = findViewById(R.id.product_recycler_view);

        list = new ArrayList<>();

        getProductData();

    }


    public void getProductData() {
        fStore.collection("product").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    ProductModel productModel = new ProductModel(snapshot.getString("id"), snapshot.getString("name"), snapshot.getString("price"), snapshot.getString("designer"), snapshot.getString("size"), snapshot.getString("refundable"), snapshot.getString("weekend_hire"), snapshot.getString("short_description"), snapshot.getString("description"), snapshot.getString("photo"));
                    list.add(productModel);
                    productAdapter = new ProductAdapter(AdminProductActivity.this, list, false);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setAdapter(productAdapter);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.add_button_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_product_items:
                Intent addIntent = new Intent(this, AddProductActivity.class);
                startActivity(addIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}