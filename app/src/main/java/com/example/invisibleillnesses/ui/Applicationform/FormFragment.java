package com.example.invisibleillnesses.ui.Applicationform;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.invisibleillnesses.Form.HelpingHandActivity;
import com.example.invisibleillnesses.Form.LearningActivity;
import com.example.invisibleillnesses.Form.MentorActivity;
import com.example.invisibleillnesses.R;
import com.example.invisibleillnesses.databinding.FragmentFormBinding;
import com.example.invisibleillnesses.dataholder;
import com.example.invisibleillnesses.ui.Contactus.ContactusFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    RelativeLayout helpingHand, mentoring, learning, hairDresser, nailTech;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        FormViewModel formViewModel =
                new ViewModelProvider(this).get(FormViewModel.class);

        binding = FragmentFormBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        helpingHand = view.findViewById(R.id.helping_hand);
        mentoring = view.findViewById(R.id.mentoring);
        learning = view.findViewById(R.id.learning);
        hairDresser = view.findViewById(R.id.hairdresser);
        nailTech = view.findViewById(R.id.nail_tech);

        helpingHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpingIntent = new Intent(getActivity(), HelpingHandActivity.class);
                getActivity().startActivity(helpingIntent);
            }
        });

        mentoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mentoringIntent = new Intent(getContext(), MentorActivity.class);
                mentoringIntent.putExtra("titleName", "Mentoring");
                startActivity(mentoringIntent);
            }
        });


        learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent learningIntent = new Intent(getContext(), LearningActivity.class);
                startActivity(learningIntent);
            }
        });

        hairDresser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mentoringIntent = new Intent(getContext(), MentorActivity.class);
                mentoringIntent.putExtra("titleName", "Hair Dresser");
                startActivity(mentoringIntent);
            }
        });

        nailTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mentoringIntent = new Intent(getContext(), MentorActivity.class);
                mentoringIntent.putExtra("titleName", "Nail Tech");
                startActivity(mentoringIntent);
            }
        });


        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }


}


