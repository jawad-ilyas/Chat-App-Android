package com.example.chatsapppart1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chatsapppart1.databinding.ActivityProfileSetupLibraryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;

public class profileSetupLibrary extends AppCompatActivity {


    // 1- step is create binding obejcts
    private ActivityProfileSetupLibraryBinding binding;

    // 3- step is create objects of the firbase like auth , storage , database

    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;


    // 8- create uri object because we want to store reference of the image (Uniform Resource Identifier)
    Uri selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2- step is set layout inflater and set content view
        binding = ActivityProfileSetupLibraryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // 4- step provide a reference of the object
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();



        // 10 create the progress bar for loadin sectoin


        // 5- after clicked on button get the image so
        binding.profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // 6- create a intent because we need to open the gallery so also known as inteneral intent
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);

                // also need to set type because we are not move from one one activity to other
                intent.setType("image/*");

                // because we dont close the activity so we used startActivityForResult()
                startActivityForResult(intent,45);
            }
        });

        // 9- after clicked on button we need to insert data into data base of the firebase
        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.etUserName.getText().toString().trim();





                Toast.makeText(profileSetupLibrary.this, "Button is Clicked", Toast.LENGTH_SHORT).show();

                if(name.isEmpty())
                {
                    binding.etUserName.setError("Please Type a Name");
                }

                if(selectedImage != null)
                {



                    Toast.makeText(profileSetupLibrary.this, "Image iS Selected ", Toast.LENGTH_SHORT).show();

                    // StoreageReference -- get reference of the root folder of the fire base
                    // child("Profiles") -- means go to this folder (profile is my folder name you need to enter your folder name )
                    // child(auth.getUid) -- fetched the id of the login / verifed user
                    StorageReference reference = storage.getReference().child("Profiles").child(auth.getUid());

                    reference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful())
                            {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        String imageUrl = uri.toString();
                                        String uid = auth.getUid();
                                        String name = binding.etUserName.getText().toString().trim();
                                        String PhoneNumber = auth.getCurrentUser().getPhoneNumber();
                                        User user   = new User(name ,imageUrl,uid,PhoneNumber);
                                        database.getReference()
                                                .child("users")
                                                .child(uid)
                                                .setValue(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(profileSetupLibrary.this, "new data is inserted ", Toast.LENGTH_SHORT).show();

                                                        Intent intent = new Intent(profileSetupLibrary.this, MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                });
                                    }
                                });
                            }
                        }
                    });
                }

                else
                {




                    String uid = auth.getUid();
                    String PhoneNumber = auth.getCurrentUser().getPhoneNumber();
                    User user   = new User(name ,"no_image",uid,PhoneNumber);
                    database.getReference()
                            .child("users")
                            .child(uid)
                            .setValue(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(profileSetupLibrary.this, "new data is inserted ", Toast.LENGTH_SHORT).show();


                                    Intent intent = new Intent(profileSetupLibrary.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                }
            }
        });





    }


    // 7- after we get the data from the activity and onActivityResult give us data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(data != null)
        {
            if(data.getData() != null)
            {
                binding.profilePicture.setImageURI(data.getData());

                selectedImage = data.getData();
            }
        }
    }
}