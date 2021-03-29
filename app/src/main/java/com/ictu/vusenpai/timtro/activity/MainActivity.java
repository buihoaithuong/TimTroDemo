package com.ictu.vusenpai.timtro.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.layouts.list_baiDang_Fragment;
import com.ictu.vusenpai.timtro.layouts.profile_Fragment;
import com.ictu.vusenpai.timtro.layouts.qlPhong_Fragment;
import com.ictu.vusenpai.timtro.layouts.search_Fragment;


public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new list_baiDang_Fragment()).commit();
    }
    //main

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, login_activity.class));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment=new list_baiDang_Fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,fragment).commit();
                    break;
                case R.id.navigation_search:
                    fragment=new search_Fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,fragment).commit();
                    break;
                case R.id.navigation_new:
                    fragment=new qlPhong_Fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,fragment).commit();
                    break;
                case R.id.navigation_user:
                    fragment=new profile_Fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,fragment).commit();
                    break;
            }
            return true;
        }
    };
}
