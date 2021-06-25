package com.marveltech.docare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
private static final int SPLASH_SCREEN_TIMEOUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser()!= null) {
                    startActivity(new Intent(MainActivity.this,HomeActivity2.class));
                    finish();
                }
                else
                {
                    Intent i = new Intent(MainActivity.this,
                            LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },SPLASH_SCREEN_TIMEOUT);
    }

}