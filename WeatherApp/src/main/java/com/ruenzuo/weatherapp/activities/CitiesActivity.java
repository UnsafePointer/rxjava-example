package com.ruenzuo.weatherapp.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.managers.WeatherAPIManager;
import com.ruenzuo.weatherapp.models.City;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CitiesActivity extends ActionBarActivity implements Observer<ArrayList<City>> {

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subscription = AndroidObservable.fromActivity(this, WeatherAPIManager.INSTANCE.getCities())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(ArrayList<City> cities) {
        Log.d("CitiesActivity", cities.toString());
    }
}
