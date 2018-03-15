package com.tap.tap;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by R4J35H V415HN4V on 19-03-2017.
 */

public class OnlineTest extends AppCompatActivity {
    ListView simpleList;
    // String[] questions = {"SFDADBFASDFSD","Q2 DFDASDFSDF","Q3SDFSDF"};

    String testnumber;


    long seed = System.nanoTime();
    String questype;
    Button submit, typeselect;

    //FirebaseDatabase database;

    ArrayList<String> arr = new ArrayList<>();
    ArrayList<String> arr1 = new ArrayList<>();
    ArrayList<String> arr2 = new ArrayList<>();
    ArrayList<String> arr3 = new ArrayList<>();
    ArrayList<String> arr4 = new ArrayList<>();
    ArrayList<String> arr5 = new ArrayList<>();

    private FirebaseAuth firebaseAuth;
    private StorageReference storageRef;
    String[] key5;
    String UID;
    RadioButton one;
    RadioButton two;
    RadioButton three;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onlinetest);
        one = (RadioButton) findViewById(R.id.radioButton16);
        two = (RadioButton) findViewById(R.id.radioButton15);
        three = (RadioButton) findViewById(R.id.radioButton14);


        testnumber = getIntent().getStringExtra("TEST_NO");


        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure?");
        builder1.setCancelable(false);


        loadmain();
        one.setChecked(true);


        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();

                        final ProgressDialog progressDialog = new ProgressDialog(OnlineTest.this);
                        progressDialog.setTitle("Evaluation Results....");
                        progressDialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                storeresult();
                                progressDialog.dismiss();
                            }
                        }, 2000);


                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        final AlertDialog alert11 = builder1.create();

        loadmain();


        //  myRef = database.getReference("questions/"+questype);


// get the string array from string.xml file

// get the reference of ListView and Button
        simpleList = (ListView) findViewById(R.id.simpleListView);
        submit = (Button) findViewById(R.id.button2);
        //  typeselect=(Button)findViewById(R.id.button3);

//expandable list

        simpleList.invalidate();


// set the adapter to fill the data in the ListView


// perform setOnClickListerner event on Button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";
                alert11.show();


            }
        });
    }

    void storeresult() {
        int right = 0, wrong = 0, unutm = 0, total = 0;
// get the value of selected answers from custom adapter
        for (int i = 0; i < CustomAdapter.selectedAnswers.size(); i++) {
            if (CustomAdapter.selectedAnswers.get(i).equals(arr5.get(i))) {
                right++;
            } else if (CustomAdapter.selectedAnswers.get(i).equals("Not Attempted")) {
                unutm++;
            } else {
                wrong++;
            }
            total++;
        }


        firebaseAuth = FirebaseAuth.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();
        if (firebaseAuth.getCurrentUser() == null) {

            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }


        FirebaseUser user = firebaseAuth.getCurrentUser();

        UID = user.getUid().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRefmain = database.getReference("Test_results");

        DatabaseReference myRef = myRefmain.child(UID.toString());
        DatabaseReference testno = myRef.child(testnumber);

        DatabaseReference myRefTest;
        DatabaseReference myRefUser, middle;


        if (one.isChecked()) {
            myRefTest = testno.child("verbal");
            middle = myRefTest.child(UID.toString());


            myRefUser = middle.child("testmarks");
            myRefUser.setValue(Integer.toString(right));

            myRefUser = middle.child("outoff");
            myRefUser.setValue(Integer.toString(total));

            myRefUser = middle.child("unattempted");
            myRefUser.setValue(Integer.toString(unutm));

            myRefUser = middle.child("wrong");
            myRefUser.setValue(Integer.toString(wrong));


        }
        if (two.isChecked()) {
            myRefTest = testno.child("logical");
            middle = myRefTest.child(UID.toString());

            myRefUser = middle.child("testmarks");
            myRefUser.setValue(Integer.toString(right));

            myRefUser = middle.child("outoff");
            myRefUser.setValue(Integer.toString(total));

            myRefUser = middle.child("unattempted");
            myRefUser.setValue(Integer.toString(unutm));


            myRefUser = middle.child("wrong");
            myRefUser.setValue(Integer.toString(wrong));
        }

        if (three.isChecked()) {
            myRefTest = testno.child("cont");
            middle = myRefTest.child(UID.toString());

            myRefUser = middle.child("testmarks");
            myRefUser.setValue(Integer.toString(right));

            myRefUser = middle.child("outoff");
            myRefUser.setValue(Integer.toString(total));

            myRefUser = middle.child("unattempted");
            myRefUser.setValue(Integer.toString(unutm));

            myRefUser = middle.child("wrong");
            myRefUser.setValue(Integer.toString(wrong));

        }
        // display the message on screen with the help of Toast.
        Toast.makeText(getApplicationContext(), "right: " + right + " wrong: " + wrong + " Not attempted: " + unutm, Toast.LENGTH_LONG).show();


    }

    void loadmain() {

        questype = "verbal";

        questionload(questype);
//questype="verbal";
        one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// set Yes values in ArrayList if RadioButton is checked
                if (isChecked)
                    questype = "verbal";
                questionload(questype);
            }
        });
        two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// set Yes values in ArrayList if RadioButton is checked
                if (isChecked)
                    questype = "logical";

                questionload(questype);
            }
        });
        three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// set Yes values in ArrayList if RadioButton is checked
                if (isChecked)
                    questype = "cont";

                questionload(questype);
            }
        });


    }


    public void questionload(String questype) {

        DatabaseReference myRef;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("questions/" + questype);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Getting data...");
        progressDialog.show();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arr.clear();
                arr1.clear();
                arr2.clear();
                arr3.clear();
                arr4.clear();
                arr5.clear();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Post data = child.getValue(Post.class);

                    arr.add(data.getQuestion());
                    arr1.add(data.getOp1());
                    arr2.add(data.getOp2());
                    arr3.add(data.getOp3());
                    arr4.add(data.getOp4());
                    arr5.add(data.getRight());

                    Log.d(getClass().getName(), "getQuestion : "+data.getQuestion());
                    Log.d(getClass().getName(), "getOp1: "+data.getOp1());
                    Log.d(getClass().getName(), "getOp2: "+data.getOp2());
                    Log.d(getClass().getName(), "getOp3: "+data.getOp3());
                    Log.d(getClass().getName(), "getOp4: "+data.getOp4());
                    Log.d(getClass().getName(), "getOp right ans : "+data.getRight());

                    Random r1 = new Random(seed);

                    Collections.shuffle(arr, r1);
                    r1 = new Random(seed);
                    Collections.shuffle(arr1, r1);
                    r1 = new Random(seed);
                    Collections.shuffle(arr2, r1);
                    r1 = new Random(seed);
                    Collections.shuffle(arr3, r1);
                    r1 = new Random(seed);
                    Collections.shuffle(arr4, r1);
                    r1 = new Random(seed);
                    Collections.shuffle(arr5, r1);

                    //tv1.setText(dataSnapshot.getKey());
                    //tv.setText(data.getRight());
                }
                String[] key = arr.toArray(new String[arr.size()]);
                String[] key1 = arr1.toArray(new String[arr1.size()]);
                String[] key2 = arr2.toArray(new String[arr2.size()]);
                String[] key3 = arr3.toArray(new String[arr3.size()]);
                String[] key4 = arr4.toArray(new String[arr4.size()]);
                key5 = arr5.toArray(new String[arr5.size()]);


                // String []key1=arr.toArray(new String[arr1.size()]);
                // String []key2=arr.toArray(new String[arr2.size()]);
                // String []key3=arr.toArray(new String[arr3.size()]);
                // String []key4=arr.toArray(new String[arr4.size()]);

                final CustomAdapter quest = new CustomAdapter(getApplicationContext(), key, key1, key2, key3, key4, key5);

                simpleList.setAdapter(quest);
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
