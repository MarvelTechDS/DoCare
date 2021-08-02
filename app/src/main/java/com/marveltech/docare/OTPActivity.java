package com.marveltech.docare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {
    String mobile, verificationCodeBySystem;
    TextView otp_textview;
    EditText otp_edit;
    ProgressDialog progressDialog;
    FirebaseDatabase database ;
    DatabaseReference reference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);
        mobile = getIntent().getStringExtra("number");
        otp_textview = findViewById(R.id.otp_text);
        otp_edit = findViewById(R.id.otp_otp);
        otp_textview.setText("OTP sent to number " + mobile);
        progressDialog = new ProgressDialog(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        sendVerificcationCodetoUser();
        findViewById(R.id.otp_verify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Verifing.....");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                String otp = otp_edit.getText().toString();
                if (!otp.isEmpty()) {
                    verifyCode(otp);
                }
                else {
                    Toast.makeText(OTPActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.otp_resend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificcationCodetoUser();
            }
        });

    }

    private void sendVerificcationCodetoUser() {
        progressDialog.setMessage("Verifing Code...........");
        progressDialog.show();
        progressDialog.setCancelable(false);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+mobile,
                60,
                TimeUnit.SECONDS,
               this,
                mCallbacks
        );
    }
    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationCodeBySystem = s;
            Toast.makeText(OTPActivity.this, "Code Sent Successfully", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code!=null)
            {
                verifyCode(code);
                Toast.makeText(OTPActivity.this, "Code Verified", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OTPActivity.this, ""+e, Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            startActivity(new Intent(OTPActivity.this,LoginActivity.class));
        }
    };

    private void verifyCode(String CodeByUser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem,CodeByUser);
        signInTheUserWthCredential(credential);
    }

    private void signInTheUserWthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(OTPActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(OTPActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            String uid = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                            Query checkid = reference.orderByChild("uid").equalTo(uid);
                            checkid.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists())
                                    {
                                        startActivity(new Intent(OTPActivity.this,HomeActivity2.class));
                                    }
                                    else
                                    {
                                        Intent i = new Intent(OTPActivity.this,SignUpDetails.class);
                                        i.putExtra("mobilenumber",mobile);
                                        startActivity(i);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                });
    }

}