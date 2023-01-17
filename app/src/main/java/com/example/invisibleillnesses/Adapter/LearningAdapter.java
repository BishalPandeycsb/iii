package com.example.invisibleillnesses.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invisibleillnesses.Model.HelpingHandModel;
import com.example.invisibleillnesses.Model.Learning;
import com.example.invisibleillnesses.R;

import java.util.List;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.LearningHolder> {

    Context context;
    private List<Learning> elist;

    public LearningAdapter(Context ct, List<Learning> list) {
        context = ct;
        elist = list;
    }

    @NonNull
    @Override
    public LearningHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_learning, parent, false);
        return new LearningHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningHolder holder, int position) {
        Learning learning = elist.get(position);
        holder.first_name.setText(learning.getFirst_name());
        holder.last_name.setText(learning.getLast_name());
        holder.address.setText(learning.getAddress());
        holder.email.setText(learning.getEmail());
        holder.phone.setText(learning.getPhone_number());
        holder.suburb.setText(learning.getSuburb());
        holder.explain.setText(learning.getExplain());
        holder.birthday.setText(learning.getBirthday());
        holder.child_help.setText(learning.getChild_help());
        holder.comment.setText(learning.getComment());
        holder.gender.setText(learning.getGender());
        holder.who_help.setText(learning.getWho_help());


    }

    @Override
    public int getItemCount() {
        return elist.size();
    }

    public class LearningHolder extends RecyclerView.ViewHolder {


        TextView first_name, last_name, address, email, phone, suburb, explain, birthday, child_help, comment, gender, who_help;

        public LearningHolder(@NonNull View itemView) {
            super(itemView);
            first_name = itemView.findViewById(R.id.admin_learning_first_name);
            last_name = itemView.findViewById(R.id.admin_learning_last_name);
            address = itemView.findViewById(R.id.admin_learning_address);
            email = itemView.findViewById(R.id.admin_learning_email);
            phone = itemView.findViewById(R.id.admin_learning_phone_no);
            suburb = itemView.findViewById(R.id.admin_learning_suburb);
            explain = itemView.findViewById(R.id.admin_learning_explain);
            birthday = itemView.findViewById(R.id.admin_learning_birthday);
            child_help = itemView.findViewById(R.id.admin_learning_child_help);
            comment = itemView.findViewById(R.id.admin_learning_comment);
            gender = itemView.findViewById(R.id.admin_learning_gender);
            who_help = itemView.findViewById(R.id.admin_helping_who_help);

        }
    }
}
