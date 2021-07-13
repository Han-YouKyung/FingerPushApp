package com.example.fingerpushapp;
/*
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fingerpushapp.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
      //  actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
       // actionBar.setHomeAsUpIndicator(R.drawable.home); //뒤로가기 버튼 이미지 지정

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();


                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

*/


import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fingerpush.android.FingerPushManager;
import com.fingerpush.android.NetworkUtility;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Context context = this;
    Fragment noticeFragment;
    Fragment tagFragment;
    Fragment infoFragment;
    Fragment targetFragment;
    Fragment guideFragment;
    Fragment settingFragment;
    Fragment mainFragment;
    FragmentManager fragmentManager;
    ImageButton homeBtn;
    TextView title;
    String appKey = "FIX0AIXR23CD";
    String secretKey = "GVBx0fTXO9Dewctmj876wj9ifFc1cLmd";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        noticeFragment = new NoticeFragment();
        tagFragment = new TagFragment();
        targetFragment = new TargetFragment();
        infoFragment = new InfoFragment();
        guideFragment = new GuideFragment();
        settingFragment = new SettingFragment();
        mainFragment = new MainFragment();
        fragmentManager = getSupportFragmentManager();

        homeBtn = (ImageButton) findViewById(R.id.homeBtn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      /*  ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기*/
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_compass);
        //   actionBar.setHomeAsUpIndicator(android.R.drawable.arrow_up_float); //뒤로가기 버튼 이미지 지정


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
      /*     Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        iv_menu = (ImageButton) findViewById(R.id.iv_menu);
        // iv_menu.setHomeAsUpIndicator;
        actionBar.setHomeAsUpIndicator(R.drawable.menu1);*/
      /*    actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(android.R.drawable.arrow_up_float);
        */

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_container, mainFragment);
        fragmentTransaction.commit();


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("FINGER PUSH LIVE");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.main_container, mainFragment);
                fragmentTransaction.commit();
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                title = (TextView) findViewById(R.id.tv_title);

                if (id == R.id.item_notice) {
                    //태그 프래그먼트
                    title.setText("태그");
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_container, noticeFragment);
                    fragmentTransaction.commit();

                } else if (id == R.id.item_tag) {
                    //태그 프래그먼트
                    title.setText("태그");
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_container, tagFragment);
                    fragmentTransaction.commit();

                } else if (id == R.id.item_target) {
                    // 타겟 프래그먼트
                    title.setText("타겟팅");
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_container, targetFragment);
                    fragmentTransaction.commit();

                } else if (id == R.id.item_info) {
                    // 정보 프래그먼트
                    title.setText("개발정보");
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_container, infoFragment);
                    fragmentTransaction.commit();

                } else if (id == R.id.item_guide) {
                    // 가이드 프래그먼트
                    title.setText("이용 가이드");
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_container, guideFragment);
                    fragmentTransaction.commit();
                } else if (id == R.id.item_setting) {
                    // 설정 프래그먼트
                    title.setText("알림설정");
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_container, settingFragment);
                    fragmentTransaction.commit();
                }

                return true;
            }
        });



        FingerPushManager.setAppKey(appKey);
        FingerPushManager.setAppSecret(secretKey);


        FingerPushManager.getInstance(this).setDevice(new NetworkUtility.ObjectListener() {
            @Override
            public void onComplete(String code, String message, JSONObject jsonObject) {
                if (code.equals("200") || code.equals("201")) {
                    System.out.println("성공 : " + code + message);  // 디바이스 최초 등록시 해당 코드 리턴 후 태그 등록
                }

            }

            @Override
            public void onError(String code, String message) {
                if (code.equals("504")) {
                    // 디바이스가 이미 등록된 경우 해당 코드 리턴 후 태그 등록
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { // 왼쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }
}