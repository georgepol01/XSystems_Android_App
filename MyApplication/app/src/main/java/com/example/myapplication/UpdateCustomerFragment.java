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

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class UpdateCustomerFragment extends Fragment {
    private EditText editText1, editText2, editText3;

    public UpdateCustomerFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_customer, container, false);
        editText1 = view.findViewById(R.id.CustomerEditText1);
        editText2 = view.findViewById(R.id.CustomerEditText2);
        editText3 = view.findViewById(R.id.CustomerEditText3);
        Button submitButton = view.findViewById(R.id.updateCustomerSubmitButton);
        editText1.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                int customerId;
                try {
                    customerId = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                    return;
                }
                menuActivity.db.collection("Customer")
                        .document(String.valueOf(customerId))
                        .get()
                        .addOnSuccessListener((DocumentSnapshot documentSnapshot) -> {
                            if (documentSnapshot.exists()) {
                                Customer customer = documentSnapshot.toObject(Customer.class);
                                assert customer != null;
                                editText2.setText(customer.getCname());
                                editText3.setText(customer.getCustomerEmail());
                            } else {
                                editText2.setText("");
                                editText3.setText("");
                                Toast.makeText(getActivity(), "Customer record not found", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener((e) -> Toast.makeText(getActivity(), "Error fetching customer record", Toast.LENGTH_LONG).show());
            }
        });
        submitButton.setOnClickListener((v) -> {
            int customerId = 0;
            String customerName;
            String customerEmail;
            try {
                customerId = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Could not parse " + ex);
            }
            String customerIdTEXT = String.valueOf(customerId);
            if (TextUtils.isEmpty(customerIdTEXT)) {
                Toast.makeText(getActivity(), "Document ID does not exist", Toast.LENGTH_LONG).show();
                return;
            }
            customerName = editText2.getText().toString();
            customerEmail = editText3.getText().toString();

            try {
                int final_customerId = customerId;
                if (final_customerId <= 0) {
                    Toast.makeText(getActivity(), "Invalid ID", Toast.LENGTH_LONG).show();
                    return;
                }
                menuActivity.db.collection("Customer").document("" + final_customerId).get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Customer customer = null;
                        assert false;
                        customer.setCid(final_customerId);
                        customer.setCname(customerName);
                        customer.setCustomerEmail(customerEmail);
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("cid", final_customerId);
                        updates.put("cname", customerName);
                        updates.put("cemail", customerEmail);
                        menuActivity.db.collection("Customer").document("" + final_customerId)
                                .update(updates)
                                .addOnCompleteListener((task) -> Toast.makeText(getActivity(), "Record updated", Toast.LENGTH_LONG).show())
                                .addOnFailureListener((e) -> Toast.makeText(getActivity(), "Update Operation Failed", Toast.LENGTH_LONG).show());
                    } else {
                        Toast.makeText(getActivity(), "Document with ID " + final_customerId + " does not exist", Toast.LENGTH_LONG).show();
                    }
                });
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
