package com.marveltech.docare;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class HomeActivity2 extends AppCompatActivity {
FirebaseAuth firebaseAuth;
    private static final int INTERNET_CODE_PERMISSION = 100;
    private static final int CALL_CODE_PERMISSION = 101;
    private static final int LOCATION_CODE_PERMISSION = 102;
    private static final int FOREGROUND_CODE_PERMISSION = 103;
    public static String contactnumber1,contactnumber2,contactnumber3;
    FirebaseDatabase database ;
    DatabaseReference reference ;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();
//        startService(new Intent(getApplicationContext(),BackgroundService.class));
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.INTERNET,Manifest.permission.FOREGROUND_SERVICE,Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION,Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS},
                PackageManager.PERMISSION_GRANTED);
                findViewById(R.id.home_oxygen).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(HomeActivity2.this,OxygenActivity.class));
                    }
                });
                findViewById(R.id.home_beds).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(HomeActivity2.this,BedsActivity.class));
                    }
                });
//        if (ContextCompat.checkSelfPermission(HomeActivity2.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
//        {
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},INTERNET_CODE_PERMISSION);
//        }
//        if (ContextCompat.checkSelfPermission(HomeActivity2.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
//        {
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},CALL_CODE_PERMISSION);
//        }
//        if (ContextCompat.checkSelfPermission(HomeActivity2.this, Manifest.permission.FOREGROUND_SERVICE) != PackageManager.PERMISSION_GRANTED)
//        {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.FOREGROUND_SERVICE},FOREGROUND_CODE_PERMISSION);
//            }
//        }
//        if (ContextCompat.checkSelfPermission(HomeActivity2.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED)
//        {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},LOCATION_CODE_PERMISSION);
//            }
//        }
    }

    private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null)
        {
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("Users");
            String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
            Query getDetails = reference.orderByChild("uid").equalTo(uid);
            getDetails.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists())
                    {
                        contactnumber1 = snapshot.child(uid).child("number1").getValue(String.class);
                        contactnumber2 = snapshot.child(uid).child("number2").getValue(String.class);
                        contactnumber3 = snapshot.child(uid).child("number3").getValue(String.class);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_signout: {
                firebaseAuth.signOut();
                startActivity(new Intent(HomeActivity2.this, LoginActivity.class));
                return true;
            }
            case R.id.menu_settings: {
                startActivity(new Intent(HomeActivity2.this,SettingsActivity.class));
                return true;
            }
            case R.id.menu_about: {
//                startActivity(new Intent(HomeActivity2.this,AboutActivity.class));
                return true;
            }
            case R.id.menu_help: {
//                startActivity(new Intent(HomeActivity2.this,HelpActivity.class));
                return true;
            }
            default:
                return true;
        }

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == INTERNET_CODE_PERMISSION)
//        {
//            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//                Toast.makeText(this, "Internet Permission Granted", Toast.LENGTH_SHORT).show();
//            }
//        }
//        if (requestCode == CALL_CODE_PERMISSION)
//        {
//            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//                Toast.makeText(this, "Call Phone Permission Granted", Toast.LENGTH_SHORT).show();
//            }
//        }
//        if (requestCode == LOCATION_CODE_PERMISSION)
//        {
//            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//                Toast.makeText(this, "Location Permission Granted", Toast.LENGTH_SHORT).show();
//            }
//        }
//        if (requestCode == FOREGROUND_CODE_PERMISSION)
//        {
//            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//                Toast.makeText(this, "Background Permission Granted", Toast.LENGTH_SHORT).show();
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }


}