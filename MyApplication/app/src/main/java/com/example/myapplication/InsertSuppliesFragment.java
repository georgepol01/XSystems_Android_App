package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class InsertSuppliesFragment extends Fragment {
    private EditText editText1, editText2, editText3;

    public InsertSuppliesFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_supplies, container, false);
        editText1 = view.findViewById(R.id.SuppliesEditText1);
        editText2 = view.findViewById(R.id.SuppliesEditText2);
        editText3 = view.findViewById(R.id.SuppliesEditText3);
        Button submitButton = view.findViewById(R.id.insertSuppliesSubmitButton);
        submitButton.setOnClickListener((v) -> {
            int suppliesId = 0;
            int supplierId = 0;
            int productId = 0;
            String suppliesIdText = editText1.getText().toString().trim();
            String supplierIdText = editText2.getText().toString().trim();
            String productIdText = editText3.getText().toString().trim();
            if (!suppliesIdText.isEmpty() && !supplierIdText.isEmpty() && !productIdText.isEmpty()) {
                try {
                    suppliesId = Integer.parseInt(suppliesIdText);
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                try {
                    supplierId = Integer.parseInt(supplierIdText);
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                try {
                    productId = Integer.parseInt(productIdText);
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
            } else {
                Toast.makeText(getActivity(), "Please enter a valid ID", Toast.LENGTH_LONG).show();
                return;
            }

            try {
                Supplies supplies = new Supplies();
                supplies.setId(suppliesId);
                supplies.setIdSupplier(supplierId);
                supplies.setIdProduct(productId);
                menuActivity.myDatabase.myDao().insertSupplies(supplies);
                Toast.makeText(getActivity(), "Record added", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                String message = e.getMessage();
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
        });
        return view;
    }
}
