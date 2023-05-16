package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class InsertSupplierFragment extends Fragment {
    private EditText editText1, editText2, editText3, editText4, editText5;

    public InsertSupplierFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_supplier, container, false);
        editText1 = view.findViewById(R.id.SupplierEditText1);
        editText2 = view.findViewById(R.id.SupplierEditText2);
        editText3 = view.findViewById(R.id.SupplierEditText3);
        editText4 = view.findViewById(R.id.SupplierEditText4);
        editText5 = view.findViewById(R.id.SupplierEditText5);
        Button submitButton = view.findViewById(R.id.insertSupplierSubmitButton);
        submitButton.setOnClickListener((v) -> {
            int supplierId = 0;
            int supplierPhone = 0;
            String supplierName;
            String supplierAddress;
            String supplierEmail;

            String supplierIdText = editText1.getText().toString().trim();
            String supplierNameText = editText2.getText().toString().trim();
            if (!supplierIdText.isEmpty() && !supplierNameText.isEmpty()) {
                try {
                    supplierId = Integer.parseInt(supplierIdText);
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                supplierName = editText2.getText().toString().trim();
                supplierAddress = editText3.getText().toString().trim();
                try {
                    supplierPhone = Integer.parseInt(editText4.getText().toString().trim());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                supplierEmail = editText5.getText().toString().trim();

            } else {
                Toast.makeText(getActivity(), "Please enter a valid ID", Toast.LENGTH_LONG).show();
                return;
            }

            try {
                Supplier supplier = new Supplier();
                supplier.setId(supplierId);
                supplier.setName(supplierName);
                supplier.setAddress(supplierAddress);
                supplier.setPhone(String.valueOf(supplierPhone));
                supplier.setEmail(supplierEmail);
                menuActivity.myDatabase.myDao().insertSupplier(supplier);
                Toast.makeText(getActivity(), "Record added", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                String message = e.getMessage();
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
            editText4.setText("");
            editText5.setText("");
        });
        return view;
    }
}
