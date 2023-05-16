package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class DeleteSuppliesFragment extends Fragment {
    private EditText editText1;

    public DeleteSuppliesFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_supplies, container, false);
        editText1 = view.findViewById(R.id.deleteSuppliesEditText1);
        Button submitButton = view.findViewById(R.id.deleteSuppliesSubmitButton);
        submitButton.setOnClickListener((v) -> {
            int suppliesId;
            try {
                suppliesId = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException ex) {
                Toast.makeText(getActivity(), "Invalid ID", Toast.LENGTH_LONG).show();
                return;
            }
            Supplies supplies = menuActivity.myDatabase.myDao().getSuppliesById(suppliesId);
            if (supplies == null) {
                Toast.makeText(getActivity(), "Record not found", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                menuActivity.myDatabase.myDao().deleteSupplies(supplies);
                Toast.makeText(getActivity(), "Record deleted", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                String message = e.getMessage();
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
            editText1.setText("");
        });
        return view;
    }
}
