package com.example.invisibleillnesses.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.invisibleillnesses.Model.Mentoring;
import com.example.invisibleillnesses.R;

import java.util.List;

public class MentoringAdapter extends RecyclerView.Adapter<MentoringAdapter.MentoringHolder> {
    Context context;
    private List<Mentoring> elist;

    public MentoringAdapter(Context ct, List<Mentoring> list) {
        context = ct;
        elist = list;
    }

    @NonNull
    @Override
    public MentoringHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_mentoring, parent, false);
        return new MentoringHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentoringHolder holder, int position) {
        Mentoring mentoring = elist.get(position);
        holder.username.setText(mentoring.getFirst_name());
        holder.suburb.setText(mentoring.getSuburb());
        holder.email.setText(mentoring.getEmail());
        holder.phone.setText(mentoring.getPhone_number());
        holder.untitled.setText(mentoring.getUntitled());

    }

    @Override
    public int getItemCount() {
        return elist.size();
    }

    public class MentoringHolder extends RecyclerView.ViewHolder {
        TextView username, suburb, email, phone, untitled;

        public MentoringHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.admin_mentoring_user_name);
            suburb = itemView.findViewById(R.id.admin_mentoring_suburb);
            email = itemView.findViewById(R.id.admin_mentoring_email);
            phone = itemView.findViewById(R.id.admin_mentoring_phone_no);
            untitled = itemView.findViewById(R.id.admin_mentoring_untitled);
        }
    }
}
