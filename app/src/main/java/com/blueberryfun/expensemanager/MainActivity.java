package com.blueberryfun.expensemanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

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
                try{
                    Button btn = (Button) findViewById(R.id.listButton);
                    btn.setBackground(getResources().getDrawable(R.drawable.round_button_white));
                    ((Button)view).setTextColor(Color.parseColor("#0099ff"));
                    btn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_format_list_bulleted_primary, 0,0,0);
                }
                catch (Exception e){
                    Log.e(TAG, "Error"+e);
                }
            }
        });
        overviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Button btn = (Button) findViewById(R.id.overviewButton);
                    btn.setBackground(getResources().getDrawable(R.drawable.round_button_white));
                    ((Button) view).setTextColor(Color.parseColor("#0099ff"));
                    btn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_piechart_primary, 0,0,0);
                }
                catch (Exception e){
                    Log.e(TAG, "Error"+e);
                }
            }
        });
        Log.d(TAG,"onCreate: Started.");
        ListView mlistview = (ListView) findViewById(R.id.listView);
        Adapters food = new Adapters(R.drawable.ic_food, "Food","Eggs and bread","24/02/2022","200");
        Adapters bills = new Adapters(R.drawable.ic_bill, "Bills","Electricity bill","24/02/2022","200");
        Adapters grocery = new Adapters(R.drawable.ic_grocery, "Grocery","Milk","24/02/2022","200");
        Adapters medicine = new Adapters(R.drawable.ic_healthcare, "Medicine","Sumo,paracetamol","24/02/2022","200");

        ArrayList<Adapters> adapterlist = new ArrayList<>();
        adapterlist.add(food);
        adapterlist.add(bills);
        adapterlist.add(grocery);
        adapterlist.add(medicine);
        AdaptersListAdapter adapter = new AdaptersListAdapter(this, R.layout.list_view_item, adapterlist);
        mlistview.setAdapter(adapter);

        FloatingActionButton btn =findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ExpenseEntryDetails.class));
            }
        });


    }
}