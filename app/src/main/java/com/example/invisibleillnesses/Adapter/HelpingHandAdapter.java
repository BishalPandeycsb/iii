package com.example.invisibleillnesses.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invisibleillnesses.Model.HelpingHandModel;
import com.example.invisibleillnesses.Model.Mentoring;
import com.example.invisibleillnesses.R;

import java.util.List;

public class HelpingHandAdapter extends RecyclerView.Adapter<HelpingHandAdapter.HelpingHandHolder> {
    Context context;
    private List<HelpingHandModel> elist;

    public HelpingHandAdapter(Context ct, List<HelpingHandModel> list) {
        context = ct;
        elist = list;
    }

    @NonNull
    @Override
    public HelpingHandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_helping_hand, parent, false);
        return new HelpingHandHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpingHandHolder holder, int position) {
        HelpingHandModel helpingHandModel = elist.get(position);
        holder.first_name.setText(helpingHandModel.getFirst_name());
        holder.last_name.setText(helpingHandModel.getLast_name());
        holder.address.setText(helpingHandModel.getAddress());
        holder.email.setText(helpingHandModel.getEmail());
        holder.phone.setText(helpingHandModel.getPhone_number());
        holder.membership.setText(helpingHandModel.getMember_ship_number());
        holder.benefit.setText(helpingHandModel.getType_of_benefit());
        holder.financial.setText(helpingHandModel.getRadio_financial());
        holder.vaccinated.setText(helpingHandModel.getRadio_vaccinated());
        holder.employed.setText(helpingHandModel.getRadio_employed());
        holder.center_link.setText(helpingHandModel.getRadio_centerLink());
        holder.assistance.setText(helpingHandModel.getRadio_assistance());
        holder.untitled.setText(helpingHandModel.getUntitled());
    }

    @Override
    public int getItemCount() {
        return elist.size();
    }

    public class HelpingHandHolder extends RecyclerView.ViewHolder {


        TextView first_name, last_name, address, email, phone, untitled, membership, benefit, financial, vaccinated, employed, center_link, assistance;

        public HelpingHandHolder(@NonNull View itemView) {
            super(itemView);
            first_name = itemView.findViewById(R.id.admin_helping_first_name);
            last_name = itemView.findViewById(R.id.admin_helping_last_name);
            address = itemView.findViewById(R.id.admin_helping_address);
            email = itemView.findViewById(R.id.admin_helping_email);
            phone = itemView.findViewById(R.id.admin_helping_phone_no);
            untitled = itemView.findViewById(R.id.admin_helping_untitled);
            membership = itemView.findViewById(R.id.admin_helping_membership_no);
            benefit = itemView.findViewById(R.id.admin_helping_benefit_no);
            financial = itemView.findViewById(R.id.admin_helping_radio_financial);
            vaccinated = itemView.findViewById(R.id.admin_helping_radio_vaccinated);
            employed = itemView.findViewById(R.id.admin_helping_radio_employed);
            center_link = itemView.findViewById(R.id.admin_helping_radio_centre_link);
            assistance = itemView.findViewById(R.id.admin_helping_radio_assistance);


        }
    }
}
