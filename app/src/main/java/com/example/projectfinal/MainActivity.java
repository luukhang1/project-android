package com.example.projectfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.projectfinal.adapter.ViewPagerAdapter;
import com.example.projectfinal.model.ImageUser;
import com.example.projectfinal.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private Button btnLogin, btnRegister;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPagerHome);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("usn");
        //System.out.println(user.getUsername());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),3,user);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0 : bottomNavigationView.getMenu().findItem(R.id.homeMenu).setChecked(true);
                        break;
                    case 1 : bottomNavigationView.getMenu().findItem(R.id.seachMenu).setChecked(true);
                        break;
                    case 2 : bottomNavigationView.getMenu().findItem(R.id.addMenu).setChecked(true);
                        break;
                    case 3 : bottomNavigationView.getMenu().findItem(R.id.profileMenu).setChecked(true);
                        break;
                    default:    bottomNavigationView.getMenu().findItem(R.id.homeMenu).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeMenu:viewPager.setCurrentItem(0);
                        break;
                    case R.id.seachMenu:viewPager.setCurrentItem(1);
                        break;
                    case R.id.addMenu:viewPager.setCurrentItem(2);
                        break;
                    case R.id.profileMenu:viewPager.setCurrentItem(3);
                        break;
                    default: viewPager.setCurrentItem(0);
                }
                return true;
            }
        });

    }
}