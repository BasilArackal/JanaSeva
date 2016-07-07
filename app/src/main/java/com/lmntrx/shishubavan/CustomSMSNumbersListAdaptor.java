package com.lmntrx.shishubavan;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/***
 * Created by livin on 8/7/16.
 */
public class CustomSMSNumbersListAdaptor extends ArrayAdapter<CustomSMSNumber> {

    ArrayList<CustomSMSNumber> list;

    public CustomSMSNumbersListAdaptor(Activity context, ArrayList<CustomSMSNumber> list) {
        super(context, 0, list);
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_sms_numbers_list_item, parent, false);
        }

        CustomSMSNumber item = list.get(position);
        String name = item.getName();
        String number = item.getNumber();
        ((TextView)convertView.findViewById(R.id.customSmsToName)).setText(name);
        ((TextView)convertView.findViewById(R.id.customSmsToNumber)).setText(number);

        return convertView;
    }
}
