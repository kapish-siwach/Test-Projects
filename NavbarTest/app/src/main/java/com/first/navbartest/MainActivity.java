package com.first.navbartest;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNavMenu);

        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.homeMenu);
    }

    HomeFragment homeFragment=new HomeFragment();
    SearchFragment searchFragment=new SearchFragment();
    SettingsFragment settingsFragment=new SettingsFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
       if (id==R.id.homeMenu){
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.fragmentFl,homeFragment)
                   .commit();
           return true;
       } else if (id==R.id.searchMenu) {
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.fragmentFl,searchFragment)
                   .commit();
           return true;
       } else if (id==R.id.settingsMenu) {
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.fragmentFl,settingsFragment)
                   .commit();
           return true;
       }
        return false;
    }
}