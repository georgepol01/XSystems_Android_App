package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class UpdateSalesFragment extends Fragment {
    private EditText editText1, editText2, editText3, editText4, editText5, editText6;

    public UpdateSalesFragment() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_sales, container, false);
        editText1 = view.findViewById(R.id.SalesEditText1);
        editText2 = view.findViewById(R.id.SalesEditText2);
        editText3 = view.findViewById(R.id.SalesEditText3);
        editText4 = view.findViewById(R.id.SalesEditText4);
        editText5 = view.findViewById(R.id.SalesEditText5);
        editText6 = view.findViewById(R.id.SalesEditText6);
        Button submitButton = view.findViewById(R.id.updateSalesSubmitButton);
        editText1.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                int salesId;
                try {
                    salesId = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                    return;
                }
                menuActivity.db.collection("Sales")
                        .document(String.valueOf(salesId))
                        .get()
                        .addOnSuccessListener((DocumentSnapshot documentSnapshot) -> {
                            if (documentSnapshot.exists()) {
                                Sales sales = documentSnapshot.toObject(Sales.class);
                                assert sales != null;
                                editText2.setText(sales.getCid().getId());
                                editText3.setText(String.valueOf(sales.getPid()));
                                editText4.setText(String.valueOf(sales.getPrice()));
                                editText5.setText(String.valueOf(sales.getQuantity()));
                                editText6.setText(sales.getSdate());
                            } else {
                                editText2.setText("");
                                editText3.setText("");
                                editText4.setText("");
                                editText5.setText("");
                                editText6.setText("");
                                Toast.makeText(getActivity(), "Sales record not found", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener((e) -> Toast.makeText(getActivity(), "Error fetching sales record", Toast.LENGTH_LONG).show());
            }
        });
        submitButton.setOnClickListener((v) -> {
            int salesId = 0;
            int salesPid = 0;
            double salesPrice = 0;
            int salesQuantity = 0;
            DocumentReference salesCustomerId;
            try {
                salesId = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Could not parse " + ex);
            }
            String salesCid = editText2.getText().toString();
            if (TextUtils.isEmpty(salesCid)) {
                Toast.makeText(getActivity(), "Document ID does not exist", Toast.LENGTH_LONG).show();
                return;
            }
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
            String salesDate = editText6.getText().toString();

            try {
                int final_salesId = salesId;
                int final_salesId2 = salesId;
                int final_salesPid = salesPid;
                double final_salesPrice = salesPrice;
                int final_salesQuantity = salesQuantity;
                if (final_salesId <= 0) {
                    Toast.makeText(getActivity(), "Invalid ID", Toast.LENGTH_LONG).show();
                    return;
                }
                menuActivity.db.collection("Sales").document("" + salesId).get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Sales sales = new Sales();
                        sales.setSid(final_salesId2);
                        sales.setCid(salesCustomerId);
                        sales.setPid(final_salesPid);
                        sales.setPrice(final_salesPrice);
                        sales.setQuantity(final_salesQuantity);
                        sales.setSdate(salesDate);
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("sid", final_salesId2);
                        updates.put("cid", salesCustomerId);
                        updates.put("pid", final_salesPid);
                        updates.put("price", final_salesPrice);
                        updates.put("quantity", final_salesQuantity);
                        updates.put("sdate", salesDate);
                        menuActivity.db.collection("Sales").document("" + final_salesId)
                                .update(updates)
                                .addOnCompleteListener((task) -> Toast.makeText(getActivity(), "Record updated", Toast.LENGTH_LONG).show())
                                .addOnFailureListener((e) -> Toast.makeText(getActivity(), "Update Operation Failed", Toast.LENGTH_LONG).show());
                    } else {
                        Toast.makeText(getActivity(), "Document with ID " + final_salesId + " does not exist", Toast.LENGTH_LONG).show();
                    }
                });
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
        });
        return view;
    }
}
