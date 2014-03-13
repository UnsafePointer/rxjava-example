package com.ruenzuo.weatherapp.helpers.networking;

import com.ruenzuo.weatherapp.models.CountryData;

import retrofit.http.GET;
import retrofit.http.Query;

public interface APIService {

    @GET("/countryInfoJSON")
    CountryData getCities(@Query("username") String username);

}
