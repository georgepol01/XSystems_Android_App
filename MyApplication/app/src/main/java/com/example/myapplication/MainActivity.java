package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private TextView username;

    private TextView password;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Button login_button;

        login_button = findViewById(R.id.loginbutton);

        login_button.setOnClickListener(v -> {
            String enteredUsername = username.getText().toString();
            String enteredPassword = password.getText().toString();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Employee")
                    .whereEqualTo("username", enteredUsername)
                    .whereEqualTo("password", enteredPassword)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {
                            Intent intent = new Intent(MainActivity.this, menuActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                        username.setText("");
                        password.setText("");
                        if (getCurrentFocus() != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        }
                    });
        });


    }


}
