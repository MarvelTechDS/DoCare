package com.marveltech.docare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import in.dd4you.appsconfig.DD4YouConfig;

public class BedSecondActivity extends AppCompatActivity {
TextView hospitalname_tv,bedsAvail_tv,location_tv;
EditText pname_et,page_et,pno_et;
RadioGroup gender_radiobtn;
String pname,page,gender,uid,phone,hospitalname,location;
int bedsavailability;
String refernceId;
Dialog dialog;
DD4YouConfig dd4YouConfig;
ProgressDialog progressDialog;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_second);
        hospitalname_tv = findViewById(R.id.bedSecond_hname);
        bedsAvail_tv = findViewById(R.id.bedSecond_avail);
        pname_et = findViewById(R.id.bedSecond_pname);
        page_et = findViewById(R.id.bedSecond_page);
        pno_et = findViewById(R.id.bedSecond_pnumber);
        location_tv = findViewById(R.id.bedSecond_location);
        gender_radiobtn = findViewById(R.id.bedSecond_gender);
        dialog = new Dialog(BedSecondActivity.this);
        dd4YouConfig = new DD4YouConfig(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Booking Bed Please Wait!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Booking......");

        hospitalname = getIntent().getStringExtra("hospitalname");
        bedsavailability = getIntent().getIntExtra("availability",0);
        location = getIntent().getStringExtra("location");
        hospitalname_tv.setText(hospitalname);
        bedsAvail_tv.setText(bedsavailability);
        location_tv.setText(location);

        findViewById(R.id.bedSecond_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                getGender();
                if (bedsavailability != 0) {
                    if (!TextUtils.isEmpty(pname_et.getText().toString())
                            && !TextUtils.isEmpty(page_et.getText().toString())
                            && !TextUtils.isEmpty(pno_et.getText().toString())
                            && !TextUtils.isEmpty(gender)) {
                        dialog.setContentView(R.layout.customdialog_success);
                        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.successdialog_background));
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog.setCancelable(false);
                        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                        TextView ok = dialog.findViewById(R.id.ok_customdialog_success);
                        getReferenceId();
                        pname = pname_et.getText().toString();
                        page = page_et.getText().toString();
                        phone = pno_et.getText().toString();
                        uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                        BedBookingHelperClass BBhelperClass = new BedBookingHelperClass(pname,page,phone,uid,refernceId,hospitalname,location);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("BedBooking");
                        reference.child(refernceId).setValue(BBhelperClass);
                        TextView id = dialog.findViewById(R.id.orderid_customdialog_success);
                        id.setText(refernceId);
                        progressDialog.dismiss();
                        dialog.show();
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(BedSecondActivity.this, HomeActivity2.class));
                                dialog.dismiss();
                            }
                        });
                    } else {
                        Toast.makeText(BedSecondActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    progressDialog.dismiss();
                    dialog.setContentView(R.layout.customdialog_failure);
                    dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.successdialog_background));
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.setCancelable(false);
                    dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                    TextView ok = dialog.findViewById(R.id.ok_customdialog_success);
                    dialog.show();
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(BedSecondActivity.this,BedsActivity.class));
                            dialog.dismiss();
                        }
                    });
                }
            }
        });
    }

    private void getReferenceId() {
        refernceId = dd4YouConfig.generateUniqueID(9);
    }

    private void getGender() {
       if (gender_radiobtn.getId() == R.id.male)
           gender = "male";
       else
           gender = "female";

    }
}