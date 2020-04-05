package edu.scranton.lunchilicious;

import java.text.DecimalFormat;

public class MenuItem {
    int mID;
    String mType;
    String mName;
    String mDescription;
    double mUnitPrice;
    String price;
    DecimalFormat df = new DecimalFormat("#.##");
    public MenuItem(int id, String type, String name, String description, double unitPrice) {
        mID = id;
        mType = type;
        mName = name;
        mDescription = description;
        mUnitPrice = unitPrice;
    }
    public int getId(){
        return mID;
    }
    public String getFoodType(){
        return mType;
    }
    public String getFoodName(){
        return mName;
    }

    public String getDescription(){
    return mDescription;
}
    public String getPrice(){
        price= df.format(mUnitPrice);
    return price;
    }
}
