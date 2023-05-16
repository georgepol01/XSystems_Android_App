package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class DeleteCustomerFragment extends Fragment {
    private EditText editText1;

    public DeleteCustomerFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_customer, container, false);
        editText1 = view.findViewById(R.id.deleteCustomerEditText1);
        Button submitButton = view.findViewById(R.id.deleteCustomerSubmitButton);
        submitButton.setOnClickListener((v) -> {
            int customerId = 0;
            try {
                customerId = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Could not parse " + ex);
            }
            try {
                int final_customerId = customerId;
                menuActivity.db.collection("Customer").document("" + customerId).get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Customer customer = null;
                        assert false;
                        customer.setCid(final_customerId);
                        menuActivity.db.collection("Customer").document("" + final_customerId)
                                .delete()
                                .addOnCompleteListener((task) -> Toast.makeText(getActivity(), "Record deleted", Toast.LENGTH_LONG).show())
                                .addOnFailureListener((e) -> Toast.makeText(getActivity(), "Delete Operation Failed", Toast.LENGTH_LONG).show());
                    } else {
                        Toast.makeText(getActivity(), "Document with ID " + final_customerId + " does not exist", Toast.LENGTH_LONG).show();
                    }
                });
            } catch (Exception e) {
                String message = e.getMessage();
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
            editText1.setText("");
        });
        return view;
    }
}
