package com.example.invisibleillnesses.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.*;
import android.view.View.OnClickListener;
import com.example.invisibleillnesses.R;
import com.example.invisibleillnesses.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements OnClickListener {
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Button btn_learn;
        ImageView icon_fb, icon_meta, icon_link;
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn_learn = (Button) root.findViewById(R.id.btn_learn);
        btn_learn.setOnClickListener(this);

        icon_fb = (ImageView) root.findViewById(R.id.icon_fb);
        icon_fb.setOnClickListener(this);

        icon_meta = (ImageView) root.findViewById(R.id.icon_meta);
        icon_meta.setOnClickListener(this);

        icon_link = (ImageView) root.findViewById(R.id.icon_link);
        icon_link.setOnClickListener(this);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_learn: {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://invisibleillnesses.org.au/who-we-are/"));
                startActivity(intent);
            }
            case R.id.icon_fb: {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/invisibleillnessesinc/"));
                startActivity(intent);
            }
            case R.id.icon_meta: {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.instagram.com/invisibleillneseswa/"));
                startActivity(intent);
            }
            case R.id.icon_link: {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/in/invisibleillnesses/"));
                startActivity(intent);
            }

        }
    }
}