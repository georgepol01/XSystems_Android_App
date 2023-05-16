package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class insertFragment extends Fragment implements View.OnClickListener {
    public insertFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert, container, false);
        Button insertButtonSupplier = view.findViewById(R.id.insertSupplierButton);
        insertButtonSupplier.setOnClickListener(this);

        Button insertButtonSupplies = view.findViewById(R.id.insertSuppliesButton);
        insertButtonSupplies.setOnClickListener(this);

        Button insertButtonProduct = view.findViewById(R.id.insertProductButton);
        insertButtonProduct.setOnClickListener(this);

        Button insertButtonCustomer = view.findViewById(R.id.insertCustomerButton);
        insertButtonCustomer.setOnClickListener(this);

        Button insertButtonSales = view.findViewById(R.id.insertSalesButton);
        insertButtonSales.setOnClickListener(this);

        return view;
    }

    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.insertSupplierButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new InsertSupplierFragment()).addToBackStack(null).commit();
        } else if (id == R.id.insertSuppliesButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new InsertSuppliesFragment()).addToBackStack(null).commit();
        } else if (id == R.id.insertProductButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new InsertProductFragment()).addToBackStack(null).commit();
        } else if (id == R.id.insertCustomerButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new InsertCustomerFragment()).addToBackStack(null).commit();
        } else if (id == R.id.insertSalesButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new InsertSalesFragment()).addToBackStack(null).commit();
        }
    }

}
