package com.tap.tap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Ganesh on 2/5/2017.
 */
public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText edt_name, edt_username, edt_pass, edt_mail, edt_contact, edt_gen, edt_class, edt_ssc, edt_hsc, edt_diploma, edt_degree, edt_livekt, edt_Dkt, edt_yrGap, edt_passYr;
    Button btn;
    TextView txt;
    String s1, pass, email, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15;
    private FirebaseAuth firebaseAuth;

    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        firebaseAuth = firebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //start profile activity
            finish();
            startActivity(new Intent(getApplicationContext(), MainModule.class));
        }
        progressDialog = new ProgressDialog(this);

        edt_name = (EditText) findViewById(R.id.student_name);
        edt_username = (EditText) findViewById(R.id.username);
        edt_pass = (EditText) findViewById(R.id.password);
        edt_mail = (EditText) findViewById(R.id.email_id);
        edt_contact = (EditText) findViewById(R.id.contact);
        edt_gen = (EditText) findViewById(R.id.gender);
//        edt_class = (EditText) findViewById(R.id.Class);
//        edt_ssc = (EditText) findViewById(R.id.SSC);
//        edt_hsc = (EditText) findViewById(R.id.HSC);
//        edt_diploma = (EditText) findViewById(R.id.diploma);
        edt_degree = (EditText) findViewById(R.id.degree);
//        edt_livekt = (EditText) findViewById(R.id.live_kt);
//        edt_Dkt = (EditText) findViewById(R.id.dead_kt);
//        edt_yrGap = (EditText) findViewById(R.id.year_gap);
        edt_passYr = (EditText) findViewById(R.id.passing_year);
        btn = (Button) findViewById(R.id.submit);
        btn.setOnClickListener(this);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            //Go to login
        } else {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Toast.makeText(this, "uid : " + uid, Toast.LENGTH_SHORT).show();
        }
    }


    private void registerUser() {
//        s1 = edt_name.getText().toString().trim();
        pass = edt_pass.getText().toString().trim();
        email = edt_mail.getText().toString().trim();
//        s4 = edt_username.getText().toString().trim();
//        s5 = edt_contact.getText().toString().trim();
//        s6 = edt_gen.getText().toString().trim();
//        s7 = edt_class.getText().toString().trim();
//        s8 = edt_ssc.getText().toString().trim();
//        s9 = edt_hsc.getText().toString().trim();
//        s10 = edt_diploma.getText().toString().trim();
//        s11 = edt_degree.getText().toString().trim();
//        s12 = edt_livekt.getText().toString().trim();
//        s13 = edt_Dkt.getText().toString().trim();
//        s14 = edt_yrGap.getText().toString().trim();
//        s15 = edt_passYr.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            // email is empty
            Toast.makeText(this, "Plaese Enter the Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {

            //password is empty
            Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registring User..");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "REGISTERD SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(Register.this, " user : " + user, Toast.LENGTH_SHORT).show();
                            if (firebaseAuth.getCurrentUser() != null) {
                                //start profile activity

                                finish();
                                String UID = user.getUid();

                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference myRefmain = database.getReference("USERS");

                                DatabaseReference myRef = myRefmain.child(UID);

//                                DatabaseReference myRefUser = myRef.child("username");
//                                myRefUser.setValue(s4);
//
//                                myRefUser = myRef.child("gender");
//                                myRefUser.setValue(s6);
//
//                                myRefUser = myRef.child("mail_id");
//                                myRefUser.setValue(s3);
//
//                                myRefUser = myRef.child("Phone_no");
//                                myRefUser.setValue(s5);
//
//                                myRefUser = myRef.child("degree");
//                                myRefUser.setValue(s11);
//                                myRefUser = myRef.child("liveKT");
//                                myRefUser.setValue(s12);
//
//                                myRefUser = myRef.child("DeadKT");
//                                myRefUser.setValue(s13);
//
//                                myRefUser = myRef.child("YearGap");
//                                myRefUser.setValue(s14);
//                                myRefUser = myRef.child("passingyr");
//                                myRefUser.setValue(s15);

                                startActivity(new Intent(getApplicationContext(), MainModule.class));
                            }
                            Toast.makeText(Register.this, "REGISTERD SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Register.this, "REGISTRATION FAILED", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                registerUser();
        }

    }


}

