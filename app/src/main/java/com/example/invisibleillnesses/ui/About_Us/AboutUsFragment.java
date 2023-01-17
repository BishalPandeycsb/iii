package com.example.invisibleillnesses.ui.About_Us;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.View.OnClickListener;
import com.example.invisibleillnesses.R;
import com.example.invisibleillnesses.databinding.FragmentAboutusBinding;
import com.example.invisibleillnesses.ui.About_Us.AboutUsViewModel;

public class AboutUsFragment extends Fragment implements OnClickListener{

    private FragmentAboutusBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TextView ourhistory, whatwedo, ourteam, advocacy,governance;
        AboutUsViewModel aboutUsViewModel=
                new ViewModelProvider(this).get(AboutUsViewModel.class);

        binding = FragmentAboutusBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ourhistory = (TextView) root.findViewById(R.id.ourhistory);
        ourhistory.setOnClickListener(this);

        whatwedo =(TextView) root.findViewById(R.id.whatwedo);
        whatwedo.setOnClickListener(this);

        ourteam =(TextView) root.findViewById(R.id.ourteam);
        ourteam.setOnClickListener(this);

        advocacy =(TextView) root.findViewById(R.id.advocacy);
        advocacy.setOnClickListener(this);

        governance =(TextView) root.findViewById(R.id.governance);
        governance.setOnClickListener(this);

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
            case R.id.ourhistory: {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://invisibleillnesses.org.au/who-we-are/our-history/"));
                startActivity(intent);
            }
            case R.id.whatwedo: {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://invisibleillnesses.org.au/who-we-are/"));
                startActivity(intent);
            }
            case R.id.ourteam: {
                    Intent intent= new  Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://invisibleillnesses.org.au/who-we-are/our-team/"));
                    startActivity(intent);
            }
            case R.id.advocacy: {
                Intent intent= new  Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://invisibleillnesses.org.au/advocacy/"));
                startActivity(intent);
            }
            case R.id.governance: {
                Intent intent= new  Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://invisibleillnesses.org.au/who-we-are/governance/"));
                startActivity(intent);
            }
        }
    }
}