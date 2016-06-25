package com.lmntrx.shishubavan.DatabaseModels;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by livin on 24/6/16.
 */
@Table(name = "Excise")
public class ExciseTable extends Model {
    @Column(name = "PlaceName")
    public String placeName;


    @Column(name = "Number")
    public String number;

    @Column(name = "Enabled")
    public boolean enabled;

    public ExciseTable(){super();}

    public ExciseTable(String placeName,String number,Boolean enabled) {
        this.placeName = placeName;
        this.number = number;
        this.enabled = enabled;
    }
}
