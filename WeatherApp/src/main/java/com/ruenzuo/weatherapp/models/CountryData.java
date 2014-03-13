package com.ruenzuo.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CountryData {

    @SerializedName("geonames")
    ArrayList<Country> cities;

    public ArrayList<Country> getCities() {
        return cities;
    }

    public void setCities(ArrayList<Country> cities) {
        this.cities = cities;
    }

}
