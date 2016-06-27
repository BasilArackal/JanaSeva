package com.lmntrx.shishubavan.DatabaseModels;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/***
 * Created by livin on 24/6/16.
 */
@Table(name = "BloodBanksTable")
public class BloodBanksTable extends Model {
    @Column(name = "PlaceName")
    public String placeName;

    @Column(name = "Number")
    public String number;

    @Column(name = "Enabled")
    public boolean enabled;

    public BloodBanksTable(){super();}

    public BloodBanksTable(String placeName,String number,Boolean enabled){
        this.placeName = placeName;
        this.number = number;
        this.enabled = enabled;
    }
}
