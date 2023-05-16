package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class menuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static FragmentManager fragmentManager;
    public static MyDatabase myDatabase;
    public static FirebaseFirestore db;
    public static Toolbar toolbar;
    public static ActionBarDrawerToggle toggle;
    public static DrawerLayout drawerLayout;
    public static NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new mainFragment()).commit();
        }
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "userDB").allowMainThreadQueries().build();
        db = FirebaseFirestore.getInstance();

        drawerLayout = findViewById(R.id.drawer_menu);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.search);

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        final int searchId = R.id.search;
        final int insertId = R.id.insert;
        final int deleteId = R.id.delete;
        final int updateId = R.id.update;
        final int logoutId = R.id.logout;
        final int aboutId = R.id.about;

        switch (item.getItemId()) {
            case searchId:
                menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new queryFragment()).addToBackStack(null).commit();
                break;
            case insertId:
                menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new insertFragment()).addToBackStack(null).commit();
                break;
            case deleteId:
                menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new deleteFragment()).addToBackStack(null).commit();
                break;
            case updateId:
                menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new updateFragment()).addToBackStack(null).commit();
                break;
            case logoutId:
                Intent intent = new Intent(menuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case aboutId:
                menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new aboutFragment()).addToBackStack(null).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}