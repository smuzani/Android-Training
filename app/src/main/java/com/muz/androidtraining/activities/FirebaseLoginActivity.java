package com.muz.androidtraining.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.muz.androidtraining.R;

public class FirebaseLoginActivity extends AppCompatActivity {

    Button btSignup, btForgot, btLogin;
    TextInputEditText etPassword, etEmail;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_login);
        btSignup = (Button) findViewById(R.id.bt_signup);
        btForgot = (Button) findViewById(R.id.bt_forgot);
        btLogin = (Button) findViewById(R.id.bt_login);
        etEmail = (TextInputEditText) findViewById(R.id.et_email);
        etPassword = (TextInputEditText) findViewById(R.id.et_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Log In");

        auth = FirebaseAuth.getInstance();
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(FirebaseLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(FirebaseLoginActivity.this, "Authentication failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(FirebaseLoginActivity.this, "Authentication success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirebaseLoginActivity.this, FirebaseSignUpActivity.class);
                startActivity(intent);
            }
        });

        btForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
