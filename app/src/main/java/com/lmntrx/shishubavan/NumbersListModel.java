package com.lmntrx.shishubavan;

/***
 * Created by livin on 4/5/16.
 */
public class NumbersListModel {

    String placeName, number;
    Boolean isChosen;

    public NumbersListModel(String placeName, String number, Boolean isChosen){
        this.placeName = placeName;
        this.isChosen = isChosen;
        this.number = number;
    }

    public String getPlace(){
        return placeName;
    }

    public String getNumber(){
        return number;
    }

    public Boolean isEnabled(){
        return isChosen;
    }




}
