package com.blueberryfun.expensemanager;

public class Adapters {
    private int image;
    private String type;
    private String remark;
    private String date;
    private String amount;

    public Adapters(Integer image, String type, String remark, String date, String  amount) {
        this.image = image;
        this.type = type;
        this.remark = remark;
        this.date = date;
        this.amount = amount;
    }

    public Integer getImage(){
        return image;
    }
    public void setImage(Integer image){
        this.image = image;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String  getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
