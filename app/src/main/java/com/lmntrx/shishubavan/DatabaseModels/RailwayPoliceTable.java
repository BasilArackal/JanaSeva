package com.lmntrx.shishubavan.DatabaseModels;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by livin on 27/6/16.
 */
@Table(name = "RailwayPolice")
public class RailwayPoliceTable extends Model {

    @Column(name = "PlaceName")
    public String placeName;



    @Column(name = "Number")
    public String number;

    @Column(name = "Enabled")
    public boolean enabled;

    public RailwayPoliceTable(){super();}

    public RailwayPoliceTable(String placeName,String number,Boolean enabled){
        this.placeName = placeName;
        this.number = number;
        this.enabled = enabled;
    }


}
