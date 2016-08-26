package com.muz.androidtraining.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.muz.androidtraining.R;

public class FirebaseForgotPasswordActivity extends AppCompatActivity {

    Button btSubmit;
    EditText etEmail;
    TextInputLayout tilEmail;
    private FirebaseAuth auth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_forgot_password);
        btSubmit = (Button) findViewById(R.id.bt_reset_password);
        etEmail = (EditText) findViewById(R.id.et_email);
        tilEmail = (TextInputLayout) findViewById(R.id.til_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Log In");

        auth = FirebaseAuth.getInstance();
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                if (!validateForm(email)){
                    return;
                }

                pd = new ProgressDialog(FirebaseForgotPasswordActivity.this);
                pd.setMessage("Loading…");
                pd.show();

                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                pd.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(FirebaseForgotPasswordActivity.this, "An email has been sent with instruction to reset your password.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(FirebaseForgotPasswordActivity.this, "Failed to send reset email! \n" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private boolean validateForm(String email) {
        boolean isValid = true;
        tilEmail.setErrorEnabled(false);
        if (email.equals("")){
            tilEmail.setError("Please enter e-mail");
            isValid = false;
        }

        return isValid;
    }
}
