package com.mobile.ooad_project.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mobile.ooad_project.R;

public class AdminAvtivity extends AppCompatActivity {

    FrameLayout frameLayoutAdmin;

    BottomNavigationView bottomNavigationViewAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_admin);

        frameLayoutAdmin = (FrameLayout) findViewById(R.id.frameFragAdmin);
        bottomNavigationViewAdmin = (BottomNavigationView) findViewById(R.id.BottomNavAdmin);
        loadFragment(new DatSanFrag());
        addEvent();

    }
    public void addEvent(){
        bottomNavigationViewAdmin.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()  {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.QuanLyCoSoSan){
                    loadFragment(new QuanLyCoSoSan());
                    return true;
                } else if (id == R.id.QuanLyHoanTien) {
                    loadFragment(new QuanLyHoanTien());
                    return true;
                }
                return false;
            }
        });
    }
    public void loadFragment(androidx.fragment.app.Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.frameFragAdmin,fragment);
        ft.commit();
    }
}