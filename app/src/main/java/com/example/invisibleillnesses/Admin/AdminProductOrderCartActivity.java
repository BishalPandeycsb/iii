package com.example.invisibleillnesses.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.invisibleillnesses.Adapter.CartAdapter;
import com.example.invisibleillnesses.Adapter.ProductOrderCartAdapter;
import com.example.invisibleillnesses.Model.CartModel;
import com.example.invisibleillnesses.Model.OrderCartModel;
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

public class AdminProductOrderCartActivity extends AppCompatActivity {

    private ArrayList<CartModel> list;
    ProductOrderCartAdapter productOrderAdapter;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    FirebaseFirestore fStore;
    RecyclerView recyclerView;
    HashMap<String, String> hashMap;
    CartModel cartModel;
    String product_order_id_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_order_cart);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Product Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.product_cart_recycler_view);


        fStore = FirebaseFirestore.getInstance();
        product_order_id_value = getIntent().getStringExtra("product_order_id");


        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        productOrderAdapter = new ProductOrderCartAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(productOrderAdapter);

        getProductData();
    }

    public void getProductData() {

        fStore.collection("product_order_items").whereEqualTo("product_order_id", product_order_id_value).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {

                    cartModel = new CartModel(
                            snapshot.getString("id"),
                            snapshot.getString("product_id"),
                            snapshot.getString("product_name"),
                            snapshot.getString("product_price"),
                            snapshot.getString("product_designer"),
                            snapshot.getString("product_view_size"),
                            snapshot.getString("product_view_refund"),
                            snapshot.getString("product_weekend"),
                            snapshot.getString("product_view_short_des"),
                            snapshot.getString("product_view_des"),
                            snapshot.getString("product_view_image"),
                            snapshot.getString("user_id"),
                            snapshot.getString("user_email"),
                            snapshot.getString("product_choose_date")

                    );
                    list.add(cartModel);
                }

                productOrderAdapter.notifyDataSetChanged();

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