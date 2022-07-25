package com.blueberryfun.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        String myText = element.getNameForType();
        if (getActivity() instanceof ExpenseEntryDetails){
            ExpenseEntryDetails parent = (ExpenseEntryDetails) getActivity();
            parent.onExpenseTypeSelected(element);
        }
        getActivity().getFragmentManager().popBackStack();
    }
}
