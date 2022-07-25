package com.blueberryfun.expensemanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.util.Calendar;

public class ExpenseEntryDetails extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    TextView type, transactionDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_entry_details);

        type = findViewById(R.id.inputType);
        transactionDate = findViewById(R.id.transactionDate);
        type.setOnClickListener(this);
        transactionDate.setOnClickListener(this);

    }
    public void getDataFromFragment(String myText) {
        type = findViewById(R.id.inputType);
        type.setText(myText);
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