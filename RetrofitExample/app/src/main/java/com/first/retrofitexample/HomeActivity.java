package com.first.retrofitexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.first.retrofitexample.postapi.PostActivity;

public class HomeActivity extends AppCompatActivity {

    TextView userName, userEmail, companyId, roleName;
    Button logOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        getIntentExtras();
        logOutBtn.setOnClickListener(v -> {
            SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();
            startActivity(new Intent(HomeActivity.this, PostActivity.class));
            finish();
        });
    }

    private void getIntentExtras() {
        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);

      userName.setText(sp.getString("name",null));
      userEmail.setText(sp.getString("email",null));
      companyId.setText(sp.getString("companyId",null));
      roleName.setText(sp.getString("roleName",null));
    }

    private void initViews() {
        logOutBtn = findViewById(R.id.logOutBtn);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        companyId = findViewById(R.id.companyId);
        roleName = findViewById(R.id.roleName);
    }
}