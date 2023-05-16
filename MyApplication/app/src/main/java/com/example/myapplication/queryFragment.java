package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class queryFragment extends Fragment {

    private CollectionReference collectionReference;
    private TextView queryTextView, queryTextResult;
    private int test;

    public queryFragment() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        final String[] queryArray = getResources().getStringArray(R.array.queries_description_array);
        queryTextView = view.findViewById(R.id.txtquery);
        queryTextResult = view.findViewById(R.id.txtqueryresult);
        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.queries_array, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                queryTextResult.setText("");
                queryTextView.setText(queryArray[position]);
                test = position + 1;
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        queryTextResult = view.findViewById(R.id.txtqueryresult);
        Button runButton = view.findViewById(R.id.queryrun);
        runButton.setOnClickListener((v) -> {
            StringBuilder result = new StringBuilder();
            switch (test) {
                case 1:
                    List<Supplies> supplies = menuActivity.myDatabase.myDao().getSupplies();
                    for (Supplies i : supplies) {
                        int suppliesId = i.getId();
                        int supplierId = i.getIdSupplier();
                        int productId = i.getIdProduct();
                        result.append("\n\n Supplies ID: ").append(suppliesId).append("\n Supplier ID: ").append(supplierId).append("\n Product ID: ").append(productId);
                    }
                    queryTextResult.setText(result.toString());
                    break;
                case 2:
                    List<Supplier> supplier = menuActivity.myDatabase.myDao().getSupplier();
                    for (Supplier i : supplier) {
                        int supplierId = i.getId();
                        String supplierName = i.getName();
                        String supplierAddress = i.getAddress();
                        String supplierPhone = i.getPhone();
                        String supplierEmail = i.getEmail();
                        result.append("\n\n Supplier ID: ").append(supplierId).append("\n Supplier Name: ").append(supplierName.trim()).append("\n Supplier Address: ").append(supplierAddress.trim()).append("\n Supplier Phone: ").append(supplierPhone.trim()).append("\n Supplier Email: ").append(supplierEmail.trim());
                    }
                    queryTextResult.setText(result.toString());
                    break;
                case 3:
                    List<Product> product = menuActivity.myDatabase.myDao().getProduct();
                    for (Product i : product) {
                        int productId = i.getId();
                        String productName = i.getName();
                        int productStock = i.getStock();
                        double productPrice = i.getPrice();
                        result.append("\n\n Product ID: ").append(productId).append("\n Product Name: ").append(productName).append("\n Product Stock: ").append(productStock).append("\n Product Price: ").append(productPrice);
                    }
                    queryTextResult.setText(result.toString());
                    break;
                case 4:
                    CollectionReference salesRef1 = menuActivity.db.collection("Sales");
                    CollectionReference customersRef1 = menuActivity.db.collection("Customer");

                    salesRef1.orderBy("sid", Query.Direction.ASCENDING)
                            .get()
                            .addOnSuccessListener(queryDocumentSnapshots -> {
                                List<String> resultList = new ArrayList<>();
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    DocumentReference cidRef = documentSnapshot.getDocumentReference("cid");
                                    if (cidRef != null) {
                                        customersRef1.document(cidRef.getId())
                                                .get()
                                                .addOnSuccessListener(customerDoc -> {
                                                    String customerName = customerDoc.getString("cname");
                                                    String result3 = "\n\nSales ID: " + documentSnapshot.getLong("sid")
                                                            + "\nCustomer ID: " + customerDoc.getLong("cid")
                                                            + "\nCustomer Name: " + customerName
                                                            + "\nProduct ID: " + documentSnapshot.getLong("pid")
                                                            + "\nPrice: " + documentSnapshot.getLong("price")
                                                            + "\nQuantity: " + documentSnapshot.getLong("quantity")
                                                            + "\nDate: " + documentSnapshot.getString("sdate");
                                                    resultList.add(result3);
                                                    if (resultList.size() == queryDocumentSnapshots.size()) {
                                                        resultList.sort((s1, s2) -> {
                                                            Integer sid1 = Integer.parseInt(s1.split(": ")[1].split("\n")[0]);
                                                            Integer sid2 = Integer.parseInt(s2.split(": ")[1].split("\n")[0]);
                                                            return sid1.compareTo(sid2);
                                                        });
                                                        StringBuilder resultBuilder = new StringBuilder();
                                                        for (String result4 : resultList) {
                                                            resultBuilder.append(result4);
                                                        }
                                                        queryTextResult.setText(resultBuilder.toString());
                                                    }
                                                })
                                                .addOnFailureListener(e -> Toast.makeText(getActivity(), "Query Operation Failed ", Toast.LENGTH_LONG).show());
                                    }
                                }
                            })
                            .addOnFailureListener(e -> Toast.makeText(getActivity(), "Query Operation Failed ", Toast.LENGTH_LONG).show());
                    break;
                case 5:
                    collectionReference = menuActivity.db.collection("Customer");
                    collectionReference.get().addOnSuccessListener(queryDocumentSnapshots -> {
                        StringBuilder result1 = new StringBuilder();
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Customer customer = documentSnapshot.toObject(Customer.class);
                            int cid = customer.getCid();
                            String cname = customer.getCname();
                            String cEmail = customer.getCustomerEmail();
                            result1.append("\n\n Customer ID: ").append(cid).append("\n Customer Name: ").append(cname).append("\n Customer Email: ").append(cEmail);
                        }
                        queryTextResult.setText(result1.toString());
                    }).addOnFailureListener((e) -> Toast.makeText(getActivity(), "Query Operation Failed ", Toast.LENGTH_LONG).show());
                    break;
                case 6:
                    List<Product> product1 = menuActivity.myDatabase.myDao().getQuery6();
                    for (Product i : product1) {
                        int productId = i.getId();
                        String productName = i.getName();
                        int productStock = i.getStock();
                        double productPrice = i.getPrice();
                        result.append("\n\n Product ID: ").append(productId).append("\n Product Name: ").append(productName).append("\n Product Stock: ").append(productStock).append("\n Product Price: ").append(productPrice);
                    }
                    queryTextResult.setText(result.toString());
                    break;
                case 7:
                    List<Supplier> supplier1 = menuActivity.myDatabase.myDao().getQuery7();
                    for (Supplier i : supplier1) {
                        String supplierName = i.getName();
                        result.append("\n\n Supplier Name: ").append(supplierName);
                    }
                    queryTextResult.setText(result.toString());
                    break;
                case 8:
                    List<Product> product2 = menuActivity.myDatabase.myDao().getQuery8();
                    for (Product i : product2) {
                        int productId = i.getId();
                        String productName = i.getName();
                        int productStock = i.getStock();
                        double productPrice = i.getPrice();
                        result.append("\n\n Product ID: ").append(productId).append("\n Product Name: ").append(productName).append("\n Product Stock: ").append(productStock).append("\n Product Price: ").append(productPrice);
                    }
                    queryTextResult.setText(result.toString());
                    break;
                case 9:
                    collectionReference = menuActivity.db.collection("Sales");
                    collectionReference.get().addOnSuccessListener(queryDocumentSnapshots -> {
                        double total = 0;
                        String result2 = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            if (documentSnapshot.contains("price")) {
                                Double priceDouble = documentSnapshot.getDouble("price");
                                double price;
                                if (priceDouble != null) {
                                    price = priceDouble;
                                    total += price;
                                }
                            }
                        }
                        result2 += "\n\n Total Sales amount: " + total;
                        queryTextResult.setText(result2);
                    }).addOnFailureListener((e) -> Toast.makeText(getActivity(), "Query Operation Failed ", Toast.LENGTH_LONG).show());
                    break;
                case 10:
                    collectionReference = menuActivity.db.collection("Sales");
                    collectionReference.orderBy("price", com.google.firebase.firestore.Query.Direction.DESCENDING)
                            .limit(1)
                            .get()
                            .addOnSuccessListener(queryDocumentSnapshots -> {
                                String result3 = "";
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    DocumentSnapshot doc = queryDocumentSnapshots.getDocuments().get(0);
                                    Double price = doc.getDouble("price");
                                    double highestPrice = 0.0;
                                    if (price != null) {
                                        highestPrice = price;
                                    }
                                    result3 += "\n\n Highest Price: " + highestPrice;
                                    queryTextResult.setText(result3);
                                }
                            }).addOnFailureListener((e) -> Toast.makeText(getActivity(), "Query Operation Failed ", Toast.LENGTH_LONG).show());
                    break;
                case 11:
                    CollectionReference salesRef = menuActivity.db.collection("Sales");
                    CollectionReference customersRef = menuActivity.db.collection("Customer");
                    salesRef.orderBy("cid")
                            .get()
                            .addOnSuccessListener(queryDocumentSnapshots -> {
                                Map<String, Integer> orderCountMap = new HashMap<>();
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    DocumentReference customerRef = documentSnapshot.getDocumentReference("cid");
                                    assert customerRef != null;
                                    String customerId = customerRef.getId();
                                    if (orderCountMap.containsKey(customerId)) {
                                        Integer orderCount = orderCountMap.get(customerId);
                                        if (orderCount != null) {
                                            orderCountMap.put(customerId, orderCount + 1);
                                        }
                                    } else {
                                        orderCountMap.put(customerId, 1);
                                    }
                                }

                                String customerIdWithMostOrders = Collections.max(orderCountMap.entrySet(),
                                        Map.Entry.comparingByValue()).getKey();

                                customersRef.document(customerIdWithMostOrders)
                                        .get()
                                        .addOnSuccessListener(documentSnapshot -> {
                                            String result6 = "";
                                            String customerName = documentSnapshot.getString("cname");
                                            result6 += "\n\n Customer with most Orders: " + customerName;
                                            queryTextResult.setText(result6);
                                        }).addOnFailureListener(e -> Toast.makeText(getActivity(), "Query Operation Failed ", Toast.LENGTH_LONG).show());
                            }).addOnFailureListener(e -> Toast.makeText(getActivity(), "Query Operation Failed ", Toast.LENGTH_LONG).show());

                    break;
            }
        });
        return view;
    }

}
