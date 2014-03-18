package com.ruenzuo.weatherapp.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.fragments.CountriesListFragment;
import com.ruenzuo.weatherapp.models.Country;

public class CountriesActivity extends ActionBarActivity implements
        CountriesListFragment.OnCountrySelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        setTitle("Countries");
    }

    @Override
    public void onCountrySelected(Country country) {

    }

}
