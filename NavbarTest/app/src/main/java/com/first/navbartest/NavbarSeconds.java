package com.first.navbartest;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class NavbarSeconds extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar_seconds);

        drawerLayout=findViewById(R.id.main);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if (id==R.id.homeMenu){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framelayoutFgm,new HomeFragment())
                            .commit();
                    drawerLayout.closeDrawers();
            } else if (id==R.id.settingsMenu) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framelayoutFgm,new SettingsFragment())
                            .commit();
                    drawerLayout.closeDrawers();
                } else if (id==R.id.searchMenu) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framelayoutFgm,new SearchFragment())
                            .commit();
                    drawerLayout.closeDrawers();
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
}