package com.ruenzuo.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CitiesData {

    @SerializedName("geonames")
    ArrayList<City> cities;

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

}
