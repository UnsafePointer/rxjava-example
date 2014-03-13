package com.ruenzuo.weatherapp.helpers.networking;

import retrofit.RestAdapter;

public class NetworkingHelper {

    private APIService service;

    public APIService getService() {
        if (service == null) {
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://api.geonames.org").build();
            service = restAdapter.create(APIService.class);
        }
        return service;
    }

}
