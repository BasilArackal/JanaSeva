package com.lmntrx.shishubavan.DatabaseModels;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by livin on 25/6/16.
 */
@Table(name = "VigilanceTable")
public class VigilanceTable extends Model {
    @Column(name = "PlaceName")
    public String placeName;

    @Column(name = "Number")
    public String number;

    @Column(name = "Enabled")
    public boolean enabled;

    public VigilanceTable(){super();}

    public VigilanceTable(String placeName, String number, Boolean enabled){
        this.placeName = placeName;
        this.number = number;
        this.enabled = enabled;
    }
}
