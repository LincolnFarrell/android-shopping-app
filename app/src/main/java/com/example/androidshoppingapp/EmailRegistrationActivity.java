package com.example.androidshoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class EmailRegistrationActivity extends AppCompatActivity {

    private EditText tbxEmail, tbxPassword;
    private Button btnRegister;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_registration);
        auth = FirebaseAuth.getInstance();

        tbxEmail = findViewById(R.id.tbxEmail);
        tbxPassword = findViewById(R.id.tbxPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> createUser());
    }

    private void createUser()
    {
        String email = tbxEmail.getText().toString();
        String password = tbxPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Email required!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Password required!", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be at least 6 characters long!", Toast.LENGTH_LONG).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Registration success!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EmailRegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Registration failed!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
