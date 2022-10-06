package com.blueberryfun.expensemanager;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PieChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PieChartFragment extends Fragment {

    AnyChartView anyChartView;
    DBHelper db;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PieChartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        DBHelper dbHelper = new DBHelper(getActivity());
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PieChartFragment newInstance(String param1, String param2) {
        PieChartFragment fragment = new PieChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);
        anyChartView = view.findViewById(R.id.anyChartView);
        setUpPieChart();
        return view;
    }
    public void setUpPieChart(){
        MainActivity parent = (MainActivity) getActivity();
        int year = parent.countYear;
        int month = parent.countMonth;

        db = new DBHelper(getActivity());

        int expenseArray[] = new int[100];
        String typeArray[] = new String[100];

        Cursor cursor = db.dataBetweenRange(month, year);
        cursor.moveToFirst();
//        Log.e(TAG, "fromCursor: " + cursor.getString(1) );
//        Log.e(TAG, "fromCursor: " + cursor.getString(2) );
//        while(!cursor.moveToLast()){
//            Log.e(TAG, "fromCursor: " + cursor.getString(2) );
//            typeArray[i] = cursor.getString(1);
//            expenseArray[i] = cursor.getInt(2);
//            i++;
//            cursor.moveToNext();
//        }
        Log.e(TAG, "typeArray: " + typeArray[0] );


        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for(int i = 0; i < cursor.getCount(); i++){
            dataEntries.add(new ValueDataEntry(cursor.getString(1), cursor.getInt(2)));
            Log.e(TAG, "fromCursor: " + cursor.getString(2) );
            cursor.moveToNext();
        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }
}