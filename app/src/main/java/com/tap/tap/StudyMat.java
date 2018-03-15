package com.tap.tap;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by R4J35H V415HN4V on 18-03-2017.
 */

public class StudyMat  extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    ArrayList<String> arr= new ArrayList<>();
    ArrayList<String> arr1= new ArrayList<>();
    ListView listView;



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studymat);

        myRef = database.getReference("StudyMat");
        //  arr = new ArrayList<>();
        listView=(ListView)findViewById(R.id.lv1);
        //Linkify.addLinks((Spannable) listView,Linkify.WEB_URLS);
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Getting data...");
        progressDialog.show();
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class).toString();
                String key=dataSnapshot.getKey();

                arr.add(key);
                arr1.add(value);
                String []key1=arr.toArray(new String[arr.size()]);
                String []value1=arr1.toArray(new String[arr1.size()]);

                CustomAdapterTwo customAdapter = new CustomAdapterTwo(getApplicationContext(), key1,value1);
                listView.setAdapter(customAdapter);

                customAdapter.notifyDataSetChanged();

                progressDialog.dismiss();
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(StudyMat.this , "error "+databaseError, Toast.LENGTH_LONG).show();

            }
        });

      //  listView.setOnItemClickListener((AdapterView.OnItemClickListener) StudyMat.this);

    }

}