package com.blueberryfun.expensemanager;

public class ExpenseType {
    Integer imageForType;
    String nameForType;
    Integer backgroundColorForType;

    ExpenseType(Integer imageForType, String nameForType, Integer backgroundColorForType){
        this.imageForType = imageForType;
        this.nameForType = nameForType;
        this.backgroundColorForType = backgroundColorForType;
    }

    public Integer getBackgroundColorForType() {
        return backgroundColorForType;
    }
    public Integer getImageForType() {
        return imageForType;
    }
    public String getNameForType(){
        return nameForType;
    }
}
