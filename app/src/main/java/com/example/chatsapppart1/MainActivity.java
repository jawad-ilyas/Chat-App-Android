package com.example.chatsapppart1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.chatsapppart1.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;


    // create this adapter for the view pager and tab layout
    MyFragmentAdapter myFragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

    }
}