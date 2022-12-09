package com.example.ignocop;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransaction;
    NavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,new Home_Fragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");
        nav_view=(NavigationView)findViewById(R.id.nav_view);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new Connect_Fragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Connect");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new Home_Fragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Home");
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new Trusted_Fragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Trusted Users");

        } else if (id == R.id.nav_slideshow) {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new Devices_Fragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Devices");

        } else if (id == R.id.nav_ppl) {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new People_Fragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Users");

        } else if (id == R.id.nav_tools) {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new Police_Fragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Police");

        } else if (id == R.id.nav_share) {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new Settings_Fragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Settings");

        } else if (id == R.id.nav_send) {
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new About_Fragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("About");

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
