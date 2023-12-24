package com.example.chatsapppart1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentAdapter extends FragmentStateAdapter {
    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            return new ChatFrag();
        } else if (position == 1) {
            return new UpdateFrag();
        } else if (position == 2) {
            return new CallFrag();
        } else {
            return null;
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
