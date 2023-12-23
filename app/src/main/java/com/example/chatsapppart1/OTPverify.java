package com.example.chatsapppart1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chatsapppart1.databinding.ActivityOtpverifyBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class OTPverify extends AppCompatActivity {



    private ActivityOtpverifyBinding binding;

    FirebaseAuth auth;

    String VerificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOtpverifyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        ProgressBar progressBar = binding.progressBar;
        binding.etPhPhone.setEnabled(false);
        binding.btnPhSubmit.setEnabled(false);


        Intent getIntent = getIntent();

        String PhoneNumber = getIntent.getStringExtra("PhoneNumber");

        binding.tvPhoneNumber.setText(PhoneNumber);

        auth = FirebaseAuth.getInstance();
        // Show ProgressBar before starting authentication
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(PhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(OTPverify.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String verifyId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verifyId, forceResendingToken);


                        // Hide ProgressBar after authentication completes (whether success or failure)
                        progressBar.setVisibility(View.GONE);
                        binding.btnPhSubmit.setEnabled(true);
                        binding.etPhPhone.setEnabled(true);


                        VerificationId = verifyId;
                    }
                }).build();


            PhoneAuthProvider.verifyPhoneNumber(options);


            String Otp =  binding.etPhPhone.getText().toString().trim();

        binding.btnPhSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Otp = binding.etPhPhone.getText().toString().trim();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId, Otp);

                auth.signInWithCredential(credential).addOnCompleteListener(OTPverify.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Authentication success
//                            Log.d("TAG", "signInWithCredential:success");

                            // You can get the authenticated user
//                            FirebaseUser user = task.getResult().getUser();
                            Toast.makeText(OTPverify.this, "Verify ", Toast.LENGTH_SHORT).show();

                            // Proceed with the authenticated user
                            // For example, you can navigate to another activity
//                            Intent intent = new Intent(OTPverify.this, PhoneActivity.class);
//                            startActivity(intent);
//                            finish(); // Optional: Close current activity after successful authentication
                        } else {
                            // Authentication failed
//                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(OTPverify.this, "Failed ", Toast.LENGTH_SHORT).show();
                            // Handle failed authentication, display error message, etc.
                            // For example, show an error message to the user
                            // Toast.makeText(OTPverify.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}
