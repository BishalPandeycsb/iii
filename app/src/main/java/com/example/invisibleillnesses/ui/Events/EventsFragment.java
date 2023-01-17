package com.example.invisibleillnesses.ui.Events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.invisibleillnesses.Adapter.EventAdapter;
import com.example.invisibleillnesses.Adapter.HelpingHandAdapter;
import com.example.invisibleillnesses.Model.EventModel;
import com.example.invisibleillnesses.R;
import com.example.invisibleillnesses.databinding.FragmentEventsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventsFragment extends Fragment {

    private FragmentEventsBinding binding;
    RecyclerView recyclerView;

    FirebaseFirestore fStore;
    HashMap<String, String> hashMap;
    private List<EventModel> list;
    EventAdapter eventAdapter;
    RecyclerView.LayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EventsViewModel eventsViewModel =
                new ViewModelProvider(this).get(EventsViewModel.class);

        binding = FragmentEventsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.event_fragment_recycler_view);

        fStore = FirebaseFirestore.getInstance();
        hashMap = new HashMap<>();


        list = new ArrayList<>();


        getEventData();

        return root;
    }

    public void getEventData() {
        fStore.collection("event").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                for (DocumentSnapshot snapshot : task.getResult()) {
                    EventModel eventModel = new EventModel(snapshot.getString("id"), snapshot.getString("name"), snapshot.getString("price"), snapshot.getString("location"), snapshot.getString("date"), snapshot.getString("description"), snapshot.getString("photo"));
                    list.add(eventModel);
                    eventAdapter = new EventAdapter(getContext(), list, true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(eventAdapter);
                }
//                eventAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}