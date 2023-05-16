package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class DeleteSupplierFragment extends Fragment {
    private EditText editText1;

    public DeleteSupplierFragment() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_supplier, container, false);
        editText1 = view.findViewById(R.id.deleteSupplierEditText1);
        Button submitButton = view.findViewById(R.id.deleteSupplierSubmitButton);
        submitButton.setOnClickListener((v) -> {
            int supplierId;
            try {
                supplierId = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException ex) {
                Toast.makeText(getActivity(), "Invalid ID", Toast.LENGTH_LONG).show();
                return;
            }
            Supplier supplier = menuActivity.myDatabase.myDao().getSupplierById(supplierId);
            if (supplier == null) {
                Toast.makeText(getActivity(), "Record not found", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                menuActivity.myDatabase.myDao().deleteSupplier(supplier);
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
