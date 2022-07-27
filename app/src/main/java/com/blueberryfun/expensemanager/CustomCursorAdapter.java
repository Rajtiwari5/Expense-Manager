package com.blueberryfun.expensemanager;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

interface CustomCursorAdapterClickListener{
    void didTapListViewAtIndex(int index);
}


public class CustomCursorAdapter extends CursorAdapter implements View.OnClickListener {
    CustomCursorAdapterClickListener listener;
    public CustomCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_view_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        view.setOnClickListener(this);
        view.setTag(cursor.getPosition());

        TextView item_type = view.findViewById(R.id.typeText);
        TextView item_amount = view.findViewById(R.id.amountText);
        TextView item_date = view.findViewById(R.id.dateText);
        TextView item_remark = view.findViewById(R.id.remark);
        ImageView item_image = view.findViewById(R.id.imageType);


        String type = cursor.getString(1);
        String amount = cursor.getString(2);
        String date = cursor.getString(3);
        String remark = cursor.getString(4);

        item_type.setText(type);
        item_amount.setText(String.valueOf(amount));
        item_date.setText(String.valueOf(date));
        item_remark.setText(remark);
        switch (type){
            case "Food/Drink":
                item_image.setImageResource(R.drawable.ic_food);
                break;
            case "Transport":
                item_image.setImageResource(R.drawable.ic_transport);
                break;
            case "Groceries":
                item_image.setImageResource(R.drawable.ic_grocery);
                break;
            case "Bills":
                item_image.setImageResource(R.drawable.ic_bill);
                break;
            case "Entertainment":
                item_image.setImageResource(R.drawable.ic_entertairment);
                break;
            case "Shopping":
                item_image.setImageResource(R.drawable.ic_shopping);
                break;
            case "House":
                item_image.setImageResource(R.drawable.ic_house);
                break;
            case "Travel":
                item_image.setImageResource(R.drawable.ic_travel);
                break;
            case "Healthcare":
                item_image.setImageResource(R.drawable.ic_healthcare);
                break;
            case "Education":
                item_image.setImageResource(R.drawable.ic_education);
                break;
            case "Pets":
                item_image.setImageResource(R.drawable.ic_pets);
                break;
            case "Gifts":
                item_image.setImageResource(R.drawable.ic_gifts);
                break;

        }
    }


    @Override
    public void onClick(View view) {
        listener.didTapListViewAtIndex((int) view.getTag());
    }
}