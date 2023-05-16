package com.example.myapplication;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class mainFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button searchButton = view.findViewById(R.id.mainsearchbtn);
        searchButton.setOnClickListener(this);

        Button insertButton = view.findViewById(R.id.maininsertbtn);
        insertButton.setOnClickListener(this);

        Button deleteButton = view.findViewById(R.id.maindeletebtn);
        deleteButton.setOnClickListener(this);

        Button updateButton = view.findViewById(R.id.mainupdatebtn);
        updateButton.setOnClickListener(this);

        return view;

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mainsearchbtn) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new queryFragment()).addToBackStack(null).commit();
        } else if (v.getId() == R.id.maininsertbtn) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new insertFragment()).addToBackStack(null).commit();
        } else if (v.getId() == R.id.maindeletebtn) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new deleteFragment()).addToBackStack(null).commit();
        } else if (v.getId() == R.id.mainupdatebtn) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new updateFragment()).addToBackStack(null).commit();
        }
    }


}
