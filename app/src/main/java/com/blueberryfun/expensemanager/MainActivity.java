package com.blueberryfun.expensemanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private int count;
    private int countYear;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        Button listButton = findViewById(R.id.listButton);
        Button overviewbtn = findViewById(R.id.overviewButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Button btn = (Button) findViewById(R.id.listButton);
                    Button btn2 = (Button) findViewById(R.id.overviewButton);
                    btn.setBackground(getResources().getDrawable(R.drawable.round_button_white));
                    btn.setTextColor(Color.parseColor("#0099ff"));
                    btn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_format_list_bulleted_primary, 0, 0, 0);

                    btn2.setBackground(getResources().getDrawable(R.drawable.round_button));
                    btn2.setTextColor(Color.parseColor("#FFFFFF"));
                    btn2.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_pie_chart_24, 0, 0, 0);

                } catch (Exception e) {
                    Log.e(TAG, "Error" + e);
                }
            }
        });
        overviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Button btn = (Button) findViewById(R.id.overviewButton);
                    Button btn2 = (Button) findViewById(R.id.listButton);

                    btn.setBackground(getResources().getDrawable(R.drawable.round_button_white));
                    btn.setTextColor(Color.parseColor("#0099ff"));
                    btn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_piechart_primary, 0, 0, 0);

                    btn2.setBackground(getResources().getDrawable(R.drawable.round_button));
                    btn2.setTextColor(Color.parseColor("#FFFFFF"));
                    btn2.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_format_list_bulleted_24, 0, 0, 0);
                } catch (Exception e) {
                    Log.e(TAG, "Error" + e);
                }
            }
        });

        Log.d(TAG, "onCreate: Started.");

        ListView mlistview = (ListView) findViewById(R.id.listView);
        Adapters food = new Adapters(R.drawable.ic_food, "Food", "Eggs and bread", "24/02/2022", "20000");
        Adapters bills = new Adapters(R.drawable.ic_bill, "Bills", "Electricity bill", "24/02/2022", "200");
        Adapters grocery = new Adapters(R.drawable.ic_grocery, "Grocery", "Milk", "24/02/2022", "200");
        Adapters medicine = new Adapters(R.drawable.ic_healthcare, "Medicine", "Sumo,paracetamol", "24/02/2022", "200");

        ArrayList<Adapters> adapterlist = new ArrayList<>();
        adapterlist.add(food);
        adapterlist.add(bills);
        adapterlist.add(grocery);
        adapterlist.add(medicine);
        AdaptersListAdapter adapter = new AdaptersListAdapter(this, R.layout.list_view_item, adapterlist);
        mlistview.setAdapter(adapter);

        FloatingActionButton btn = findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ExpenseEntryDetails.class));
            }
        });


        TextView inputDate = findViewById(R.id.inputDate);
        ImageButton previous, next;

        previous = findViewById(R.id.previousButton);
        next = findViewById(R.id.nextButton);

        Calendar today = Calendar.getInstance();
        int year;
        year = today.get(Calendar.YEAR);
        countYear = today.get(Calendar.YEAR);

        String[] monthName = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String month = monthName[today.get(Calendar.MONTH)];
        inputDate.setText(month + ", " + year);
        count = today.get(Calendar.MONTH);

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                    if(count<=11){
                    Log.e("count:-->",""+count);
                    String month = monthName[count];
                    inputDate.setText(month + ", " + year);
                }
        else if(count>11){
                    //Log.e("count:-->",""+count);
                    count = 0;
                    countYear = countYear + 1;
                    inputDate.setText(month + ", " + countYear);
                }
            }
            });



            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count--;
                    if (count >= 0) {
                        Log.e("count:-->", "" + count);

                        String month = monthName[count];
                        inputDate.setText(month + ", " + year);
                    } else if (count < 0) {
//                        Log.e("count:-->", "" + count);
                        count = 11;
                        countYear = countYear - 1;
                        inputDate.setText(month + ", " + countYear);
                    }
                }
            });

}}
