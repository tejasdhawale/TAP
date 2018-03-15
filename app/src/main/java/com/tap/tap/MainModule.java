package com.tap.tap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Ganesh on 2/6/2017.
 */
public class MainModule extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    Button cp, notice, hs, ap, ot, crtprofile, resultbtn;
    TextView usermail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_module);

        usermail = (TextView) findViewById(R.id.txtUsermail);
        crtprofile = (Button) findViewById(R.id.createprofile);
        hs = (Button) findViewById(R.id.higherstudy);
        notice = (Button) findViewById(R.id.notice);
        ot = (Button) findViewById(R.id.onlinetest);
        resultbtn = (Button) findViewById(R.id.resultbutton);

        /**  cp = (Button) findViewById(R.id.createprofile);


         ap = (Button) findViewById(R.id.preparation);

         logout.setOnClickListener(this);
         notice.setOnClickListener(this);
         hs.setOnClickListener(this);
         ap.setOnClickListener(this);
         ot.setOnClickListener(this);
         Intent intent=getIntent();
         String name=intent.getStringExtra("name");
         System.out.println(name);
         **/
        try {
            firebaseAuth = FirebaseAuth.getInstance();

            if (firebaseAuth.getCurrentUser() == null) {

                finish();
                startActivity(new Intent(this, LoginActivity.class));
            }

            FirebaseUser user = firebaseAuth.getCurrentUser();


            usermail.setText("Welcome " + user.getEmail());

        } catch (Exception e) {
            Toast.makeText(MainModule.this, "someproble " + e.toString(), Toast.LENGTH_SHORT).show();

        }

        crtprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainModule.this, ProfileActivity.class));
            }
        });
        hs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainModule.this, HigherStudy.class));
            }
        });


        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainModule.this, Notices.class));

            }
        });
        ot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainModule.this, TestMid.class));

            }
        });

        resultbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainModule.this, ResultAnalysis.class));

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(MainModule.this, LoginActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


/**
 * if(v.getId()==R.id.createprofile)
 * {
 * Intent profile=new Intent(this,Profile.class);
 * startActivity(profile);
 * }
 * if(v.getId()==R.id.notice)
 * {
 * Intent notice=new Intent(this,MainActivity.class);
 * startActivity(notice);
 * }
 * <p>
 * if(v.getId()==R.id.higherstudy)
 * {
 * Intent study=new Intent(this,HigherStudy.class);
 * startActivity(study);
 * }
 * <p>
 * if(v.getId()==R.id.preparation)
 * {
 * Intent prepration=new Intent(this,MainActivity.class);
 * startActivity(prepration);
 * }
 * <p>
 * if(v.getId()==R.id.onlinetest)
 * {
 * Intent test=new Intent(this,Test.class);
 * startActivity(test);
 * }
 **/


