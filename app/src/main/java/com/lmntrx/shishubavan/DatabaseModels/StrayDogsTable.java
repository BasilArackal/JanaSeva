package com.lmntrx.shishubavan.DatabaseModels;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/***
 * Created by livin on 5/5/16.
 */

@Table(name = "StrayDogs")
public class StrayDogsTable extends Model {

    @Column(name = "PlaceName")
    public String placeName;



    @Column(name = "Number")
    public String number;

    @Column(name = "Enabled")
    public Boolean enabled;

    public StrayDogsTable(){
        super();
    }

    public StrayDogsTable(String placeName,String number,Boolean enabled){
        this.placeName = placeName;
        this.number = number;
        this.enabled = enabled;
    }

}
