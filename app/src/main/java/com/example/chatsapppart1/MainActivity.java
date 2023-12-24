package com.example.chatsapppart1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.chatsapppart1.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;


    // create this adapter for the view pager and tab layout
    MyFragmentAdapter myFragmentAdapter;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // how to work with the view pager 2 and tab layout

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Chat"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Update"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Call"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        myFragmentAdapter = new MyFragmentAdapter(fragmentManager, getLifecycle());
        binding.viewPager.setAdapter(myFragmentAdapter);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });



        // ----  ---- ----



        // how to work with the custom toolbar , action bar , app bar


        // 1- step for the toolbar
            // Find the Toolbar by its ID
        setSupportActionBar(binding.toolbar);

        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setTitle("Chat App");
        }



    }


}