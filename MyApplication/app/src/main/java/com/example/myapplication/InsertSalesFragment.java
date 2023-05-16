package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentReference;

public class InsertSalesFragment extends Fragment {
    private EditText editText1, editText2, editText3, editText4, editText5, editText6;

    public InsertSalesFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_sales, container, false);
        editText1 = view.findViewById(R.id.SalesEditText1);
        editText2 = view.findViewById(R.id.SalesEditText2);
        editText3 = view.findViewById(R.id.SalesEditText3);
        editText4 = view.findViewById(R.id.SalesEditText4);
        editText5 = view.findViewById(R.id.SalesEditText5);
        editText6 = view.findViewById(R.id.SalesEditText6);
        editText4.setFocusable(false);
        Button submitButton = view.findViewById(R.id.insertSalesSubmitButton);
        editText5.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                try {
                    int productId = Integer.parseInt(editText3.getText().toString());
                    int quantity = Integer.parseInt(editText5.getText().toString());
                    Product product = menuActivity.myDatabase.myDao().getProductById(productId);
                    double productPrice = product.getPrice();
                    double totalPay;
                    totalPay = quantity * productPrice;
                    editText4.setText(String.valueOf(totalPay));
                } catch (NumberFormatException e) {
                    editText4.setText("");
                }
            }
        });
        submitButton.setOnClickListener((v) -> {
            int salesId = 0;
            int salesPid = 0;
            double salesPrice = 0.0;
            int salesQuantity = 0;
            String salesCid;
            String salesDate;
            DocumentReference salesCustomerId;
            String salesIdTEXT = editText1.getText().toString().trim();
            String salesCidTEXT = editText2.getText().toString().trim();
            String salesPidTEXT = editText3.getText().toString().trim();
            String salesPriceTEXT = editText4.getText().toString().trim();
            String salesQuantityTEXT = editText5.getText().toString().trim();
            String salesDateTEXT = editText6.getText().toString().trim();
            if (!salesIdTEXT.isEmpty() && !salesCidTEXT.isEmpty() && !salesPidTEXT.isEmpty() && !salesPriceTEXT.isEmpty() && !salesQuantityTEXT.isEmpty() && !salesDateTEXT.isEmpty()) {
                try {
                    salesId = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                salesCid = editText2.getText().toString().trim();
                salesCustomerId = menuActivity.db.document("/Customer/" + salesCid);
                try {
                    salesPid = Integer.parseInt(editText3.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                try {
                    salesPrice = Double.parseDouble(editText4.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                try {
                    salesQuantity = Integer.parseInt(editText5.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                salesDate = editText6.getText().toString().trim();
            } else {
                Toast.makeText(getActivity(), "Please enter values for all fields", Toast.LENGTH_LONG).show();
                return;
            }
            int final_salesId = salesId;
            DocumentReference final_salesCustomerId = salesCustomerId;
            int final_salesPid = salesPid;
            double final_salesPrice = salesPrice;
            int final_salesQuantity = salesQuantity;
            String final_salesDate = salesDate;
            menuActivity.db.collection("Sales")
                    .whereEqualTo("sid", salesId)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()) {
                                int stock_CHECK = menuActivity.myDatabase.myDao().getProductById(final_salesPid).getStock();
                                if (stock_CHECK >= final_salesQuantity) {
                                    try {
                                        Sales sales = new Sales();
                                        sales.setSid(final_salesId);
                                        sales.setCid(final_salesCustomerId);
                                        sales.setPid(final_salesPid);
                                        sales.setPrice(final_salesPrice);
                                        sales.setQuantity(final_salesQuantity);
                                        sales.setSdate(final_salesDate);
                                        menuActivity.db.collection("Sales")
                                                .document("" + final_salesId)
                                                .set(sales)
                                                .addOnCompleteListener((task1) -> {
                                                    Product prod = menuActivity.myDatabase.myDao().getProductById(final_salesPid);
                                                    int newStock = stock_CHECK - final_salesQuantity;
                                                    prod.setStock(newStock);
                                                    menuActivity.myDatabase.myDao().updateProduct(prod);
                                                    Toast.makeText(getActivity(), "Record added", Toast.LENGTH_LONG).show();
                                                })
                                                .addOnFailureListener((e) -> Toast.makeText(getActivity(), "Insert Operation Failed", Toast.LENGTH_LONG).show());
                                    } catch (Exception e) {
                                        String message = e.getMessage();
                                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                                    }
                                    editText1.setText("");
                                    editText2.setText("");
                                    editText3.setText("");
                                    editText4.setText("");
                                    editText5.setText("");
                                    editText6.setText("");
                                } else {
                                    Toast.makeText(getActivity(), "Insufficient product quantity", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Record already exists", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error getting document", Toast.LENGTH_LONG).show();
                        }
                    });
        });
        return view;
    }
}
