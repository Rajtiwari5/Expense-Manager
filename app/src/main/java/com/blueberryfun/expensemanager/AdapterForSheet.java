package com.blueberryfun.expensemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterForSheet extends RecyclerView.Adapter<AdapterForSheet.ViewHolder> {

    private Context context;
    private List<ExpenseType> expenseTypeList;

    public AdapterForSheet(Context context, List<ExpenseType> expenseTypeList) {
        this.context = context;
        this.expenseTypeList = expenseTypeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExpenseType expenseType = expenseTypeList.get(position);
        holder.nameTypeSheet.setText(expenseType.getNameForType());
        holder.imageTypeSheet.setImageResource(expenseType.getImageForType());
    }

    @Override
    public int getItemCount() {
        return expenseTypeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTypeSheet;
        public ImageView imageTypeSheet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTypeSheet = itemView.findViewById(R.id.nameItemSheet);
            imageTypeSheet = itemView.findViewById(R.id.imageItemSheet);
        }

    }
}