package com.ruenzuo.weatherapp.models;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("countryName")
    private String name;
    @SerializedName("countryCode")
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
