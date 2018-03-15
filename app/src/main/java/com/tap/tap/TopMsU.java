package com.tap.tap;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
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

public class TopMsU extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    TextView tv;
    ArrayList<String> arr= new ArrayList<>();
    ArrayList<String> arr1= new ArrayList<>();

    ListView listView;
    String name;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topmsu);
tv=(TextView)findViewById(R.id.textView5);
        myRef = database.getReference("topmsu");



        //  arr = new ArrayList<>();
       listView=(ListView)findViewById(R.id.listView1);
        //tv=(TextView)findViewById(R.id.textView4);
      //  final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,arr1);
     //   listView.setAdapter(arrayAdapter);

        // listView.setAdapter(arrayAdapter);
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
                Log.d(getClass().getName(), "key ms: "+key);
                Log.d(getClass().getName(), "value ms: "+value);
                String []key1=arr.toArray(new String[arr.size()]);
                String []value1=arr1.toArray(new String[arr1.size()]);

                CustomAdapterone customAdapter = new CustomAdapterone(getApplicationContext(), key1,value1);
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
                Toast.makeText(TopMsU.this , "error "+databaseError, Toast.LENGTH_LONG).show();

            }
        });

    }
}
