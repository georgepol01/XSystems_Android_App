package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class InsertProductFragment extends Fragment {
    private EditText editText1, editText2, editText3, editText4;

    public InsertProductFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_product, container, false);
        editText1 = view.findViewById(R.id.ProductEditText1);
        editText2 = view.findViewById(R.id.ProductEditText2);
        editText3 = view.findViewById(R.id.ProductEditText3);
        editText4 = view.findViewById(R.id.ProductEditText4);
        Button submitButton = view.findViewById(R.id.insertProductSubmitButton);

        submitButton.setOnClickListener((v) -> {
            int productId = 0;
            int productStock = 0;
            double productPrice = 0;
            String productName;

            String productIdText = editText1.getText().toString().trim();
            String productNameText = editText2.getText().toString().trim();
            if (!productIdText.isEmpty() && !productNameText.isEmpty()) {
                try {
                    productId = Integer.parseInt(productIdText);
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                productName = editText2.getText().toString().trim();
                try {
                    productStock = Integer.parseInt(editText3.getText().toString().trim());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                try {
                    productPrice = Integer.parseInt(editText4.getText().toString().trim());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
            } else {
                Toast.makeText(getActivity(), "Please enter a valid ID", Toast.LENGTH_LONG).show();
                return;
            }

            try {
                Product product = new Product();
                product.setId(productId);
                product.setName(productName);
                product.setStock(productStock);
                product.setPrice(productPrice);
                menuActivity.myDatabase.myDao().insertProduct(product);
                Toast.makeText(getActivity(), "Record added", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                String message = e.getMessage();
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
            editText4.setText("");
        });
        return view;
    }
}
