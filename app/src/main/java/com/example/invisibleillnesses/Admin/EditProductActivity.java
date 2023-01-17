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

public class EditProductActivity extends AppCompatActivity {

    Button button, uploadImage;
    EditText productName, productPrice, productDesigner, productSize, productRefundable, productWeekend, productShortDes, productDescription;
    String productViewId, productViewName, productViewPrice, productViewDesigner, productViewSize, productViewRefundable, productViewWeekend, productViewShortDes, productViewDes, productViewImg;


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
        setContentView(R.layout.activity_edit_product);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Edit Product");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        button = findViewById(R.id.edit_product_create);
        productName = findViewById(R.id.edit_product_name);
        productPrice = findViewById(R.id.edit_product_price);
        productDesigner = findViewById(R.id.edit_product_designer);
        productSize = findViewById(R.id.edit_product_size);
        productRefundable = findViewById(R.id.edit_product_refundable_deposit);
        productWeekend = findViewById(R.id.edit_product_weekend_hire);
        productShortDes = findViewById(R.id.edit_product_short_description);
        productDescription = findViewById(R.id.edit_product_description);
        uploadImage = findViewById(R.id.edit_upload_image);
        uploadImageView = findViewById(R.id.edit_upload_image_view);


        productViewId = getIntent().getStringExtra("id");
        productViewName = getIntent().getStringExtra("name");
        productViewPrice = getIntent().getStringExtra("price");
        productViewDesigner = getIntent().getStringExtra("designer");
        productViewSize = getIntent().getStringExtra("size");
        productViewRefundable = getIntent().getStringExtra("refundable");
        productViewWeekend = getIntent().getStringExtra("weekend");
        productViewShortDes = getIntent().getStringExtra("shortDes");
        productViewDes = getIntent().getStringExtra("des");
        productViewImg = getIntent().getStringExtra("photo");


        productName.setText(productViewName);
        productPrice.setText(productViewPrice);
        productDesigner.setText(productViewDesigner);
        productSize.setText(productViewSize);
        productRefundable.setText(productViewRefundable);
        productWeekend.setText(productViewWeekend);
        productShortDes.setText(productViewShortDes);
        productDescription.setText(productViewDes);
        Picasso.get().load(productViewImg).into(uploadImageView);

        fStore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.edit_product_name, RegexTemplate.NOT_EMPTY, R.string.valid_product_name);
        awesomeValidation.addValidation(this, R.id.edit_product_price, RegexTemplate.NOT_EMPTY, R.string.valid_product_price);
        awesomeValidation.addValidation(this, R.id.edit_product_designer, RegexTemplate.NOT_EMPTY, R.string.valid_product_price);
        awesomeValidation.addValidation(this, R.id.edit_product_size, RegexTemplate.NOT_EMPTY, R.string.valid_product_price);
        awesomeValidation.addValidation(this, R.id.edit_product_refundable_deposit, RegexTemplate.NOT_EMPTY, R.string.valid_product_price);
        awesomeValidation.addValidation(this, R.id.edit_product_weekend_hire, RegexTemplate.NOT_EMPTY, R.string.valid_product_price);
        awesomeValidation.addValidation(this, R.id.edit_product_short_description, RegexTemplate.NOT_EMPTY, R.string.valid_product_price);
        awesomeValidation.addValidation(this, R.id.edit_product_description, RegexTemplate.NOT_EMPTY, R.string.valid_product_price);

        fStore = FirebaseFirestore.getInstance();


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
                            "images/"
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
                                                String name = productName.getText().toString();
                                                String price = productPrice.getText().toString();
                                                String designer = productDesigner.getText().toString();
                                                String size = productSize.getText().toString();
                                                String refundable = productRefundable.getText().toString();
                                                String weekend = productWeekend.getText().toString();
                                                String shortDescription = productShortDes.getText().toString();
                                                String description = productDescription.getText().toString();


                                                Map<String, Object> productInfo = new HashMap<>();
                                                productInfo.put("id", productViewId);
                                                productInfo.put("name", name);
                                                productInfo.put("price", price);
                                                productInfo.put("designer", designer);
                                                productInfo.put("size", size);
                                                productInfo.put("refundable", refundable);
                                                productInfo.put("weekend_hire", weekend);
                                                productInfo.put("short_description", shortDescription);
                                                productInfo.put("description", description);
                                                productInfo.put("photo", uri.toString());

                                                fStore.collection("product").document(productViewId)
                                                        .set(productInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                progressDialog.dismiss();
                                                                finish();
                                                                Toast.makeText(EditProductActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Toast.makeText(EditProductActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
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
                                    .makeText(EditProductActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });

        }
    }

    public void editUploadBox() {
        progressDialog.show();
        String name = productName.getText().toString();
        String price = productPrice.getText().toString();
        String designer = productDesigner.getText().toString();
        String size = productSize.getText().toString();
        String refundable = productRefundable.getText().toString();
        String weekend = productWeekend.getText().toString();
        String shortDescription = productShortDes.getText().toString();
        String description = productDescription.getText().toString();

        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("id", productViewId);
        productInfo.put("name", name);
        productInfo.put("price", price);
        productInfo.put("designer", designer);
        productInfo.put("size", size);
        productInfo.put("refundable", refundable);
        productInfo.put("weekend_hire", weekend);
        productInfo.put("short_description", shortDescription);
        productInfo.put("description", description);
        productInfo.put("photo", productViewImg);

        fStore.collection("product").document(productViewId)
                .set(productInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        finish();
                        Toast.makeText(EditProductActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProductActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}