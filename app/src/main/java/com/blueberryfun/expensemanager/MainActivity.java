package com.blueberryfun.expensemanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        mDisplayDate = (TextView) findViewById(R.id.inputDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo,
                        mDateSetListener, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month , int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
        Log.d(TAG, "onCreate: Started.");
        ListView mlistview = (ListView) findViewById(R.id.listView);
        //Create the Adapters objects
        Adapters food = new Adapters(R.drawable.circular_shape, "Food", "With friends", "12-02-2022","500");
        Adapters bill = new Adapters(R.drawable.circular_shape, "Bill", "Electricity bill", "16-03-2022","1000");
        Adapters shopping = new Adapters(R.drawable.circular_shape, "Shopping", "Dress and jeans", "17-03-2022","2500");
        Adapters grocery = new Adapters(R.drawable.circular_shape, "Grocery", "Breads,eggs", "18-03-2022","100");

        ArrayList<Adapters> typelist = new ArrayList<>();
        typelist.add(food);
        typelist.add(bill);
        typelist.add(shopping);
        typelist.add(grocery);

        AdaptersListAdapter adapter = new AdaptersListAdapter(this,R.layout.list_view_item, typelist);
        mlistview.setAdapter(adapter);


    }}


