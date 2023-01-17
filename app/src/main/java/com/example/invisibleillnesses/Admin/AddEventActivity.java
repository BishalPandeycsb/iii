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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddEventActivity extends AppCompatActivity {

    Button button, uploadImage;
    EditText eventName, eventPrice, eventLocation, eventDescription,eventDate;

    private AwesomeValidation awesomeValidation;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;
    Toolbar toolbar;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    ImageView uploadImageView;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Add Event");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        button = findViewById(R.id.event_create);
        eventName = findViewById(R.id.event_name);
        eventPrice = findViewById(R.id.event_price);
        eventLocation = findViewById(R.id.event_location);
        eventDate = findViewById(R.id.event_date);
        eventDescription = findViewById(R.id.event_description);


        uploadImage = findViewById(R.id.upload_image);
        uploadImageView = findViewById(R.id.upload_image_view);

        fStore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.event_name, RegexTemplate.NOT_EMPTY, R.string.valid_event_name);
        awesomeValidation.addValidation(this, R.id.event_price, RegexTemplate.NOT_EMPTY, R.string.valid_event_price);
        awesomeValidation.addValidation(this, R.id.event_location, RegexTemplate.NOT_EMPTY, R.string.valid_event_location);
        awesomeValidation.addValidation(this, R.id.event_date, RegexTemplate.NOT_EMPTY, R.string.valid_event_date);
        awesomeValidation.addValidation(this, R.id.event_description, RegexTemplate.NOT_EMPTY, R.string.valid_event_description);

        fStore = FirebaseFirestore.getInstance();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (awesomeValidation.validate()) {
                        uploadImage();
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

                                                String id = UUID.randomUUID().toString();


                                                Map<String, Object> productInfo = new HashMap<>();
                                                productInfo.put("id", id);
                                                productInfo.put("name", name);
                                                productInfo.put("price", price);
                                                productInfo.put("location", location);
                                                productInfo.put("date", eventNewDate);
                                                productInfo.put("description", description);
                                                productInfo.put("photo", uri.toString());

                                                fStore.collection("event").document(id)
                                                        .set(productInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                progressDialog.dismiss();
                                                                finish();
                                                                Toast.makeText(AddEventActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                progressDialog.dismiss();
                                                                Toast.makeText(AddEventActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
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
                                    .makeText(AddEventActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });

        }
    }

}