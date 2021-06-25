package com.marveltech.docare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorInfo;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {
    String mobile, verificationCodeBySystem;
    TextView otp_textview;
    EditText otp_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);
        mobile = getIntent().getStringExtra("number");
        otp_textview = findViewById(R.id.otp_text);
        otp_edit = findViewById(R.id.otp_otp);
        otp_textview.setText("OTP sent to number " + mobile);
        sendVerificcationCodetoUser();
        findViewById(R.id.otp_verify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code!=null)
            {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OTPActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                            startActivity(new Intent(getApplicationContext(),HomeActivity2.class));

                        }
                    }
                });
    }
}