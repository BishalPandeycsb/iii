package com.example.invisibleillnesses.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invisibleillnesses.Dialog.ConfirmDialog;
import com.example.invisibleillnesses.Model.CartModel;
import com.example.invisibleillnesses.Model.Learning;
import com.example.invisibleillnesses.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    Context context;
    private List<CartModel> elist;

    public CartAdapter(Context ct, List<CartModel> list) {
        context = ct;
        elist = list;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_cart, parent, false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        CartModel cartModel = elist.get(position);
        holder.name.setText(cartModel.getProduct_name());
        int totalPrice = Integer.parseInt(cartModel.getProduct_view_refund()) + Integer.parseInt(cartModel.getProduct_weekend());
        holder.price.setText(String.valueOf(totalPrice));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDialog confirmDialog = new ConfirmDialog(cartModel.getId(), "product_book");
                confirmDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "Logout Dialog Box");
            }
        });
    }

    @Override
    public int getItemCount() {
        return elist.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        TextView price, name;
        ImageView imageView;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_cart_name);
            price = itemView.findViewById(R.id.product_cart_price);
            imageView = itemView.findViewById(R.id.cart_item_delete);
        }
    }
}
