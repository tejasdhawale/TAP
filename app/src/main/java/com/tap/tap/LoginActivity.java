package com.tap.tap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private Button button5, reg;
    private EditText editText11;
    private EditText editText12;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = firebaseAuth.getInstance();

   //  byPass

//        startActivity(new Intent(getApplicationContext(), MainModule.class));
//


        if (firebaseAuth.getCurrentUser() != null) {
            //start profile activity

            finish();
            startActivity(new Intent(getApplicationContext(), MainModule.class));
        }
        Log.e(getClass().getName(), "onCreate:  firebaseAuth.getUid() :" + firebaseAuth.getUid());

        progressDialog = new ProgressDialog(this);
        button5 = (Button) findViewById(R.id.btn_lgn);
        reg = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        editText11 = (EditText) findViewById(R.id.edt_mail);
        editText12 = (EditText) findViewById(R.id.edt_pass);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

    }

    private void userLogin() {

        String email = editText11.getText().toString().trim();
        String password = editText12.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            // email is empty
            Toast.makeText(this, "Plaese Enter the Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {

            //password is empty
            Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Loggin in ...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainModule.class));
                        }
                    }
                });
    }


}
