package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class UpdateSuppliesFragment extends Fragment {
    private EditText editText1, editText2, editText3;

    public UpdateSuppliesFragment() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_supplies, container, false);
        editText1 = view.findViewById(R.id.SuppliesEditText1);
        editText2 = view.findViewById(R.id.SuppliesEditText2);
        editText3 = view.findViewById(R.id.SuppliesEditText3);
        Button submitButton = view.findViewById(R.id.updateSuppliesSubmitButton);

        editText1.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                try {
                    int suppliesId = Integer.parseInt(editText1.getText().toString());

                    Supplies supplies = menuActivity.myDatabase.myDao().getSuppliesById(suppliesId);

                    if (supplies != null) {
                        editText2.setText(String.valueOf(supplies.getIdSupplier()));
                        editText3.setText(String.valueOf(supplies.getIdProduct()));
                    } else {
                        editText2.setText("");
                        editText3.setText("");
                    }
                } catch (NumberFormatException e) {
                    editText2.setText("");
                    editText3.setText("");
                }
            }
        });
        submitButton.setOnClickListener((v) -> {
            int suppliesId = -1;
            int supplierId = -1;
            int productId = -1;

            try {
                suppliesId = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Could not parse " + ex);
            }
            try {
                supplierId = Integer.parseInt(editText2.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Could not parse " + ex);
            }
            try {
                productId = Integer.parseInt(editText3.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Could not parse " + ex);
            }

            String supplierIdTEXT = editText2.getText().toString().trim();
            String productIdTEXT = editText3.getText().toString().trim();


            if (suppliesId != -1 && !supplierIdTEXT.isEmpty() && !productIdTEXT.isEmpty()) {

                int rowsUpdated = -1;
                int count = menuActivity.myDatabase.myDao().checkSuppliesExistence(supplierId, productId);
                if (count > 0) {
                    Toast.makeText(getActivity(), "Invalid input values", Toast.LENGTH_LONG).show();
                } else {
                    rowsUpdated = menuActivity.myDatabase.myDao().updateSupplies(suppliesId, supplierId, productId);
                }

                if (rowsUpdated > 0) {
                    Toast.makeText(getActivity(), "Record updated", Toast.LENGTH_LONG).show();
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                } else {
                    Toast.makeText(getActivity(), "No record was updated", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getActivity(), "Invalid input values", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }
}
