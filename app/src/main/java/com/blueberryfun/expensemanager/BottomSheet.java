package com.blueberryfun.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BottomSheet extends BottomSheetDialogFragment implements BottomSheetClickedListener {

    public static ArrayList<ExpenseType> arrayList = new ArrayList<>();

    public static ArrayList<ExpenseType> myArrayList() {

        arrayList.add(new ExpenseType(R.drawable.ic_food,"Food/Drink",R.drawable.background_color_1));
        arrayList.add(new ExpenseType(R.drawable.ic_transport,"Transport",R.drawable.background_color_2));
        arrayList.add(new ExpenseType(R.drawable.ic_grocery,"Groceries",R.drawable.background_color_3));
        arrayList.add(new ExpenseType(R.drawable.ic_bill,"Bills",R.drawable.background_color_4));
        arrayList.add(new ExpenseType(R.drawable.ic_entertairment,"Entertainment",R.drawable.background_color_1));
        arrayList.add(new ExpenseType(R.drawable.ic_shopping,"Shopping",R.drawable.background_color_2));
        arrayList.add(new ExpenseType(R.drawable.ic_house,"House",R.drawable.background_color_3));
        arrayList.add(new ExpenseType(R.drawable.ic_travel,"Travel",R.drawable.background_color_4));
        arrayList.add(new ExpenseType(R.drawable.ic_healthcare,"Healthcare",R.drawable.background_color_1));
        arrayList.add(new ExpenseType(R.drawable.ic_education,"Education",R.drawable.background_color_2));
        arrayList.add(new ExpenseType(R.drawable.ic_pets,"Pets",R.drawable.background_color_3));
        arrayList.add(new ExpenseType(R.drawable.ic_gifts,"Gifts",R.drawable.background_color_4));

        return arrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,container, false);


        ImageView backButton = v.findViewById(R.id.sheetBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        RecyclerView myRecyclerView = v.findViewById(R.id.myRecyclerView);
        myRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        AdapterForSheet adapterForSheet = new AdapterForSheet(getActivity(),myArrayList());
        adapterForSheet.itemListener = this;
        myRecyclerView.setAdapter(adapterForSheet);

        return v;
    }


    @Override
    public void bottomSheetItemClicked(View v, int position) {
        ExpenseType element  = myArrayList().get(position);
        dismiss();

        if (getActivity() instanceof ExpenseEntryDetails){
            ExpenseEntryDetails parent = (ExpenseEntryDetails) getActivity();
            parent.onExpenseTypeSelected(element);
        }
        getActivity().getFragmentManager().popBackStack();
    }
}
