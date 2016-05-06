package com.lmntrx.shishubavan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;

/***
 * Created by livin on 4/5/16.
 */
public class NumbersListAdaptor extends ArrayAdapter<String> implements CheckBox.OnCheckedChangeListener {

    Activity activity;
    ArrayList list;
    String TYPE;

    public NumbersListAdaptor(Activity activity, ArrayList<String> stringArrayList, String TYPE){
        super(activity,0,stringArrayList);
        this.activity = activity;
        this.list = stringArrayList;
        this.TYPE = TYPE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.numbers_list_item, parent, false);
        }

            String item = list.get(position).toString();
            String placeName = item.substring(0,item.indexOf("|"));
            Boolean enabled = (item.substring(item.indexOf("|") + 1)).equals("true");
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.list_item_checkbox);
        if (!placeName.equalsIgnoreCase("null")){
            checkBox.setText(placeName);
            checkBox.setChecked(enabled);
            return convertView;
        }else return convertView;

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        CheckBox checkBox = (CheckBox)buttonView;
        MotherOfDatabases.UpdateItem(TYPE,checkBox.getText().toString(),isChecked);
    }
}
