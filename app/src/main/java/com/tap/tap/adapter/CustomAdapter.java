package com.tap.tap;

/**
 * Created by Tejas on 19-03-2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {
    Context context;
    String[] questionsList, op1, op2, op3, op4, result;
    LayoutInflater inflter;
    public static ArrayList<String> selectedAnswers;

    public CustomAdapter(Context applicationContext, String[] questionsList, String[] op1, String[] op2, String[] op3, String[] op4, String[] result) {
        this.context = context;
        this.questionsList = questionsList;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.result = result;
// initialize arraylist and add static string for all the questions
        selectedAnswers = new ArrayList<>();
        for (int i = 0; i < questionsList.length; i++) {
            selectedAnswers.add("Not Attempted");
        }
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return questionsList.length;
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
        view = inflter.inflate(R.layout.list_items, null);
// get the reference of TextView and Button's
        TextView question = (TextView) view.findViewById(R.id.question);
        RadioButton one = (RadioButton) view.findViewById(R.id.r1);
        RadioButton two = (RadioButton) view.findViewById(R.id.r2);
        RadioButton three = (RadioButton) view.findViewById(R.id.r3);
        RadioButton four = (RadioButton) view.findViewById(R.id.r4);
// perform setOnCheckedChangeListener event on yes button
        one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// set Yes values in ArrayList if RadioButton is checked
                if (isChecked)
                    selectedAnswers.set(i, "Yes");
                selectedAnswers.set(i, op1[i]);
            }
        });
// perform setOnCheckedChangeListener event on no button
        two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// set No values in ArrayList if RadioButton is checked
                if (isChecked)
                    selectedAnswers.set(i, op2[i]);

            }
        });

        three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// set No values in ArrayList if RadioButton is checked
                if (isChecked)
                    selectedAnswers.set(i, op3[i]);

            }
        });
        four.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// set No values in ArrayList if RadioButton is checked
                if (isChecked)
                    selectedAnswers.set(i, op4[i]);

            }
        });
// set the value in TextView
        question.setText(i + 1 + ": " + questionsList[i]);
        one.setText("A: " + op1[i]);
        two.setText("B: " + op2[i]);
        three.setText("C: " + op3[i]);
        four.setText("D: " + op4[i]);
        return view;
    }
}