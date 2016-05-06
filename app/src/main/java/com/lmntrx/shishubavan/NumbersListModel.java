package com.lmntrx.shishubavan;

/***
 * Created by livin on 4/5/16.
 */
public class NumbersListModel {

    String placeName;
    Boolean isChosen;

    public NumbersListModel(String placeName, Boolean isChosen){
        this.placeName = placeName;
        this.isChosen = isChosen;
    }

    public String getPlace(){
        return placeName;
    }

    public Boolean isEnabled(){
        return isChosen;
    }




}
