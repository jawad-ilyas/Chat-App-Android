package com.example.chatsapppart1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class ChatFrag extends Fragment {

    public ChatFrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.menu_chat_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.camera) {
            showToast("Camera clicked");
            return true;
        } else if (itemId == R.id.search) {
            showToast("Search clicked");
            return true;
        } else if (itemId == R.id.adverise) {
            showToast("Advertise clicked");
            return true;
        } else if (itemId == R.id.business_tools) {
            showToast("Business Tools clicked");
            return true;
        } else if (itemId == R.id.clear_call_log) {
            showToast("Clear Call Log clicked");
            return true;
        } else if (itemId == R.id.setting) {
            showToast("Setting clicked");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }





    }
    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }



}