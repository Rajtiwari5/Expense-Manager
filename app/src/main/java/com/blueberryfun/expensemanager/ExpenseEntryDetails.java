package com.blueberryfun.expensemanager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExpenseEntryDetails extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    TextView type, transactionDate;
    EditText amount, remark;
    Button addButton, updateButton;
    ImageView deleteButton;
    DBHelper DB;
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

                if(typeText.length() == 0 || inputAmount.length() == 0 || inputDate.length() == 0 || inputRemark.length() == 0){
                    Toast.makeText(ExpenseEntryDetails.this, "Fields Required!", Toast.LENGTH_SHORT).show();
                } else{
                    Boolean checkInsertData = DB.insertUserData(typeText, inputAmount, inputDate, inputRemark);
                    if(checkInsertData) {
                        Toast.makeText(ExpenseEntryDetails.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ExpenseEntryDetails.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(ExpenseEntryDetails.this,MainActivity.class));
                }
                break;
            case R.id.updateButton:
                type = findViewById(R.id.inputType);
                transactionDate = findViewById(R.id.transactionDate);
                amount = findViewById(R.id.inputAmount);
                remark = findViewById(R.id.inputRemark);

                String typeText1 = type.getText().toString();
                String inputAmount1 = amount.getText().toString();
                String inputDate1 = transactionDate.getText().toString();
                String inputRemark1 = remark.getText().toString();

                if(typeText1.length() == 0 || inputAmount1.length() == 0 || inputDate1.length() == 0 || inputRemark1.length() == 0){
                    Toast.makeText(ExpenseEntryDetails.this, "Fields Required!", Toast.LENGTH_SHORT).show();
                } else{
                    Boolean checkUpdateData = DB.updateUserData(typeText1, inputAmount1, inputDate1, inputRemark1);
                    if(checkUpdateData) {
                        Toast.makeText(ExpenseEntryDetails.this, "Update", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ExpenseEntryDetails.this, "Not Update", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(ExpenseEntryDetails.this,MainActivity.class));
                }
                break;
            case R.id.deleteButton:
                DBHelper DB;
                DB = new DBHelper(this);
                type = findViewById(R.id.inputType);
                typeText = type.getText().toString();
                DB.deleteData(typeText);
                startActivity(new Intent(ExpenseEntryDetails.this,MainActivity.class));

        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString  = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        TextView textView = findViewById(R.id.transactionDate);
        datePicker.setMaxDate(c.getTimeInMillis());
        textView.setText(currentDateString);
    }

    public void onExpenseTypeSelected(ExpenseType expenseType){
        String myText = expenseType.getNameForType();
        BottomSheet bottomSheet = new BottomSheet();

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