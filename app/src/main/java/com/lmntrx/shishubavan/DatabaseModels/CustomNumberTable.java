package com.lmntrx.shishubavan.DatabaseModels;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/***
 * Created by livin on 11/5/16.
 */
@Table(name = "CustomNumbers")
public class CustomNumberTable {

    @Column(name = "ContactName")
    public String contactName;



    @Column(name = "Number")
    public String number;

    @Column(name = "Enabled")
    public Boolean enabled;


    public CustomNumberTable(){super();}

    public CustomNumberTable(String contactName,String number,Boolean enabled){
        this.contactName = contactName;
        this.number = number;
        this.enabled = enabled;
    }

}
