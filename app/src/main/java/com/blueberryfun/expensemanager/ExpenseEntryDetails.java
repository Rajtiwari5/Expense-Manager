package com.blueberryfun.expensemanager;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blueberryfun.expensemanager.databinding.ActivityExpenseEntryDetailsBinding;

public class ExpenseEntryDetails extends AppCompatActivity {


    ActivityExpenseEntryDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_expense_entry_details);

    }
}