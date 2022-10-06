package com.blueberryfun.expensemanager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExpenseEntryDetails extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    TextView type, transactionDate;
    EditText amount, remark;
    Button addButton, updateButton;
    ImageView deleteButton;
    DBHelper DB;

    MainActivity mainActivity = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_entry_details);

        DB = new DBHelper(ExpenseEntryDetails.this);
        Cursor cursor = DB.getData();

        amount = findViewById(R.id.inputAmount);
        remark = findViewById(R.id.inputRemark);

        type = findViewById(R.id.inputType);
        type.setOnClickListener(this);

        transactionDate = findViewById(R.id.transactionDate);
        transactionDate.setOnClickListener(this);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);

        updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(this);

        addButton.setVisibility(View.INVISIBLE);
        updateButton.setVisibility(View.INVISIBLE);
        deleteButton.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        String typeString = intent.getStringExtra("KEY_TYPE");
        String amountString = intent.getStringExtra("KEY_AMOUNT");
        String dateString = intent.getStringExtra("KEY_DATE");
        String remarkString = intent.getStringExtra("KEY_REMARK");

        type.setText(typeString);
        amount.setText(amountString);
        transactionDate.setText(dateString);
        remark.setText(remarkString);


        if(type.getText().toString().equals("")){
            addButton.setVisibility(View.VISIBLE);

        } else{
            updateButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.inputType:
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "BottomSheet");
                break;
            case R.id.transactionDate:
                DialogFragment datePicker  = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "datePicker");
                break;
            case R.id.addButton:
                    type = findViewById(R.id.inputType);
                    transactionDate = findViewById(R.id.transactionDate);
                    amount = findViewById(R.id.inputAmount);
                    remark = findViewById(R.id.inputRemark);

                    String typeText = type.getText().toString();
                    String inputAmount = amount.getText().toString();
                    String inputDate = transactionDate.getText().toString();
                    String inputRemark = remark.getText().toString();

                //                        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
//                        Date d1 = format1.parse(inputDate);
//                        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
//                        String d2 = format2.format(d1);


                if (typeText.length() == 0 || inputAmount.length() == 0 || inputDate.length() == 0 || inputRemark.length() == 0) {
                    Toast.makeText(ExpenseEntryDetails.this, "Fields Required!", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkInsertData = DB.insertUserData(typeText, inputAmount, inputDate, inputRemark);
                    if (checkInsertData) {
                        Toast.makeText(ExpenseEntryDetails.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ExpenseEntryDetails.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(ExpenseEntryDetails.this, MainActivity.class));
                }

                break;
            case R.id.updateButton:
                Log.e("Update Button","clicked");
                type = findViewById(R.id.inputType);
                transactionDate = findViewById(R.id.transactionDate);
                amount = findViewById(R.id.inputAmount);
                remark = findViewById(R.id.inputRemark);
                Intent intent = getIntent();
                Integer index = intent.getIntExtra("KEY_INDEX", 0);

                int year = mainActivity.countYear;
                int month = mainActivity.countMonth;

                Cursor cursor = DB.dataBetweenRange(month, year);
                cursor.moveToPosition(index);
                Integer id = cursor.getInt(0);

                String typeText1 = type.getText().toString();
                String inputAmount1 = amount.getText().toString();
                String inputDate1 = transactionDate.getText().toString();
                String inputRemark1 = remark.getText().toString();
                Log.e("Entry Updated","In Process before try");
                Log.e("Entry Updated","In Process");
//                    SimpleDateFormat format3 = new SimpleDateFormat("dd/MM/yyyy");
//                    Date d3 = format3.parse(inputDate1);
//                    SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd");
//                    String d4 = format4.format(d3);
//                    Log.e("Entry Updated","In Process");

                if (typeText1.length() == 0 || inputAmount1.length() == 0 || inputDate1.length() == 0 || inputRemark1.length() == 0) {
                    Toast.makeText(ExpenseEntryDetails.this, "Fields Required!", Toast.LENGTH_SHORT).show();
                    Log.e("checking Fields","after if");
                } else {
                    Boolean checkUpdateData = DB.updateUserData(id, typeText1, inputAmount1, inputDate1, inputRemark1);
                    if (checkUpdateData) {
                        Log.e("Entry Updated","Successfully in"+month+" and"+year);
                        Toast.makeText(ExpenseEntryDetails.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ExpenseEntryDetails.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(ExpenseEntryDetails.this, MainActivity.class));
                }
                break;
            case R.id.deleteButton:
                DBHelper DB;
                DB = new DBHelper(this);
                Intent intent1 = getIntent();
                Integer index1 = intent1.getIntExtra("KEY_INDEX", 0);


                int year1 = mainActivity.countYear;
                int month1 = mainActivity.countMonth;
                Cursor cursor1 = DB.dataBetweenRange(month1, year1);
                cursor1.moveToPosition(index1);

                Integer id1 = cursor1.getInt(0);
                DB.deleteData(id1);
                startActivity(new Intent(ExpenseEntryDetails.this,MainActivity.class));

        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString  = (String) android.text.format.DateFormat.format("yyyy-MM-dd", c.getTime());
        TextView textView = findViewById(R.id.transactionDate);
        datePicker.setMaxDate(c.getTimeInMillis());
        textView.setText(currentDateString);
    }

    public void onExpenseTypeSelected(ExpenseType expenseType){
        String myText = expenseType.getNameForType();
        type.setText(myText);
    }

//    private void showDialog() {
//        final Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(R.layout.bottom_sheet_layout);
//        dialog.setContentView(R.layout.bottom_sheet_layout);
//
//        dialog.show();
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog.getWindow().setGravity(Gravity.BOTTOM);
//    }
}