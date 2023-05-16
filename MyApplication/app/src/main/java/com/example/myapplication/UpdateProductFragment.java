package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class UpdateProductFragment extends Fragment {
    private EditText editText1, editText2, editText3, editText4;

    public UpdateProductFragment() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_product, container, false);
        editText1 = view.findViewById(R.id.ProductEditText1);
        editText2 = view.findViewById(R.id.ProductEditText2);
        editText3 = view.findViewById(R.id.ProductEditText3);
        editText4 = view.findViewById(R.id.ProductEditText4);
        Button submitButton = view.findViewById(R.id.updateProductSubmitButton);

        editText1.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                try {
                    int productId = Integer.parseInt(editText1.getText().toString());

                    Product product = menuActivity.myDatabase.myDao().getProductById(productId);

                    if (product != null) {
                        editText2.setText(product.getName());
                        editText3.setText(String.valueOf(product.getStock()));
                        editText4.setText(String.valueOf(product.getPrice()));
                    } else {
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                    }
                } catch (NumberFormatException e) {
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                }
            }
        });
        submitButton.setOnClickListener((v) -> {
            int productId = -1;
            int productStock = -1;
            double productPrice = -1.0;

            try {
                productId = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Could not parse " + ex);
            }

            String productName = editText2.getText().toString().trim();

            try {
                productStock = Integer.parseInt(editText3.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Could not parse " + ex);
            }

            try {
                productPrice = Double.parseDouble(editText4.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Could not parse " + ex);
            }

            String productStockTEXT = editText3.getText().toString().trim();
            String productPriceTEXT = editText4.getText().toString().trim();
            if (productId != -1 && !productName.isEmpty() && !productStockTEXT.isEmpty() && !productPriceTEXT.isEmpty()) {
                Product product = new Product();
                product.setId(productId);
                product.setName(productName);
                product.setStock(productStock);
                product.setPrice(productPrice);

                int rowsUpdated = menuActivity.myDatabase.myDao().updateProduct(product);

                if (rowsUpdated > 0) {
                    Toast.makeText(getActivity(), "Record updated", Toast.LENGTH_LONG).show();
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
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
