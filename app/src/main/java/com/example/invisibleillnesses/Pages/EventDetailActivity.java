package com.example.invisibleillnesses.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.invisibleillnesses.R;
import com.squareup.picasso.Picasso;

public class EventDetailActivity extends AppCompatActivity {

    ImageView imageView;
    Button ticketBtn;
    TextView eventName, eventLocation, eventPrice, eventDate, eventDescription;
    String eventViewId, eventViewName, eventViewPrice, eventViewLocation, eventViewDes, eventViewDate, eventViewImg;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        eventViewName = getIntent().getStringExtra("name");
        toolbar.setTitle(eventViewName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageView = findViewById(R.id.event_detail_image);
        ticketBtn = findViewById(R.id.event_book_button);
        eventName = findViewById(R.id.event_detail_title);
        eventLocation = findViewById(R.id.event_detail_location);
        eventPrice = findViewById(R.id.event_detail_ticket);
        eventDate = findViewById(R.id.event_detail_date);
        eventDescription = findViewById(R.id.event_detail_description);

        eventViewId = getIntent().getStringExtra("id");
        eventViewPrice = getIntent().getStringExtra("price");
        eventViewLocation = getIntent().getStringExtra("location");
        eventViewDate = getIntent().getStringExtra("date");
        eventViewDes = getIntent().getStringExtra("des");
        eventViewImg = getIntent().getStringExtra("photo");

        eventName.setText(eventViewName);
        eventPrice.setText(eventViewPrice);
        eventLocation.setText(eventViewLocation);
        eventDate.setText(eventViewDate);
        eventDescription.setText(eventViewDes);
        Picasso.get().load(eventViewImg).into(imageView);


        ticketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EventDetailActivity.this, EventFormActivity.class);
                i.putExtra("id", eventViewId);
                i.putExtra("name", eventViewName);
                i.putExtra("price", eventViewPrice);
                i.putExtra("location", eventViewLocation);
                i.putExtra("date", eventViewDate);
                i.putExtra("des", eventViewDes);
                i.putExtra("photo", eventViewImg);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}