package com.lmntrx.shishubavan;

/***
 * Created by livin on 8/7/16.
 */

public class CustomSMSNumber {

    String name,number;

    public CustomSMSNumber(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
