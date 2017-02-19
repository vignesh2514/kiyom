package com.v7ench.kiyo;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import static com.v7ench.kiyo.MainActivity.hasPermissions;

public class SignActivity extends AppCompatActivity {
    int PERMISSION_ALL = 1;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence Titles[] = {"Sign In", "Sign Up"};
    int Numboftabs = 2;
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    boolean isUserFirstTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isUserFirstTime = Boolean.valueOf(Utils.readSharedSetting(SignActivity.this, PREF_USER_FIRST_TIME, "true"));
        Intent introIntent = new Intent(SignActivity.this, PagerActivity.class);
        introIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime);

        if (isUserFirstTime)
            startActivity(introIntent);
        setContentView(R.layout.activity_sign);
        String[] PERMISSIONS = {Manifest.permission.READ_SMS, Manifest.permission.CAMERA};

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles,
                Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);
        tabs.setViewPager(pager);
    }




}
