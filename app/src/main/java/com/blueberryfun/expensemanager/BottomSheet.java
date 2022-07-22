package com.blueberryfun.expensemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheet extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,container, false);

        ArrayList<ExpenseType> arrayList = new ArrayList<>();
        arrayList.add(new ExpenseType(R.drawable.ic_food,"Food/Drink"));
        arrayList.add(new ExpenseType(R.drawable.ic_transport,"Transport"));
        arrayList.add(new ExpenseType(R.drawable.ic_grocery,"Groceries"));
        arrayList.add(new ExpenseType(R.drawable.ic_bill,"Bills"));
        arrayList.add(new ExpenseType(R.drawable.ic_entertairment,"Entertainment"));
        arrayList.add(new ExpenseType(R.drawable.ic_shopping,"Shopping"));
        arrayList.add(new ExpenseType(R.drawable.ic_house,"House"));
        arrayList.add(new ExpenseType(R.drawable.ic_travel,"Travel"));
        arrayList.add(new ExpenseType(R.drawable.ic_healthcare,"Healthcare"));
        arrayList.add(new ExpenseType(R.drawable.ic_education,"Education"));
        arrayList.add(new ExpenseType(R.drawable.ic_pets,"Pets"));
        arrayList.add(new ExpenseType(R.drawable.ic_gifts,"Gifts"));


        RecyclerView myRecyclerView = v.findViewById(R.id.myRecyclerView);
        myRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        AdapterForSheet adapterForSheet = new AdapterForSheet(getActivity(),arrayList);
        myRecyclerView.setAdapter(adapterForSheet);

        return v;
    }
}
