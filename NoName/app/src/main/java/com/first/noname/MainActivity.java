package com.first.noname;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        signout=findViewById(R.id.signout);

        SharedPreferences sp = getSharedPreferences("LoginStatus", MODE_PRIVATE);
        boolean isLoggedIn = sp.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            bottomNavigationView.setVisibility(View.GONE);
            signout.setVisibility(View.VISIBLE);
        } else {
            bottomNavigationView.setVisibility(View.VISIBLE);
            LoginDialog loginDialog = new LoginDialog();
            loginDialog.show(getSupportFragmentManager(), "LoginDialog");
            signout.setVisibility(View.GONE);
        }

        signout.setOnClickListener(v->{
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();
            bottomNavigationView.setVisibility(View.VISIBLE);
            LoginDialog loginDialog = new LoginDialog();
            loginDialog.show(getSupportFragmentManager(), "LoginDialog");
            signout.setVisibility(View.GONE);
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    if (id == R.id.login) {
                        LoginDialog loginDialog = new LoginDialog();
                        loginDialog.show(getSupportFragmentManager(), "LoginDialog");
                        return true;
                    } else if (id == R.id.signUp) {
                        SignupDialog signupDialog = new SignupDialog();
                        signupDialog.show(getSupportFragmentManager(), "SigninDialog");
                        return true;
                    }
                    return true;
                }
            });
        }
    }
