package com.ruenzuo.weatherapp.helpers.networking;

import com.ruenzuo.weatherapp.models.CitiesData;

import retrofit.http.GET;
import retrofit.http.Query;

public interface APIService {

    @GET("/countryInfoJSON")
    CitiesData getCities(@Query("username") String username);

}
