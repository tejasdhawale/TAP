package com.tap.tap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tejas on 19-03-2017.
 */

public class CustomAdapterone extends BaseAdapter {
    Context context;
    String[] MainName, SubData;

    LayoutInflater inflter;
    public static ArrayList<String> selectedAnswers;

    public CustomAdapterone(Context applicationContext, String[] MainNme, String[] subData) {
        this.context = context;
        this.MainName = MainNme;
        this.SubData = subData;
// initialize arraylist and add static string for all the questions
        /** selectedAnswers = new ArrayList<>();
         for (int i = 0; i < questionsList.length; i++) {
         selectedAnswers.add("Not Attempted");
         }**/
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return MainName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_adapter1, null);
// get the reference of TextView and Button's
        TextView maintxt = (TextView) view.findViewById(R.id.textView8);
        TextView subdata = (TextView) view.findViewById(R.id.textView9);

        maintxt.setText(MainName[i]);
        subdata.setText(SubData[i]);
        return view;
    }}

