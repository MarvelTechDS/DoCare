package com.marveltech.docare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

import in.dd4you.appsconfig.DD4YouConfig;

public class OxygenSecondActivity extends AppCompatActivity {
TextView sname,avail,location,phone,address;
DatabaseReference reference,click_reference;
String id="";
int c;
DD4YouConfig dd4YouConfig;
String click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygen_second);
        sname = findViewById(R.id.oxygenSecond_hname);
        avail = findViewById(R.id.oxygenSecond_avail);
        location = findViewById(R.id.oxygenSecond_location);
        phone = findViewById(R.id.oxygenSecond_phone);
        address = findViewById(R.id.oxygenSecond_address);
        dd4YouConfig = new DD4YouConfig(this);

        sname.setText(getIntent().getStringExtra("oxygensupplyname"));
        avail.setText(getIntent().getStringExtra("avail"));
        location.setText(getIntent().getStringExtra("location"));
        phone.setText(getIntent().getStringExtra("phonenumber"));
        address.setText(getIntent().getStringExtra("address"));
        id = getIntent().getStringExtra("id");
        updateClick();
        findViewById(R.id.oxygenSecond_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:"+getIntent().getStringExtra("phonenumber");
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(uri));
                startActivity(i);
            }
        });

    }

    private void updateClick() {
        reference = FirebaseDatabase.getInstance().getReference("oxygen_views");
        click = dd4YouConfig.generateUniqueID(5);
        clickHelperClass helperClass = new clickHelperClass(click,id);
        reference.child(click).setValue(helperClass);
    }
}