package com.lmntrx.shishubavan.DatabaseModels;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/***
 * Created by livin on 5/5/16.
 */
@Table(name = "FireForce")
public class FireForceTable extends Model {

    @Column(name = "PlaceName")
    public String placeName;



    @Column(name = "Number")
    public String number;

    @Column(name = "Enabled")
    public Boolean enabled;

    public FireForceTable(String placeName,String number,Boolean enabled){
        this.placeName = placeName;
        this.number = number;
        this.enabled = enabled;
    }

}
