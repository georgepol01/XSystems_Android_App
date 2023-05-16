package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class InsertCustomerFragment extends Fragment {
    private EditText editText1, editText2, editText3;

    public InsertCustomerFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_customer, container, false);
        editText1 = view.findViewById(R.id.CustomerEditText1);
        editText2 = view.findViewById(R.id.CustomerEditText2);
        editText3 = view.findViewById(R.id.CustomerEditText3);
        Button submitButton = view.findViewById(R.id.insertCustomerSubmitButton);
        submitButton.setOnClickListener((v) -> {
            int customerId = 0;
            String customerName;
            String customerEmail;
            String customerIdTEXT = editText1.getText().toString().trim();
            String customerNameTEXT = editText2.getText().toString().trim();
            String customerEmailTEXT = editText3.getText().toString().trim();
            if (!customerIdTEXT.isEmpty() && !customerNameTEXT.isEmpty() && !customerEmailTEXT.isEmpty()) {
                try {
                    customerId = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                customerName = editText2.getText().toString().trim();
                customerEmail = editText3.getText().toString().trim();
            } else {
                Toast.makeText(getActivity(), "Please enter values for all fields", Toast.LENGTH_LONG).show();
                return;
            }
            int final_customerId = customerId;
            String final_customerName = customerName;
            String final_customerEmail = customerEmail;
            menuActivity.db.collection("Customer")
                    .whereEqualTo("cid", customerId)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()) {
                                try {
                                    Customer customer = new Customer();
                                    customer.setCid(final_customerId);
                                    customer.setCname(final_customerName);
                                    customer.setCustomerEmail(final_customerEmail);
                                    menuActivity.db.collection("Customer")
                                            .document("" + final_customerId)
                                            .set(customer)
                                            .addOnCompleteListener((task1) -> Toast.makeText(getActivity(), "Record added", Toast.LENGTH_LONG).show())
                                            .addOnFailureListener((e) -> Toast.makeText(getActivity(), "Insert Operation Failed", Toast.LENGTH_LONG).show());
                                } catch (Exception e) {
                                    String message = e.getMessage();
                                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                                }
                                editText1.setText("");
                                editText2.setText("");
                                editText3.setText("");

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
