package com.lmntrx.shishubavan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

/***
 * Created by livin on 4/5/16.
 */
public class NumbersListAdaptor extends ArrayAdapter<NumbersListModel> {

    Activity activity;
    ArrayList<NumbersListModel> list;
    String TYPE;

    public NumbersListAdaptor(Activity activity, ArrayList<NumbersListModel> list, String TYPE){
        super(activity,0,list);
        this.activity = activity;
        this.list = list;
        this.TYPE = TYPE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.numbers_list_item, parent, false);
        }
            list = MotherOfDatabases.getAllPlaces(TYPE);
            NumbersListModel item = list.get(position);
            String placeName = item.getPlace();
            String number = item.getNumber();
            Boolean enabled = item.isEnabled();
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.list_item_checkbox);
            checkBox.setText(String.format("%s: %s", placeName, number));
            checkBox.setChecked(enabled);
            return convertView;

    }
}
