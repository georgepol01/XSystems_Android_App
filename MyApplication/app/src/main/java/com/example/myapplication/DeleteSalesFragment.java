package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class DeleteSalesFragment extends Fragment {
    private EditText editText1;

    public DeleteSalesFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_sales, container, false);
        editText1 = view.findViewById(R.id.deleteSalesEditText1);
        Button submitButton = view.findViewById(R.id.deleteSalesSubmitButton);
        submitButton.setOnClickListener((v) -> {
            int salesId= 0;
            try {
                salesId = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException ex) {
                System.out.println("Could not parse " + ex);
            }
            try {
                int final_salesId = salesId;
                menuActivity.db.collection("Sales").document("" + salesId).get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Sales sales = new Sales();
                        sales.setSid(final_salesId);
                        menuActivity.db.collection("Sales").document("" + final_salesId)
                                .delete()
                                .addOnCompleteListener((task) -> Toast.makeText(getActivity(), "Record deleted", Toast.LENGTH_LONG).show())
                                .addOnFailureListener((e) -> Toast.makeText(getActivity(), "Delete Operation Failed", Toast.LENGTH_LONG).show());
                    } else {
                        Toast.makeText(getActivity(), "Document with ID " + final_salesId + " does not exist", Toast.LENGTH_LONG).show();
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
