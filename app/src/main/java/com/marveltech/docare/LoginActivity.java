package com.marveltech.docare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
EditText phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phonenumber = findViewById(R.id.login_phonenumber);
        findViewById(R.id.login_loginbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phonenumber.getText().toString();
                if (number.isEmpty() || number.length() < 10) {
                    Toast.makeText(LoginActivity.this, "Incorrect Number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(LoginActivity.this, OTPActivity.class);
                    intent.putExtra("number", number);
                    startActivity(intent);
                }
            }
        });
    }
}