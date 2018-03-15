package com.tap.tap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail, path1;
    private Button buttonLogout, btn_upload, submit;
    private static final int PICK_FILE_REQUEST = 1;
    private StorageReference storageRef;
    Uri uri;
    Uri filepath;
    EditText et1, et2, et3, et4;
    CheckBox ck1, ck2, ck3, ck4, ck5;
    String UID;
    private static final int FILE_SELECT_CODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        et1 = (EditText) findViewById(R.id.username);
        et2 = (EditText) findViewById(R.id.skills);
        et3 = (EditText) findViewById(R.id.volunteer);
        et4 = (EditText) findViewById(R.id.activities);
        ck1 = (CheckBox) findViewById(R.id.checkBox1);
        ck2 = (CheckBox) findViewById(R.id.checkBox2);
        ck3 = (CheckBox) findViewById(R.id.checkBox3);
        ck4 = (CheckBox) findViewById(R.id.checkBox4);
        ck5 = (CheckBox) findViewById(R.id.checkBox5);
        path1 = (TextView) findViewById(R.id.text);
        btn_upload = (Button) findViewById(R.id.b_upload);
        submit = (Button) findViewById(R.id.submit);
        firebaseAuth = FirebaseAuth.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();
        if (firebaseAuth.getCurrentUser() == null) {

            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        UID = user.getUid().toString();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this);
                progressDialog.setTitle("updating profile");
                progressDialog.show();
                StorageReference riversRef = storageRef.child("resume/" + UID + ".pdf");
                try {
                    riversRef.putFile(uri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // Get a URL to the uploaded content

                                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                    String resumeurl = downloadUrl.toString();

                                    updateprofile();


                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads
                                    // ...
                                }
                            });
                    Toast.makeText(ProfileActivity.this, "Applied sucessfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ProfileActivity.this, "Some error occured", Toast.LENGTH_SHORT).show();

                }


                progressDialog.dismiss();
                Toast.makeText(ProfileActivity.this, "Profile Updated ", Toast.LENGTH_LONG).show();

            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();


            }
        });


    }

    void updateprofile() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRefmain = database.getReference("User_Profile");

        DatabaseReference myRef = myRefmain.child(UID);

        DatabaseReference myRefUser = myRef.child("fullNmae");
        myRefUser.setValue(et1.getText().toString().trim());

        myRefUser = myRef.child("skills");
        myRefUser.setValue(et2.getText().toString().trim());

        myRefUser = myRef.child("Vexp");
        myRefUser.setValue(et3.getText().toString().trim());

        myRefUser = myRef.child("aands");
        myRefUser.setValue(et4.getText().toString().trim());


        String jobposte = "";
        if (ck1.isChecked() == true) {
            jobposte = jobposte + "  " + ck1.getText().toString();
        }
        if (ck2.isChecked() == true) {
            jobposte = jobposte + "  " + ck2.getText().toString();
        }
        if (ck3.isChecked() == true) {
            jobposte = jobposte + "  " + ck3.getText().toString();
        }
        if (ck4.isChecked() == true) {
            jobposte = jobposte + "  " + ck4.getText().toString();
        }
        if (ck5.isChecked() == true) {
            jobposte = jobposte + "  " + ck5.getText().toString();
        }


        myRefUser = myRef.child("jobpost");
        myRefUser.setValue(jobposte);

    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    uri = data.getData();
                    Log.d("TAG", "File Uri: " + uri.toString());
                    // Get the path

                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
