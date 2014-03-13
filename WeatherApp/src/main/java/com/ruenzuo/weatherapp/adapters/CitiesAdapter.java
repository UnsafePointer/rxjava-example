package com.ruenzuo.weatherapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.models.City;

import java.util.ArrayList;

public class CitiesAdapter extends ArrayAdapter<City> implements Filterable {

    private int resourceId;

    public CitiesAdapter(Context context, int resource) {
        super(context, resource);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
            convertView = inflater.inflate(resourceId, null);
        }
        City city = getItem(position);
        TextView textCityName = (TextView) convertView.findViewById(R.id.txtViewCityName);
        textCityName.setText(city.getName());
        return convertView;
    }

}