package com.example.invisibleillnesses.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.invisibleillnesses.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EditEventActivity extends AppCompatActivity {

    Button button, uploadImage;
    EditText eventName, eventPrice, eventLocation, eventDescription, eventDate;
    String eventViewId, eventViewName, eventViewPrice, eventViewLocation, eventViewDes, eventViewDate, eventViewImg;


    private AwesomeValidation awesomeValidation;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;
    Toolbar toolbar;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    ImageView uploadImageView;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    Boolean imageUploadStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Edit Event");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        button = findViewById(R.id.event_create);
        eventName = findViewById(R.id.edit_event_name);
        eventPrice = findViewById(R.id.edit_event_price);
        eventLocation = findViewById(R.id.edit_event_location);
        eventDate = findViewById(R.id.edit_event_date);
        eventDescription = findViewById(R.id.edit_event_description);
        uploadImage = findViewById(R.id.upload_image);
        uploadImageView = findViewById(R.id.upload_image_view);

        eventViewId = getIntent().getStringExtra("id");
        eventViewName = getIntent().getStringExtra("name");
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
        Picasso.get().load(eventViewImg).into(uploadImageView);

        fStore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.edit_event_name, RegexTemplate.NOT_EMPTY, R.string.valid_event_name);
        awesomeValidation.addValidation(this, R.id.edit_event_price, RegexTemplate.NOT_EMPTY, R.string.valid_event_price);
        awesomeValidation.addValidation(this, R.id.edit_event_location, RegexTemplate.NOT_EMPTY, R.string.valid_event_location);
        awesomeValidation.addValidation(this, R.id.edit_event_date, RegexTemplate.NOT_EMPTY, R.string.valid_event_date);
        awesomeValidation.addValidation(this, R.id.edit_event_description, RegexTemplate.NOT_EMPTY, R.string.valid_event_description);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (awesomeValidation.validate()) {
                        if (imageUploadStatus) {
                            uploadImage();
                        } else {
                            editUploadBox();
                        }
                    }
                } catch (Exception e) {

                }
            }
        });


        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    SelectImage();
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void SelectImage() {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,
                resultCode,
                data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                uploadImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }


    private void uploadImage() {
        if (filePath != null) {

            progressDialog.show();


            StorageReference ref
                    = storageReference
                    .child(
                            "event/"
                                    + UUID.randomUUID().toString());


            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                            try {


                                                String name = eventName.getText().toString();
                                                String price = eventPrice.getText().toString();
                                                String location = eventLocation.getText().toString();
                                                String description = eventDescription.getText().toString();
                                                String eventNewDate = eventDate.getText().toString();


                                                Map<String, Object> productInfo = new HashMap<>();
                                                productInfo.put("id", eventViewId);
                                                productInfo.put("name", name);
                                                productInfo.put("price", price);
                                                productInfo.put("location", location);
                                                productInfo.put("date", eventNewDate);
                                                productInfo.put("description", description);
                                                productInfo.put("photo", uri.toString());

                                                fStore.collection("event").document(eventViewId)
                                                        .set(productInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                progressDialog.dismiss();
                                                                finish();
                                                                Toast.makeText(EditEventActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                progressDialog.dismiss();
                                                                Toast.makeText(EditEventActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });

                                            } catch (Exception e) {

                                            }
                                        }
                                    });

                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Log.i("Message", e.getMessage());
                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(EditEventActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });

        }
    }

    public void editUploadBox() {
        progressDialog.show();
        try {


            String name = eventName.getText().toString();
            String price = eventPrice.getText().toString();
            String location = eventLocation.getText().toString();
            String description = eventDescription.getText().toString();
            String eventNewDate = eventDate.getText().toString();


            Map<String, Object> productInfo = new HashMap<>();
            productInfo.put("id", eventViewId);
            productInfo.put("name", name);
            productInfo.put("price", price);
            productInfo.put("location", location);
            productInfo.put("date", eventNewDate);
            productInfo.put("description", description);
            productInfo.put("photo", eventViewImg);


            fStore.collection("event").document(eventViewId)
                    .set(productInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            finish();
                            Toast.makeText(EditEventActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(EditEventActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (Exception e) {

        }

    }

}