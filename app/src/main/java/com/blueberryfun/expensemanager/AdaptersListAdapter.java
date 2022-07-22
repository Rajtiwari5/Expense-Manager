package com.blueberryfun.expensemanager;

import android.content.Context;
import android.provider.ContactsContract;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptersListAdapter extends ArrayAdapter<Adapters> {
    private static final String TAG = "AdaptersListAdapter";

    private Context mcontext;
    int mResource;

    public AdaptersListAdapter(Context context, int resource, ArrayList<Adapters> objects){
        super(context, resource, objects);
        mcontext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Integer image = getItem(position).getImage();
        String type = getItem(position).getType();
        String remark = getItem(position).getRemark();
        String date = getItem(position).getDate();
        String amount = getItem(position).getAmount();

        Adapters adapters = new Adapters(image, type, remark, date, amount);
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mResource, parent,false);
        ImageView tvimage = (ImageView) convertView.findViewById(R.id.imageType);
        TextView tvtype = (TextView) convertView.findViewById(R.id.typeText);
        TextView tvremark = (TextView) convertView.findViewById(R.id.remark);
        TextView tvdate = (TextView) convertView.findViewById(R.id.dateText);
        TextView tvamount = (TextView) convertView.findViewById(R.id.amountText);

        tvimage.setImageResource(image);
        tvtype.setText(type);
        tvremark.setText(remark);
        tvdate.setText(date);
        tvamount.setText(amount);

        return convertView;

    }


}
