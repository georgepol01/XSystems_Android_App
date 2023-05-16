package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class updateFragment extends Fragment implements View.OnClickListener {

    public updateFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        Button updateButtonSupplier = view.findViewById(R.id.updateSupplierButton);
        updateButtonSupplier.setOnClickListener(this);

        Button updateButtonSupplies = view.findViewById(R.id.updateSuppliesButton);
        updateButtonSupplies.setOnClickListener(this);

        Button updateButtonProduct = view.findViewById(R.id.updateProductButton);
        updateButtonProduct.setOnClickListener(this);

        Button updateButtonCustomer = view.findViewById(R.id.updateCustomerButton);
        updateButtonCustomer.setOnClickListener(this);

        Button updateButtonSales = view.findViewById(R.id.updateSalesButton);
        updateButtonSales.setOnClickListener(this);

        return view;
    }

    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.updateSupplierButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateSupplierFragment()).addToBackStack(null).commit();
        } else if (id == R.id.updateSuppliesButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateSuppliesFragment()).addToBackStack(null).commit();
        } else if (id == R.id.updateProductButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateProductFragment()).addToBackStack(null).commit();
        } else if (id == R.id.updateCustomerButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateCustomerFragment()).addToBackStack(null).commit();
        } else if (id == R.id.updateSalesButton) {
            menuActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateSalesFragment()).addToBackStack(null).commit();
        }
    }


}
