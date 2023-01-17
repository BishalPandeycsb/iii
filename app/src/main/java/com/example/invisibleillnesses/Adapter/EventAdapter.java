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

import com.example.invisibleillnesses.Admin.EditEventActivity;
import com.example.invisibleillnesses.Admin.EditProductActivity;
import com.example.invisibleillnesses.Dialog.ConfirmDialog;
import com.example.invisibleillnesses.Model.EventModel;
import com.example.invisibleillnesses.Model.ProductModel;
import com.example.invisibleillnesses.Pages.EventDetailActivity;
import com.example.invisibleillnesses.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    Context context;
    private List<EventModel> elist;
    Boolean curdShowOrNot;

    public EventAdapter(Context ct, List<EventModel> list, Boolean crudShow) {
        context = ct;
        elist = list;
        curdShowOrNot = crudShow;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_event, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        EventModel eventModel = elist.get(position);
        holder.eName.setText(eventModel.getName());
        holder.eLocation.setText(String.valueOf(eventModel.getLocation()));
        holder.eDate.setText(String.valueOf(eventModel.getDate()));
        holder.eTicketPrice.setText(String.valueOf(eventModel.getPrice()));
        Picasso.get().load(eventModel.getPhoto()).into(holder.imageView);
        if (curdShowOrNot) {
            holder.eCrudBox.setVisibility(View.GONE);
        }

        if (curdShowOrNot) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ei = new Intent(context, EventDetailActivity.class);
                    ei.putExtra("id", eventModel.getId());
                    ei.putExtra("name", eventModel.getName());
                    ei.putExtra("price", eventModel.getPrice());
                    ei.putExtra("location", eventModel.getLocation());
                    ei.putExtra("date", eventModel.getDate());
                    ei.putExtra("des", eventModel.getDescription());
                    ei.putExtra("photo", eventModel.getPhoto());
                    ei.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(ei);
                }
            });
        }

        holder.eDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDialog confirmDialog = new ConfirmDialog(eventModel.getId(), "event");
                confirmDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "Logout Dialog Box");
            }
        });

        holder.eEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditEventActivity.class);
                i.putExtra("id", eventModel.getId());
                i.putExtra("name", eventModel.getName());
                i.putExtra("price", eventModel.getPrice());
                i.putExtra("location", eventModel.getLocation());
                i.putExtra("date", eventModel.getDate());
                i.putExtra("des", eventModel.getDescription());
                i.putExtra("photo", eventModel.getPhoto());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return elist.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        TextView eLocation, eDate, eName, eTicketPrice;
        Button eDelete, eEdit;
        RelativeLayout eCrudBox;
        ImageView imageView;
        CardView cardView;

        public EventHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.event_image_show);
            eName = itemView.findViewById(R.id.event_view_name_show);
            eLocation = itemView.findViewById(R.id.event_location_show);
            eDate = itemView.findViewById(R.id.event_date_show);
            eDelete = itemView.findViewById(R.id.event_delete_show);
            eEdit = itemView.findViewById(R.id.event_edit_show);
            eCrudBox = itemView.findViewById(R.id.event_crud_wrapper);
            cardView = itemView.findViewById(R.id.event_card_view);
            eTicketPrice = itemView.findViewById(R.id.event_ticket_price_show);
        }
    }
}
