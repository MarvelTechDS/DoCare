package com.marveltech.docare;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.tech.NfcB;
import android.os.Bundle;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
TextView nu1,nu2,nu3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        nu1 = findViewById(R.id.settings_n1);
        nu2 = findViewById(R.id.settings_n2);
        nu3 = findViewById(R.id.settings_n3);
        nu1.setText(HomeActivity2.contactnumber1);
        nu2.setText(HomeActivity2.contactnumber2);
        nu3.setText(HomeActivity2.contactnumber3);
    }
}