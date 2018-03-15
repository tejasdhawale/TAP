package com.tap.tap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Akash on 03/03/2017.
 */
public class HigherStudy extends Activity implements View.OnClickListener{

    Button me,ms,sm,news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.higher_study);

        sm=(Button)findViewById(R.id.studymaterial);
        me=(Button)findViewById(R.id.me);
        ms=(Button)findViewById(R.id.ms);
        news=(Button)findViewById(R.id.news);
        sm.setOnClickListener(this);
        me.setOnClickListener(this);
        ms.setOnClickListener(this);
        news.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.studymaterial){
            Intent study=new Intent(this,StudyMat.class);
            startActivity(study);
            }
        if(v.getId()== R.id.me){
            Intent master=new Intent(this,TopU.class);
            startActivity(master);

        }
        if(v.getId()== R.id.ms){
            Intent masters=new Intent(this,TopMsU.class);
            startActivity(masters);

        }
        if(v.getId()== R.id.news){
            Intent news=new Intent(this,LatestNews.class);
            startActivity(news);

        }
    }
}

