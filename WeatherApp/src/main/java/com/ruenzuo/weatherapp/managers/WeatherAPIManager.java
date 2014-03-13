package com.ruenzuo.weatherapp.managers;

import com.ruenzuo.weatherapp.models.City;
import java.util.ArrayList;
import rx.Observable;
import rx.Subscriber;

public enum WeatherAPIManager {

    INSTANCE;

    public Observable<ArrayList<City>> getCities() {
        return Observable.create(new Observable.OnSubscribe<ArrayList<City>>() {
            @Override
            public void call(Subscriber<? super ArrayList<City>> subscriber) {
                try {
                    subscriber.onNext(new ArrayList<City>());
                    subscriber.onCompleted();
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }
            }
        });
    }

}
