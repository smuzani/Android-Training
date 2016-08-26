package com.muz.androidtraining.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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

public class FirebaseSignUpActivity extends AppCompatActivity {

    Button btSignup, btForgot, btLogin;
    TextInputEditText etPassword, etPassword2, etEmail;
    TextInputLayout tilEmail, tilPassword, tilPassword2;
    private FirebaseAuth auth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_sign_up);
        btSignup = (Button) findViewById(R.id.bt_signup);
        btForgot = (Button) findViewById(R.id.bt_forgot);
        btLogin = (Button) findViewById(R.id.bt_login);
        etEmail = (TextInputEditText) findViewById(R.id.et_email);
        etPassword = (TextInputEditText) findViewById(R.id.et_password);
        etPassword2 = (TextInputEditText) findViewById(R.id.et_password2);
        tilEmail = (TextInputLayout) findViewById(R.id.til_email);
        tilPassword = (TextInputLayout) findViewById(R.id.til_password);
        tilPassword2 = (TextInputLayout) findViewById(R.id.til_password2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign Up");

        auth = FirebaseAuth.getInstance();
        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String password2 = etPassword2.getText().toString().trim();

                if (!validateForm(email, password, password2)){
                    return;
                }

                // Show loading dialog
                pd = new ProgressDialog(FirebaseSignUpActivity.this);
                pd.setMessage("Loading…");
                pd.show();

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(FirebaseSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pd.dismiss();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(FirebaseSignUpActivity.this, "Authentication failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(FirebaseSignUpActivity.this, "Authentication success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirebaseSignUpActivity.this, FirebaseLoginActivity.class);
                startActivity(intent);
            }
        });

        btForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirebaseSignUpActivity.this, FirebaseForgotPasswordActivity.class);
                startActivity(intent);
            }
        });


    }

    private boolean validateForm(String email, String password, String password2) {
        boolean isValid = true;
        tilEmail.setErrorEnabled(false);
        tilPassword.setErrorEnabled(false);
        tilPassword2.setErrorEnabled(false);
        if (email.equals("")){
            tilEmail.setError("Please enter email");
            isValid = false;
        }

        if (password.length() < 8){
            tilPassword.setError("Password must be at least 8 characters");
            isValid = false;
        }

        if (password.equals("")){
            tilPassword.setError("Please enter password");
            isValid = false;
        }

        if (password2.equals("")){
            tilPassword2.setError("Please confirm password");
            isValid = false;
        }

        if (!password.equals(password2)){
            tilPassword2.setError("Passwords do not match");
            isValid = false;
        }

        return isValid;
    }
}
