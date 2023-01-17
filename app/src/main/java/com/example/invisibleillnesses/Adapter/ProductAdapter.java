package com.example.invisibleillnesses.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invisibleillnesses.Admin.EditProductActivity;
import com.example.invisibleillnesses.Dialog.ConfirmDialog;
import com.example.invisibleillnesses.Model.ProductModel;
import com.example.invisibleillnesses.Pages.ProductDetailActivity;
import com.example.invisibleillnesses.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    Context context;
    private List<ProductModel> plist;
    Boolean curdShowOrNot;

    public ProductAdapter(Context ct, List<ProductModel> list, Boolean crudShow) {
        context = ct;
        plist = list;
        curdShowOrNot = crudShow;

    }

    @NonNull
    @Override
    public ProductAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_product, parent, false);
        return new ProductHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductHolder holder, int position) {
        ProductModel productModel = plist.get(position);
        holder.pName.setText(productModel.getName());
        holder.pPrice.setText(String.valueOf(productModel.getPrice()));
//        holder.imageView
        Picasso.get().load(productModel.getPhoto()).into(holder.imageView);
        holder.eDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("DeleteId", productModel.getId());
                ConfirmDialog confirmDialog = new ConfirmDialog(productModel.getId(), "product");
                confirmDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "Logout Dialog Box");
            }
        });

        if (curdShowOrNot) {
            holder.relativeLayout.setVisibility(View.GONE);
        }

        if (curdShowOrNot) {
            holder.productDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detailIntent = new Intent(context, ProductDetailActivity.class);
                    detailIntent.putExtra("id", productModel.getId());
                    detailIntent.putExtra("name", productModel.getName());
                    detailIntent.putExtra("price", productModel.getPrice());
                    detailIntent.putExtra("designer", productModel.getDesigner());
                    detailIntent.putExtra("size", productModel.getSize());
                    detailIntent.putExtra("refundable", productModel.getRefundable());
                    detailIntent.putExtra("weekend", productModel.getWeekend_hire());
                    detailIntent.putExtra("shortDes", productModel.getShort_description());
                    detailIntent.putExtra("des", productModel.getDescription());
                    detailIntent.putExtra("photo", productModel.getPhoto());
                    detailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(detailIntent);
                }
            });
        }


        holder.eEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditProductActivity.class);
                i.putExtra("id", productModel.getId());
                i.putExtra("name", productModel.getName());
                i.putExtra("price", productModel.getPrice());
                i.putExtra("designer", productModel.getDesigner());
                i.putExtra("size", productModel.getSize());
                i.putExtra("refundable", productModel.getRefundable());
                i.putExtra("weekend", productModel.getWeekend_hire());
                i.putExtra("shortDes", productModel.getShort_description());
                i.putExtra("des", productModel.getDescription());
                i.putExtra("photo", productModel.getPhoto());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plist.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        TextView pPrice, pName;
        ImageView imageView;
        Button eDelete, eEdit;
        RelativeLayout relativeLayout;
        CardView productDetail;


        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            pPrice = itemView.findViewById(R.id.product_price_show);
            pName = itemView.findViewById(R.id.product_view_name_show);
            imageView = itemView.findViewById(R.id.product_image_show);
            eDelete = itemView.findViewById(R.id.product_delete_show);
            eEdit = itemView.findViewById(R.id.product_edit_show);
            relativeLayout = itemView.findViewById(R.id.product_crud_wrapper);
            productDetail = itemView.findViewById(R.id.product_details_show);
        }
    }
}
