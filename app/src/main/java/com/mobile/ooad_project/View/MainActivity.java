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

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameFrag);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNav);
        loadFragment(new DatSanFrag());
        addEvent();
    }

    public void addEvent(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()  {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.DatSan){
                    loadFragment(new DatSanFrag());
                    return true;
                } else if (id == R.id.DaGiai) {
                    loadFragment(new DaGiaiFrag());
                    return true;
                } else if (id == R.id.DaGiaoHuu) {
                    loadFragment(new DaGiaoHuuFrag());
                    return true;
                } else if (id == R.id.TaiKhoan) {
                    loadFragment(new TaiKhoanFrag());
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
        ft.replace(R.id.frameFrag,fragment);
        ft.commit();
    }
}