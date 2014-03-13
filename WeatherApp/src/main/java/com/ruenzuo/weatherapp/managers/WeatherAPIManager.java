package com.ruenzuo.weatherapp.managers;

import com.ruenzuo.weatherapp.helpers.networking.NetworkingHelper;
import com.ruenzuo.weatherapp.models.CitiesData;
import com.ruenzuo.weatherapp.models.City;
import java.util.ArrayList;
import rx.Observable;
import rx.Subscriber;

public enum WeatherAPIManager {

    INSTANCE;

    private NetworkingHelper networkingHelper;

    public NetworkingHelper getNetworkingHelper() {
        if (networkingHelper == null) {
            networkingHelper = new NetworkingHelper();
        }
        return networkingHelper;
    }

    public Observable<ArrayList<City>> getCities() {
        return Observable.create(new Observable.OnSubscribe<ArrayList<City>>() {
            @Override
            public void call(Subscriber<? super ArrayList<City>> subscriber) {
                try {
                    CitiesData citiesData = getNetworkingHelper().getService().getCities("WeatherApp");
                    subscriber.onNext(citiesData.getCities());
                    subscriber.onCompleted();
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }
            }
        });
    }


}
