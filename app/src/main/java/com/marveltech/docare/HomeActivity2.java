package com.marveltech.docare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity2 extends AppCompatActivity {
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();
    }

    private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null)
        {
            String phone = firebaseUser.getPhoneNumber();
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
//                startActivity(new Intent(HomeActivity2.this,SettingsActivity.class));
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


}