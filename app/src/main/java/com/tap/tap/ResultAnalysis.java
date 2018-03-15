package com.tap.tap;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

/**
 * Created by R4J35H V415HN4V on 26-03-2017.
 */

public class ResultAnalysis extends AppCompatActivity {


    String[] xdata={"Right","Wring","Un Attempted"};
    PieChart pieChart1;
    LineChart lineChar1;
ArrayList<String>testdata=new ArrayList<>();
String testum;
    FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;

    String UID;
    ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
   // String type="verbal";
    TextView tv1,tv2,tv3,tv4;
ArrayList<Float>floatdata=new ArrayList<>();
    public  String outoff="",wrong="",unattempted="",testmarks;
    public float floatoutoffq;
    public float wrongq;
    public float unattemptedq;
    DatabaseReference myRef,myRefOld,myRef1,myRef2;
    public static float testmarkq1,testmarkq2,testmarkq3;
    private static float outoffq1,outoffq2,outoffq3;
    protected BarChart mChart;
    public static float[] testmark={0,0,0};
    Button b1,b2,b3,b4,b5;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultanalysis);
        pieChart1 = (PieChart) findViewById(R.id.pichart1);
        tv1 = (TextView) findViewById(R.id.textView15);
        tv2 = (TextView) findViewById(R.id.textView14);
        tv3 = (TextView) findViewById(R.id.textView13);
b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b3=(Button)findViewById(R.id.b3);
        b4=(Button)findViewById(R.id.b4);
        b5=(Button)findViewById(R.id.b5);


        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        UID = user.getUid().toString();
        database = FirebaseDatabase.getInstance();


        myRefOld= database.getReference("Test_results/" + UID);



        mChart = (BarChart) findViewById(R.id.graph1);
        //mChart.setOnChartValueSelectedListener();

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);


        final float[] ydata1 = {testmarkq1, testmarkq2, testmarkq3};



        addDataSet(pieChart1, ydata1);
        pieChart1.invalidate();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChart.notifyDataSetChanged();

                myRef1 =myRefOld.child("test1");
                testum="test1";
                gettestdata("test1");
                float[]testmark1=gettestdata(testum);
                yVals1.add(new BarEntry(1,testmark1[0]));
                yVals1.add(new BarEntry(2,testmark1[1]));
                yVals1.add(new BarEntry(3,testmark1[2]));
               // tv1.setText(Float.toString(testmark1[0]));
                yVals1.add(new BarEntry(1,outoffq1));
                yVals1.add(new BarEntry(2,outoffq2));
                yVals1.add(new BarEntry(3,outoffq3));
                BarDataSet set1 = new BarDataSet(yVals1, "Test Result Analysis");

                set1.setDrawIcons(false);

                set1.setColors(rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"));
                BarData data = new BarData(set1);

                mChart.setData(data);

                mChart.getData().notifyDataChanged();
                mChart.invalidate();
                final float[] ydata1 = {testmarkq1, testmarkq2, testmarkq3};

                addDataSet(pieChart1, ydata1);
                pieChart1.invalidate();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                gettestdata("test2");
                testum="test2";


                float[]testmark2= gettestdata(testum);
                yVals1.add(new BarEntry(4,testmark2[0]));
                yVals1.add(new BarEntry(5,testmark2[1]));
                yVals1.add(new BarEntry(6,testmark2[2]));
                yVals1.add(new BarEntry(4,outoffq1));
                yVals1.add(new BarEntry(5,outoffq2));
                yVals1.add(new BarEntry(6,outoffq3));
               // setData();
                BarDataSet set1 = new BarDataSet(yVals1, "Test Result Analysis");

                set1.setDrawIcons(true);

                set1.setColors(rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"));
                BarData data = new BarData(set1);
                //
                mChart.notifyDataSetChanged();
                mChart.setData(data);
                final float[] ydata1 = {testmarkq1, testmarkq2, testmarkq3};

                mChart.invalidate();
                mChart.getData().notifyDataChanged();
                addDataSet(pieChart1, ydata1);
                pieChart1.invalidate();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettestdata("test3");
                testum="test3";
                testum="test3";
                float[]testmark3= gettestdata(testum);
                yVals1.add(new BarEntry(7,testmark3[0]));
                yVals1.add(new BarEntry(8,testmark3[1]));
                yVals1.add(new BarEntry(9,testmark3[2]));
                yVals1.add(new BarEntry(7,outoffq1));
                yVals1.add(new BarEntry(8,outoffq2));
                yVals1.add(new BarEntry(9,outoffq3));
               // setData();
                BarDataSet set1 = new BarDataSet(yVals1, "Test Result Analysis");

                set1.setDrawIcons(false);

                set1.setColors(rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"));
                BarData data = new BarData(set1);
                // mChart.getData().notifyDataChanged();
                mChart.notifyDataSetChanged();
                mChart.setData(data);
                mChart.invalidate();
                final float[] ydata1 = {testmarkq1, testmarkq2, testmarkq3};

                addDataSet(pieChart1, ydata1);
                pieChart1.invalidate();

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettestdata("test4");
                testum="test4";
                float[]testmark4= gettestdata(testum);
                yVals1.add(new BarEntry(10,testmark4[0]));
                yVals1.add(new BarEntry(11,testmark4[1]));
                yVals1.add(new BarEntry(12,testmark4[2]));
                yVals1.add(new BarEntry(10,outoffq1));
                yVals1.add(new BarEntry(11,outoffq2));
                yVals1.add(new BarEntry(12,outoffq3));
                pieChart1.invalidate();

                //setData();

                BarDataSet set1 = new BarDataSet(yVals1, "Test Result Analysis");

                set1.setDrawIcons(false);

                set1.setColors(rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"));
                BarData data = new BarData(set1);
                // mChart.getData().notifyDataChanged();
                mChart.notifyDataSetChanged();
                mChart.setData(data);

                mChart.invalidate();
                final float[] ydata1 = {testmarkq1, testmarkq2, testmarkq3};

                addDataSet(pieChart1, ydata1);
                pieChart1.invalidate();


            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gettestdata("test5");

              //  setData();
                testum="test5";
                float[]testmark5= gettestdata(testum);
                yVals1.add(new BarEntry(13,testmark5[0]));
                yVals1.add(new BarEntry(14,testmark5[1]));
                yVals1.add(new BarEntry(15,testmark5[2]));
                yVals1.add(new BarEntry(13,outoffq1));
                yVals1.add(new BarEntry(14,outoffq2));
                yVals1.add(new BarEntry(15,outoffq3));

                BarDataSet set1 = new BarDataSet(yVals1, "Test Result Analysis");

                set1.setDrawIcons(false);

                set1.setColors(rgb("#2ecc71"), rgb("#f1c40f"), rgb("#e74c3c"));
                BarData data = new BarData(set1);
                // mChart.getData().notifyDataChanged();
                mChart.notifyDataSetChanged();
                mChart.setData(data);
                mChart.invalidate();
                final float[] ydata1 = {testmarkq1, testmarkq2, testmarkq3};

                addDataSet(pieChart1, ydata1);
                pieChart1.invalidate();
            }
        });



    }


/**
    private void setData() {

       // float start = 1f;


        //float a[]={0f,0f,0f};


        //float b[]={0f,0f,0f};


        //float c[]={0f,0f,0f};
        /**
        //float d[]={0f,0f,0f};

        //float e[]={0f,0f,0f};






        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {

            set1.setValues(yVals1);

        } else {


            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets,);
            data.setValueTextSize(10f);
           // data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);


        }
    }**/
    public static int rgb(String hex) {
        int color = (int) Long.parseLong(hex.replace("#", ""), 16);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        return Color.rgb(r, g, b);
    }


float[] gettestdata(String testno1)
{


    myRef1 =myRefOld.child(testno1);
        myRef=myRef1.child("verbal");

    ProgressDialog progressDialog= new ProgressDialog(ResultAnalysis.this);
    progressDialog.setTitle("Getting data...");
    progressDialog.show();
    myRef.addValueEventListener(new ValueEventListener() {


        //public float testmarkq;

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {


            if (dataSnapshot != null) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Post data = child.getValue(Post.class);

//op1=data.gfloatetOp1();
                    outoff = data.getOutoff();
                    testmarks = data.getTestmarks();
                    wrong = data.getWrong();
                    unattempted = data.getUnattempted();

                    ResultAnalysis.outoffq1 = Float.parseFloat(outoff);
                    ResultAnalysis.testmarkq1 = Float.parseFloat(testmarks);// / outoffq1;
                    wrongq = 100 * Float.parseFloat(wrong) / outoffq1;
                    unattemptedq = 100 * Float.parseFloat(unattempted) / outoffq1;

                    //floatdata.add(testmarkq);

                }
                //
                // testdata.add(testmarks);
                // ydata = ydata1;

//getdataone(

            }else{
                Toast.makeText(getApplicationContext(), "no data available", Toast.LENGTH_LONG).show();

            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });


    myRef=myRef1.child("logical");
    myRef.addValueEventListener (new ValueEventListener() {


        //public float testmarkq;

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {



            if (dataSnapshot != null) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Post data = child.getValue(Post.class);

//op1=data.gfloatetOp1();
                    outoff = data.getOutoff();
                    testmarks = data.getTestmarks();
                    wrong = data.getWrong();
                    unattempted = data.getUnattempted();

                    ResultAnalysis.outoffq2 = Float.parseFloat(outoff);
                    ResultAnalysis.testmarkq2 = Float.parseFloat(testmarks); // outoffq2;
                    wrongq = 100 * Float.parseFloat(wrong) / outoffq2;
                    unattemptedq = 100 * Float.parseFloat(unattempted) / outoffq2;

                    //floatdata.add(testmarkq);

                }
                // tv2.setText(Float.toString(testmarkq2));




            }else{
                Toast.makeText(getApplicationContext(), "no data available", Toast.LENGTH_LONG).show();

            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });





    myRef=myRef1.child("cont");

    myRef.addValueEventListener(new ValueEventListener() {


        //public float testmarkq;

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {



            if (dataSnapshot != null) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Post data = child.getValue(Post.class);
                    outoff = data.getOutoff();
                    testmarks = data.getTestmarks();
                    wrong = data.getWrong();
                    unattempted = data.getUnattempted();

                    ResultAnalysis.outoffq3 = Float.parseFloat(outoff);
                    ResultAnalysis.testmarkq3 = Float.parseFloat(testmarks);// outoffq3;
                    wrongq = 100 * Float.parseFloat(wrong) / outoffq3;
                    unattemptedq = 100 * Float.parseFloat(unattempted) / outoffq3;


                }

                // tv3.setText(Float.toString(testmarkq3));
            }else{
                Toast.makeText(getApplicationContext(), "no data available", Toast.LENGTH_LONG).show();

            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
ResultAnalysis.testmark[0]=testmarkq1;
    ResultAnalysis.testmark[1]=testmarkq2;
    ResultAnalysis.testmark[2]=testmarkq3;
progressDialog.dismiss();
return  testmark;
}


void addDataSet(PieChart pieChart ,float[] ydatamain){

    pieChart.setRotationEnabled(true);
    pieChart.setHoleRadius(50f);
    pieChart.setTransparentCircleAlpha(25);
    pieChart.setCenterText("Performance");
    pieChart.setCenterTextSize(20);
    pieChart.setDrawEntryLabels(true);
    pieChart.setEntryLabelTextSize(20);





    float []xdatanew=ydatamain;
ArrayList<PieEntry> yEntry=new ArrayList<>();
    ArrayList<String> xEntry=new ArrayList<>();
   // yEntry.add(Float.parseFloat(outoff));

    for(int i = 0; i<xdata.length; i++){
        xEntry.add(xdata[i]);
      yEntry.add(new PieEntry(xdatanew[i],i));

        
    }

    PieDataSet pieDataset=new PieDataSet(yEntry,"My Pie chart");

    pieDataset.setSliceSpace(2);
    pieDataset. setValueTextSize(12);




    ArrayList<Integer>colors=new ArrayList<>();
    colors.add(Color.GREEN);
    colors.add(Color.RED);
    colors.add(Color.CYAN);
    pieDataset.setColors(colors);


    Legend legend=pieChart.getLegend();
    legend.setForm(Legend.LegendForm.CIRCLE);
    legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

    PieData pieData =new  PieData(pieDataset);

    pieChart.setData(pieData);
    pieChart.invalidate();

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
