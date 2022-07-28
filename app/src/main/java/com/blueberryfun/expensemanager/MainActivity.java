package com.blueberryfun.expensemanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CustomCursorAdapterClickListener{
    private static final String TAG = "MainActivity";

    DBHelper DB;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
//        Button listButton = findViewById(R.id.listButton);
//        Button overviewbtn = findViewById(R.id.overviewButton);
//        listButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try{
//                    Button btn = (Button) findViewById(R.id.listButton);
//                    btn.setBackground(getResources().getDrawable(R.drawable.round_button_white));
//                    ((Button)view).setTextColor(Color.parseColor("#0099ff"));
//                    btn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_format_list_bulleted_primary, 0,0,0);
//                }
//                catch (Exception e){
//                    Log.e(TAG, "Error"+e);
//                }
//            }
//        });
//        overviewbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    Button btn = (Button) findViewById(R.id.overviewButton);
//                    btn.setBackground(getResources().getDrawable(R.drawable.round_button_white));
//                    ((Button) view).setTextColor(Color.parseColor("#0099ff"));
//                    btn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_piechart_primary, 0,0,0);
//                }
//                catch (Exception e){
//                    Log.e(TAG, "Error"+e);
//                }
//            }
//        });
//        Log.d(TAG,"onCreate: Started.");
//        ListView mlistview = (ListView) findViewById(R.id.listView);
//        Adapters food = new Adapters(R.drawable.ic_food, "Food","Eggs and bread","24/02/2022","200");
//        Adapters bills = new Adapters(R.drawable.ic_bill, "Bills","Electricity bill","24/02/2022","200");
//        Adapters grocery = new Adapters(R.drawable.ic_grocery, "Grocery","Milk","24/02/2022","200");
//        Adapters medicine = new Adapters(R.drawable.ic_healthcare, "Medicine","Sumo,paracetamol","24/02/2022","200");
//
//        ArrayList<Adapters> adapterlist = new ArrayList<>();
//        adapterlist.add(food);
//        adapterlist.add(bills);
//        adapterlist.add(grocery);
//        adapterlist.add(medicine);
//        AdaptersListAdapter adapter = new AdaptersListAdapter(this, R.layout.list_view_item, adapterlist);
//        mlistview.setAdapter(adapter);
        DB = new DBHelper(MainActivity.this);
        Cursor cursor = DB.getData();

        listView = findViewById(R.id.listView);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO ITEM EXISTS", Toast.LENGTH_SHORT).show();
        } else {
            CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor);
            customCursorAdapter.listener = this;
            listView.setAdapter(customCursorAdapter);
        }

        FloatingActionButton btn = findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ExpenseEntryDetails.class));
            }
        });
//        TextView inputDate = findViewById(R.id.inputDate);
//        ImageButton previous, next;
//
//        previous = findViewById(R.id.previousButton);
//        next = findViewById(R.id.nextButton);
//
//        Calendar today = Calendar.getInstance();
//        int year;
//        year = today.get(Calendar.YEAR);
//
//        String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
//        String  month = monthName[today.get(Calendar.MONTH)];
//        inputDate.setText(month + " " + year);
//
//            next.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String month = monthName[today.get(Calendar.MONTH) + 1];
//                    inputDate.setText(month + " " + year);
//                }
//            });
//            previous.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String month = monthName[today.get(Calendar.MONTH) - 1];
//                    inputDate.setText(month + " " + year);
//                }
//            });
    }
    @Override
    public void onResume() {
        super.onResume();

        DB = new DBHelper(MainActivity.this);
        Cursor cursor = DB.getData();
        listView = findViewById(R.id.listView);
        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor);
        customCursorAdapter.listener = this;
        listView.setAdapter(customCursorAdapter);
        customCursorAdapter.changeCursor(cursor);
    }

    @Override
    public void didTapListViewAtIndex(int index) {
        DBHelper DB;
        DB = new DBHelper(this);
        Cursor cursor = DB.getData();
        cursor.moveToPosition(index);

        Integer id = cursor.getInt(0);
        String typeString = cursor.getString(1);
        String amountString = cursor.getString(2);
        String dateString = cursor.getString(3);
        String remarkString = cursor.getString(4);

        Intent intent = new Intent(MainActivity.this,ExpenseEntryDetails.class);
        //intent.putStringArrayListExtra("KEY_STRINGS", strings);
        intent.putExtra("KEY_TYPE",typeString);
        intent.putExtra("KEY_AMOUNT",amountString);
        intent.putExtra("KEY_DATE",dateString);
        intent.putExtra("KEY_REMARK",remarkString);
        intent.putExtra("KEY_INDEX", index);

        startActivity(intent);
    }
}
