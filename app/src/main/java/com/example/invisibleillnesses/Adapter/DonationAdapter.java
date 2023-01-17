package com.example.invisibleillnesses.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invisibleillnesses.Model.DonationModel;
import com.example.invisibleillnesses.R;

import java.util.ArrayList;
import java.util.List;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationHolder> {
    private List<DonationModel> list;
    Context context;

    public DonationAdapter(Context ct, List<DonationModel> eList) {
        list = eList;
        context = ct;
    }

    @NonNull
    @Override
    public DonationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_donation, parent, false);
        return new DonationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationHolder holder, int position) {
        DonationModel donationModel = list.get(position);
        holder.firstName.setText(donationModel.getFirst_name());
        holder.lastName.setText(donationModel.getLast_name());
        holder.suburb.setText(donationModel.getSuburb_value());
        holder.streetName.setText(donationModel.getStreet_name());
        holder.apartmentStatus.setText(donationModel.getApartment_status());
        holder.postCode.setText(donationModel.getPost_code());
        holder.phoneNo.setText(donationModel.getPhone_value());
        holder.emailAddress.setText(donationModel.getEmail_address());
        holder.donationPrice.setText(donationModel.getDonation_price());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DonationHolder extends RecyclerView.ViewHolder {
        TextView firstName, lastName, streetName, apartmentStatus, suburb, postCode, phoneNo, emailAddress, donationPrice;

        public DonationHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.admin_donation_first_name);
            lastName = itemView.findViewById(R.id.admin_donation_last_name);
            streetName = itemView.findViewById(R.id.admin_donation_street_name);
            apartmentStatus = itemView.findViewById(R.id.admin_donation_apartment_status);
            suburb = itemView.findViewById(R.id.admin_donation_suburb_value);
            postCode = itemView.findViewById(R.id.admin_donation_post_code);
            phoneNo = itemView.findViewById(R.id.admin_donation_phone_value);
            emailAddress = itemView.findViewById(R.id.admin_donation_email_address);
            donationPrice = itemView.findViewById(R.id.admin_donation_donation_price);
        }
    }
}
