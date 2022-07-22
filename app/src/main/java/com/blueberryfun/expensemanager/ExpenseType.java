package com.blueberryfun.expensemanager;

public class ExpenseType {
    Integer imageForType;
    String nameForType;

    ExpenseType(Integer imageForType, String nameForType){
        this.imageForType = imageForType;
        this.nameForType = nameForType;
    }
    public Integer getImageForType() {
        return imageForType;
    }
    public String getNameForType(){
        return nameForType;
    }
}
