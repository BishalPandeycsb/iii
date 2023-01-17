package com.example.invisibleillnesses.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invisibleillnesses.Model.EventOrderModel;
import com.example.invisibleillnesses.Model.ProductOrderModel;
import com.example.invisibleillnesses.R;

import java.util.List;

public class EventOrderAdapter extends RecyclerView.Adapter<EventOrderAdapter.EventOrderViewHolder> {
    List<EventOrderModel> list;
    Context context;

    public EventOrderAdapter(Context ct, List<EventOrderModel> plist) {
        context = ct;
        list = plist;
    }


    @NonNull
    @Override
    public EventOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_event_order, parent, false);
        return new EventOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventOrderViewHolder holder, int position) {
        EventOrderModel eventOrderModel = list.get(position);
        holder.firstName.setText(eventOrderModel.getFirst_name());
        holder.lastName.setText(eventOrderModel.getLast_name());
        holder.suburb.setText(eventOrderModel.getSuburb_value());
        holder.streetName.setText(eventOrderModel.getStreet_name());
        holder.apartmentStatus.setText(eventOrderModel.getApartment_status());
        holder.postCode.setText(eventOrderModel.getPost_code());
        holder.phoneNo.setText(eventOrderModel.getPhone_value());
        holder.emailAddress.setText(eventOrderModel.getEmail_address());
        holder.eventName.setText(eventOrderModel.getEvent_name());
        holder.eventPrice.setText(eventOrderModel.getEvent_price());
        holder.eventLocation.setText(eventOrderModel.getEvent_location());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EventOrderViewHolder extends RecyclerView.ViewHolder {
        TextView firstName, lastName, streetName, apartmentStatus, suburb, postCode, phoneNo, emailAddress, eventName, eventPrice, eventLocation;

        public EventOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.admin_event_first_name);
            lastName = itemView.findViewById(R.id.admin_event_last_name);
            streetName = itemView.findViewById(R.id.admin_event_street_name);
            apartmentStatus = itemView.findViewById(R.id.admin_event_apartment_status);
            suburb = itemView.findViewById(R.id.admin_event_suburb_value);
            postCode = itemView.findViewById(R.id.admin_event_post_code);
            phoneNo = itemView.findViewById(R.id.admin_event_phone_value);
            emailAddress = itemView.findViewById(R.id.admin_event_email_address);
            eventName = itemView.findViewById(R.id.admin_event_name);
            eventPrice = itemView.findViewById(R.id.admin_event_price);
            eventLocation = itemView.findViewById(R.id.admin_event_location);
        }
    }
}
