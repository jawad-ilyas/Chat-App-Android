package com.example.chatsapppart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chatsapppart1.databinding.ActivityPhoneBinding;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneActivity extends AppCompatActivity {
    private  com.example.chatsapppart1.databinding.ActivityPhoneBinding binding;



    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // other way of the bind is like this
//        setContentView(binding.getRoot());



        // this step is used for checked if user login or not
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null)
        {
            Intent intent = new Intent(PhoneActivity.this , MainActivity.class);
            startActivity(intent);
            finish();
        }


        binding.etPhPhone.requestFocus();


        // 1- step is get data from the phone activity after clicked on button

        binding.btnPhSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String PhoneNumber = binding.etPhPhone.getText().toString().trim();




                if(PhoneNumber.isEmpty())
                {
                    Toast.makeText(PhoneActivity.this, "Please Enter Phone Number ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), OTPverify.class);
                    intent.putExtra("PhoneNumber", PhoneNumber);
                    startActivity(intent);
                }
            }
        });
    }
}