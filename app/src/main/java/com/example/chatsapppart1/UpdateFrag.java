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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment updateFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateFrag newInstance(String param1, String param2) {
        UpdateFrag fragment = new UpdateFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_update, container, false);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.menu_chat_fragment, menu);

        menu.findItem(R.id.setting).setVisible(false);
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