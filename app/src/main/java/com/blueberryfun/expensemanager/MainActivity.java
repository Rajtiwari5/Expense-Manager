package com.blueberryfun.expensemanager;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setElevation(0);
        setContentView(R.layout.activity_main);
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