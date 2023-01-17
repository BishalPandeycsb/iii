package com.example.invisibleillnesses.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invisibleillnesses.Admin.AdminProductOrderCartActivity;
import com.example.invisibleillnesses.Model.DonationModel;
import com.example.invisibleillnesses.Model.OrderCartModel;
import com.example.invisibleillnesses.Model.ProductOrderModel;
import com.example.invisibleillnesses.R;

import java.util.ArrayList;
import java.util.List;

public class ProductOrderAdapter extends RecyclerView.Adapter<ProductOrderAdapter.ProductOrderHolder> {
    List<ProductOrderModel> list;
    Context context;
    ProductOrderCartAdapter productOrderAdapter;


    public ProductOrderAdapter(Context ct, List<ProductOrderModel> plist) {
        context = ct;
        list = plist;
    }

    @NonNull
    @Override
    public ProductOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_product_order, parent, false);
        return new ProductOrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductOrderHolder holder, int position) {
        ProductOrderModel donationModel = list.get(position);
        holder.firstName.setText(donationModel.getFirst_name());
        holder.lastName.setText(donationModel.getLast_name());
        holder.suburb.setText(donationModel.getSuburb_value());
        holder.streetName.setText(donationModel.getStreet_name());
        holder.apartmentStatus.setText(donationModel.getApartment_status());
        holder.postCode.setText(donationModel.getPost_code());
        holder.phoneNo.setText(donationModel.getPhone_value());
        holder.emailAddress.setText(donationModel.getEmail_address());
        holder.donationPrice.setText(donationModel.getTotal_price());

//        for (int i = 0; i < donationModel.getCarList().size(); i++) {
//            Log.i("systemi", donationModel.getCarList().get(i));
//        }

//        productOrderAdapter = new ProductOrderCartAdapter(context, donationModel.getCarList());
//        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        holder.recyclerView.setAdapter(productOrderAdapter);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AdminProductOrderCartActivity.class);
                i.putExtra("product_order_id", donationModel.getId());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductOrderHolder extends RecyclerView.ViewHolder {
        TextView firstName, lastName, streetName, apartmentStatus, suburb, postCode, phoneNo, emailAddress, donationPrice;
        CardView cardView;
        RecyclerView recyclerView;

        public ProductOrderHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.admin_product_first_name);
            lastName = itemView.findViewById(R.id.admin_product_last_name);
            streetName = itemView.findViewById(R.id.admin_product_street_name);
            apartmentStatus = itemView.findViewById(R.id.admin_product_apartment_status);
            suburb = itemView.findViewById(R.id.admin_product_suburb_value);
            postCode = itemView.findViewById(R.id.admin_product_post_code);
            phoneNo = itemView.findViewById(R.id.admin_product_phone_value);
            emailAddress = itemView.findViewById(R.id.admin_product_email_address);
            donationPrice = itemView.findViewById(R.id.admin_product_donation_price);
            cardView = itemView.findViewById(R.id.cart_product_cart_view);
            recyclerView = itemView.findViewById(R.id.product_cart_recycler_view);
        }
    }
}
