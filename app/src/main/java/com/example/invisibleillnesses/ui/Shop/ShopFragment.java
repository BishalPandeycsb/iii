package com.example.invisibleillnesses.ui.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invisibleillnesses.Adapter.EventAdapter;
import com.example.invisibleillnesses.Adapter.ProductAdapter;
import com.example.invisibleillnesses.Model.EventModel;
import com.example.invisibleillnesses.Model.ProductModel;
import com.example.invisibleillnesses.ProductDetailsActivity;
import com.example.invisibleillnesses.R;
import com.example.invisibleillnesses.databinding.FragmentShopBinding;
import com.example.invisibleillnesses.ui.Model.Products;
import com.example.invisibleillnesses.ui.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopFragment extends Fragment {
    private Button searchBtn;
    private EditText inputText;
    private RecyclerView recyclerView;
    private String searchInput;
    FirebaseFirestore fStore;
    HashMap<String, String> hashMap;
    private List<ProductModel> list;
    private List<ProductModel> searchList;
    ListAdapter adapter;
    ProductAdapter productAdapter;
    RecyclerView.LayoutManager layoutManager;
    ProductModel productModel;

    private FragmentShopBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ShopViewModel shopViewModel =
                new ViewModelProvider(this).get(ShopViewModel.class);

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        fStore = FirebaseFirestore.getInstance();
        hashMap = new HashMap<>();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputText = view.findViewById(R.id.search_product_name);
        searchBtn = view.findViewById(R.id.search_btn);
        recyclerView = view.findViewById(R.id.shop_fragment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchInput = inputText.getText().toString();
                onSearchStart(searchInput);

            }
        });

        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        searchList = new ArrayList<>();

        recyclerView.setAdapter(productAdapter);
        getProductData();

    }

    public void getProductData() {
        fStore.collection("product").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    productModel = new ProductModel(snapshot.getString("id"), snapshot.getString("name"), snapshot.getString("price"), snapshot.getString("designer"), snapshot.getString("size"), snapshot.getString("refundable"), snapshot.getString("weekend_hire"), snapshot.getString("short_description"), snapshot.getString("description"), snapshot.getString("photo"));
                    productAdapter = new ProductAdapter(getContext(), list,true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(productAdapter);
                    list.add(productModel);
                }

                productAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onSearchStart(String searchValue) {

        if (searchList.size() > 0) {
            for (ProductModel item : searchList) {
                if (item.getName().toLowerCase().contains(searchValue)) {
                    productModel = new ProductModel(item.getId(), item.getName(), item.getPrice(), item.getDesigner(), item.getSize(), item.getRefundable(), item.getWeekend_hire(), item.getShort_description(), item.getDescription(), item.getPhoto());
                    list.add(productModel);
                }
                productAdapter.notifyDataSetChanged();
            }
        }


    }
}

