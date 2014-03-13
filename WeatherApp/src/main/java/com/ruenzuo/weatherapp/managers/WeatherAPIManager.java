package com.ruenzuo.weatherapp.managers;

import com.ruenzuo.weatherapp.helpers.networking.NetworkingHelper;
import com.ruenzuo.weatherapp.models.CountryData;
import com.ruenzuo.weatherapp.models.Country;

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

    public Observable<ArrayList<Country>> getCities() {
        return Observable.create(new Observable.OnSubscribe<ArrayList<Country>>() {
            @Override
            public void call(Subscriber<? super ArrayList<Country>> subscriber) {
                try {
                    CountryData countryData = getNetworkingHelper().getService().getCities("WeatherApp");
                    subscriber.onNext(countryData.getCities());
                    subscriber.onCompleted();
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }
            }
        });
    }


}
