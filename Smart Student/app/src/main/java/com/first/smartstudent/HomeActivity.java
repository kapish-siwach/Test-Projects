package com.first.smartstudent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    Button logoutBtn;
    private UserDB userDB;
    TextView workInprogress,fullNameShow,emailShow,mobileShow;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ImageView showUserImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        getUserInfo();

        logoutBtn.setOnClickListener(v->{
           signOut();
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id=item.getItemId();
            if (id==R.id.signoutBtn){
                signOut();
            }
                return true;
            }
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else {
                    finish();
                }
            }
        });
    }

    private void initViews() {
        workInprogress=findViewById(R.id.workInprogress);
        logoutBtn=findViewById(R.id.logoutBtn);
        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.main);

        fullNameShow=navigationView.getHeaderView(0).findViewById(R.id.fullNameShow);
        emailShow=navigationView.getHeaderView(0).findViewById(R.id.emailShow);
        mobileShow=navigationView.getHeaderView(0).findViewById(R.id.mobileShow);
        showUserImg=navigationView.getHeaderView(0).findViewById(R.id.showUserImg);

        userDB = new UserDB(HomeActivity.this);

    }

    private void signOut() {
        SharedPreferences sp=getSharedPreferences("login",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLoggedIn",false);
        editor.apply();
        startActivity(new Intent(HomeActivity.this,MainActivity.class));
    }

    private void getUserInfo() {
        SharedPreferences sp=getSharedPreferences("login",0);
        UserDetails user= userDB.getUserByEmailPassword(sp.getString("userEmail",""),sp.getString("password",""));
        if (user!=null){
            fullNameShow.setText(user.getName());
            emailShow.setText(user.getEmail());
            mobileShow.setText(user.getMobile());
            showUserImg.setImageBitmap(BitmapFactory.decodeByteArray(user.getImage(), 0, user.getImage().length));
        }
    }
}