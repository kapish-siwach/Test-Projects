package com.first.smartstudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    LinearLayout loginBtn, signupBtn;
    private static final int CAMERA_PERMISSION_CODE = 100;
    ImageView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkSession();
        checkPermissions();
        initValues();

        Glide.with(this)
                        .asGif()
                                .load(R.drawable.student)
                                        .into(videoView);

        signupBtn.setOnClickListener(v->{
            SignupFragment signup= new SignupFragment();
            signup.show(getSupportFragmentManager(), "signup");
        });
        loginBtn.setOnClickListener(v->{
            LoginFragment login= new LoginFragment();
            login.show(getSupportFragmentManager(), "login");
        });
    }

    private void checkSession() {
        SharedPreferences sp=getSharedPreferences("login",0);
        if (sp.getBoolean("isLoggedIn",false)){
            Log.i("Check Session", "checkSession:"+sp.getBoolean("isLoggedIn",false));
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }

    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
    }


    private void initValues() {
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);
        videoView= findViewById(R.id.videoView);
    }
}