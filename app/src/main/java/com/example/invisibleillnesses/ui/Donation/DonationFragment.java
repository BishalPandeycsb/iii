package com.example.invisibleillnesses.ui.Donation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.invisibleillnesses.Pages.ProductFormActivity;
import com.example.invisibleillnesses.R;
import com.example.invisibleillnesses.Stripe.AcceptedPaymentMainActivity;
import com.example.invisibleillnesses.Stripe.StripeCheckoutActivity;
import com.example.invisibleillnesses.databinding.FragmentDonationBinding;
import com.example.invisibleillnesses.databinding.FragmentEventsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DonationFragment extends Fragment {

    private FragmentDonationBinding binding;
    private AwesomeValidation awesomeValidation;
    EditText first_name, last_name, street_name, apartment_suite, suburb, state, post_code, phone, email_address;
    Button orderSubmitBtn;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;
    RadioGroup radioGroup;
    int donationAmt = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DonationViewModel donationViewModel =
                new ViewModelProvider(this).get(DonationViewModel.class);

        binding = FragmentDonationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        first_name = root.findViewById(R.id.donation_first_name);
        last_name = root.findViewById(R.id.donation_last_name);
        street_name = root.findViewById(R.id.donation_address);
        apartment_suite = root.findViewById(R.id.donation_apartment_suite);
        suburb = root.findViewById(R.id.donation_suburb);
        state = root.findViewById(R.id.donation_state);
        post_code = root.findViewById(R.id.donation_post_code);
        phone = root.findViewById(R.id.donation_phone);
        email_address = root.findViewById(R.id.donation_email);
        orderSubmitBtn = root.findViewById(R.id.donation_submit);
        radioGroup = root.findViewById(R.id.donation_price);
        fStore = FirebaseFirestore.getInstance();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton
                        radioButton
                        = (RadioButton) radioGroup.findViewById(i);
            }
        });

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(getActivity(), R.id.donation_first_name, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(getActivity(), R.id.donation_last_name, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(getActivity(), R.id.donation_address, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(getActivity(), R.id.donation_apartment_suite, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(getActivity(), R.id.donation_suburb, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(getActivity(), R.id.donation_state, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(getActivity(), R.id.donation_post_code, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(getActivity(), R.id.donation_phone, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        awesomeValidation.addValidation(getActivity(), R.id.donation_email, RegexTemplate.NOT_EMPTY, R.string.valid_required);
        orderSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    orderFormSubmit();
                }

            }
        });

        return root;
    }

    public void orderFormSubmit() {
        try {

            String firstName = first_name.getText().toString();
            String lastName = last_name.getText().toString();
            String streetName = street_name.getText().toString();
            String apartmentStatus = apartment_suite.getText().toString();
            String suburbValue = suburb.getText().toString();
            String postcode = post_code.getText().toString();
            String phoneValue = phone.getText().toString();
            String emailAddress = email_address.getText().toString();
            String id = UUID.randomUUID().toString();

            int financialSelectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton financialButton = (RadioButton) radioGroup.findViewById(financialSelectedId);

            Log.i("Donation", financialButton.getText().toString());

            if (financialButton.getText().equals("Day")) {
                donationAmt = 25;
            } else if (financialButton.getText().equals("Monthly")) {
                donationAmt = 30;
            } else if (financialButton.getText().equals("Yearly")) {
                donationAmt = 50;
            }



            Intent i = new Intent(getContext(), AcceptedPaymentMainActivity.class);
            i.putExtra("id", id);
            i.putExtra("first_name", firstName);
            i.putExtra("last_name", lastName);
            i.putExtra("street_name", streetName);
            i.putExtra("apartment_status", apartmentStatus);
            i.putExtra("suburb_value", suburbValue);
            i.putExtra("post_code", postcode);
            i.putExtra("phone_value", phoneValue);
            i.putExtra("email_address", emailAddress);
            i.putExtra("donation_price", String.valueOf(donationAmt));
            startActivity(i);



        } catch (Exception e) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        binding = null;
    }
}