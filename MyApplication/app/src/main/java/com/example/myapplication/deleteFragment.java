package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class deleteFragment extends Fragment implements View.OnClickListener {

    public deleteFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);

        Button deleteButtonSupplier = view.findViewById(R.id.deleteSupplierButton);
        deleteButtonSupplier.setOnClickListener(this);

        Button deleteButtonSupplies = view.findViewById(R.id.deleteSuppliesButton);
        deleteButtonSupplies.setOnClickListener(this);

        Button deleteButtonProduct = view.findViewById(R.id.deleteProductButton);
        deleteButtonProduct.setOnClickListener(this);

        Button deleteButtonCustomer = view.findViewById(R.id.deleteCustomerButton);
        deleteButtonCustomer.setOnClickListener(this);

        Button deleteButtonSales = view.findViewById(R.id.deleteSalesButton);
        deleteButtonSales.setOnClickListener(this);

        return view;
    }

    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.deleteSupplierButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new DeleteSupplierFragment()).addToBackStack(null).commit();
        } else if (id == R.id.deleteSuppliesButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new DeleteSuppliesFragment()).addToBackStack(null).commit();
        } else if (id == R.id.deleteProductButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new DeleteProductFragment()).addToBackStack(null).commit();
        } else if (id == R.id.deleteCustomerButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new DeleteCustomerFragment()).addToBackStack(null).commit();
        } else if (id == R.id.deleteSalesButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new DeleteSalesFragment()).addToBackStack(null).commit();
        }
    }

}
