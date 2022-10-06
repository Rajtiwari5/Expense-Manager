package com.blueberryfun.expensemanager;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CustomCursorAdapterClickListener{
    private static final String TAG = "MainActivity";

    DBHelper DB;
    ListView listView;
    Button listButton;
    FragmentManager fragmentManager = getSupportFragmentManager();

    private int year = getCurrentYear();
    public  int countYear = getCurrentYear();
    private int month = getCurrentMonth();
    public int countMonth = getCurrentMonth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);

        DB = new DBHelper(MainActivity.this);

        listButton = findViewById(R.id.listButton);
        Button overViewButton = findViewById(R.id.overviewButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentLayout, MyListFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name") // name can be null
                            .commit();

                    Button btn = (Button) findViewById(R.id.listButton);
                    btn.setBackground(getResources().getDrawable(R.drawable.round_button_white));
                    btn.setTextColor(Color.parseColor("#0099ff"));
                    btn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_format_list_bulleted_primary, 0,0,0);
                    Button btn1 = (Button) findViewById(R.id.overviewButton);
                    btn1.setBackground(getResources().getDrawable(R.drawable.round_button));
                    btn1.setTextColor(Color.parseColor("#ffffff"));
                    btn1.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_pie_chart_24, 0,0,0);
                }
                catch (Exception e){
                    Log.e(TAG, "Error"+e);
                }
            }
        });
        overViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentLayout, PieChartFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("name") // name can be null
                            .commit();

                    Button btn = (Button) findViewById(R.id.overviewButton);
                    btn.setBackground(getResources().getDrawable(R.drawable.round_button_white));
                    btn.setTextColor(Color.parseColor("#0099ff"));
                    btn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_piechart_primary, 0,0,0);
                    Button btn1 = (Button) findViewById(R.id.listButton);
                    btn1.setBackground(getResources().getDrawable(R.drawable.round_button));
                    btn1.setTextColor(Color.parseColor("#ffffff"));
                    btn1.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_format_list_bulleted_24, 0,0,0);
                }
                catch (Exception e){
                    Log.e(TAG, "Error"+e);
                }
            }
        });

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



        String[] monthName = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String  monthString = monthName[month];
        inputDate.setText(monthString + ", " + year);


        Cursor cursor = DB.dataBetweenRange(countMonth, countYear);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO ITEM EXISTS", Toast.LENGTH_SHORT).show();
//            listView.setAdapter(null);
        } else {
//            CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor);
//            customCursorAdapter.listener = this;
//            listView.setAdapter(customCursorAdapter);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentLayout, MyListFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // name can be null
                    .commit();

        }

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(countMonth == getCurrentMonth() && countYear == getCurrentYear()) {
                        Toast.makeText(MainActivity.this, "Cannot move forward", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        countMonth++;
                    if (countMonth <= 11) {
                        Log.e("Month-->", String.valueOf(countMonth));
                        Log.e("Month-->", String.valueOf(countYear));
                        inputDate.setText(monthName[countMonth] + ", " + countYear);

                        if (DB.dataBetweenRange(countMonth, countYear).getCount() == 0) {
                            Toast.makeText(MainActivity.this, "NO ITEM EXISTS", Toast.LENGTH_SHORT).show();
//                            listView.setAdapter(null);
                        } else {
//                            CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(MainActivity.this, DB.dataBetweenRange(countMonth, countYear));
//                            customCursorAdapter.listener = MainActivity.this;
//                            listView.setAdapter(customCursorAdapter);
                            if(listButton.getCurrentTextColor() == -16737793){
                                fragmentManager.beginTransaction()
                                        .replace(R.id.fragmentLayout, MyListFragment.class, null)
                                        .setReorderingAllowed(true)
                                        .addToBackStack("name") // name can be null
                                        .commit();
                            }else {
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .replace(R.id.fragmentLayout, PieChartFragment.class, null)
                                        .setReorderingAllowed(true)
                                        .addToBackStack("name") // name can be null
                                        .commit();
                            }

                        }

                    } else if (countMonth > 11) {
                        countMonth = 0;
                        countYear = countYear + 1;
                        Log.e("Month-->", String.valueOf(countMonth));
                        Log.e("Month-->", String.valueOf(countYear));
                        inputDate.setText(monthName[countMonth] + ", " + countYear);

                        if (DB.dataBetweenRange(countMonth, countYear).getCount() == 0) {
                            Toast.makeText(MainActivity.this, "NO ITEM EXISTS", Toast.LENGTH_SHORT).show();
//                            listView.setAdapter(null);
                        } else {
//                            CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(MainActivity.this, DB.dataBetweenRange(countMonth, countYear));
//                            customCursorAdapter.listener = MainActivity.this;
//                            listView.setAdapter(customCursorAdapter);
                            if(listButton.getCurrentTextColor() == -16737793){
                                fragmentManager.beginTransaction()
                                        .replace(R.id.fragmentLayout, MyListFragment.class, null)
                                        .setReorderingAllowed(true)
                                        .addToBackStack("name") // name can be null
                                        .commit();
                            }else {
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .replace(R.id.fragmentLayout, PieChartFragment.class, null)
                                        .setReorderingAllowed(true)
                                        .addToBackStack("name") // name can be null
                                        .commit();
                            }

                        }
                    }
                }
            }
            });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countMonth--;
                if(countMonth >= 0) {
                    Log.e("Month-->", String.valueOf(countMonth));
                    Log.e("Month-->", String.valueOf(countYear));
                    inputDate.setText(monthName[countMonth]  + ", " + countYear);

                    if (DB.dataBetweenRange(countMonth, countYear).getCount() == 0) {
                        Toast.makeText(MainActivity.this, "NO ITEM EXISTS", Toast.LENGTH_SHORT).show();
//                        listView.setAdapter(null);
                    } else {
//                        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(MainActivity.this, DB.dataBetweenRange(countMonth, countYear));
//                        customCursorAdapter.listener = MainActivity.this;
//                        listView.setAdapter(customCursorAdapter);
                        if(listButton.getCurrentTextColor() == -16737793){
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragmentLayout, MyListFragment.class, null)
                                    .setReorderingAllowed(true)
                                    .addToBackStack("name") // name can be null
                                    .commit();
                        }else {
                            Log.e(TAG, "color of text of list button: " + listButton.getCurrentTextColor() );
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragmentLayout, PieChartFragment.class, null)
                                    .setReorderingAllowed(true)
                                    .addToBackStack("name") // name can be null
                                    .commit();
                        }                    }


                } else if(countMonth < 0){
                    countMonth = 11;
                    countYear = countYear -1;
                    Log.e("Month-->", String.valueOf(countMonth));
                    Log.e("Month-->", String.valueOf(countYear));
                    inputDate.setText(monthName[countMonth]  +", "+countYear);

                    if (DB.dataBetweenRange(countMonth, countYear).getCount() == 0) {
                        Toast.makeText(MainActivity.this, "NO ITEM EXISTS", Toast.LENGTH_SHORT).show();
//                        listView.setAdapter(null);
                    } else {
//                        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(MainActivity.this, DB.dataBetweenRange(countMonth, countYear));
//                        customCursorAdapter.listener = MainActivity.this;
//                        listView.setAdapter(customCursorAdapter);
                        if(listButton.getSolidColor() == -16737793){
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragmentLayout, MyListFragment.class, null)
                                    .setReorderingAllowed(true)
                                    .addToBackStack("name") // name can be null
                                    .commit();
                        }else {


                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragmentLayout, PieChartFragment.class, null)
                                    .setReorderingAllowed(true)
                                    .addToBackStack("name") // name can be null
                                    .commit();
                        }

                    }
                }

            }
        });
    }
    public int getCurrentMonth(){
        Calendar today = Calendar.getInstance();
        int month = today.get(Calendar.MONTH);
        return month;
    }
    public  int getCurrentYear() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        return year;
    }

    @Override
    public void onResume() {
        super.onResume();

//        DB = new DBHelper(MainActivity.this);
//        Cursor cursor = DB.dataBetweenRange(countMonth, countYear);
//        listView = findViewById(R.id.listView);
//        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor);
//        customCursorAdapter.listener = this;
//        listView.setAdapter(customCursorAdapter);
//        customCursorAdapter.changeCursor(cursor);
        listButton = findViewById(R.id.listButton);

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentLayout, MyListFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // name can be null
                    .commit();


    }

    @Override
    public void didTapListViewAtIndex(int index) {
        DBHelper DB;
        DB = new DBHelper(this);
        Cursor cursor = DB.dataBetweenRange(countMonth, countYear);
        cursor.moveToPosition(index);

        Log.e(TAG, "didTapListViewAtIndex: ");

        String typeString = cursor.getString(1);
        String amountString = cursor.getString(2);
        String dateString = cursor.getString(3);
        String remarkString = cursor.getString(4);

        Intent intent = new Intent(MainActivity.this,ExpenseEntryDetails.class);
        intent.putExtra("KEY_TYPE",typeString);
        intent.putExtra("KEY_AMOUNT",amountString);
        intent.putExtra("KEY_DATE",dateString);
        intent.putExtra("KEY_REMARK",remarkString);
        intent.putExtra("KEY_INDEX", index);

        startActivity(intent);
    }
}
