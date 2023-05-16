package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class UpdateSupplierFragment extends Fragment {
    private EditText editText1, editText2, editText3, editText4, editText5;

    public UpdateSupplierFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_supplier, container, false);
        editText1 = view.findViewById(R.id.SupplierEditText1);
        editText2 = view.findViewById(R.id.SupplierEditText2);
        editText3 = view.findViewById(R.id.SupplierEditText3);
        editText4 = view.findViewById(R.id.SupplierEditText4);
        editText5 = view.findViewById(R.id.SupplierEditText5);
        Button submitButton = view.findViewById(R.id.updateSupplierSubmitButton);

        editText1.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                try {
                    int supplierId = Integer.parseInt(editText1.getText().toString());

                    Supplier supplier = menuActivity.myDatabase.myDao().getSupplierById(supplierId);

                    if (supplier != null) {
                        editText2.setText(supplier.getName());
                        editText3.setText(supplier.getAddress());
                        editText4.setText(String.valueOf(supplier.getPhone()));
                        editText5.setText(supplier.getEmail());
                    } else {
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                        editText5.setText("");
                    }
                } catch (NumberFormatException e) {
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                    editText5.setText("");
                }
            }
        });
        submitButton.setOnClickListener((v) -> {
            int supplierId = -1;

            try {
                supplierId = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Could not parse " + ex);
            }

            String supplierName = editText2.getText().toString().trim();
            String supplierAddress = editText3.getText().toString().trim();
            String supplierPhone = editText4.getText().toString().trim();
            String supplierEmail = editText5.getText().toString().trim();

            if (supplierId != -1 && !supplierName.isEmpty()) {
                Supplier supplier = new Supplier();
                supplier.setId(supplierId);
                supplier.setName(supplierName);
                supplier.setAddress(supplierAddress);
                supplier.setPhone(supplierPhone);
                supplier.setEmail(supplierEmail);

                int rowsUpdated = menuActivity.myDatabase.myDao().updateSupplier(supplier);

                if (rowsUpdated > 0) {
                    Toast.makeText(getActivity(), "Record updated", Toast.LENGTH_LONG).show();
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                    editText5.setText("");
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
