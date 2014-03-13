package com.ruenzuo.weatherapp.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.managers.WeatherAPIManager;
import com.ruenzuo.weatherapp.models.City;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

public class CitiesActivity extends ActionBarActivity implements Observer<ArrayList<City>>, OnRefreshListener {

    private Subscription subscription;
    private PullToRefreshLayout ptrLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ptrLayout = (PullToRefreshLayout) findViewById(R.id.ptrLayout);
        ActionBarPullToRefresh.from(this)
                .allChildrenArePullable()
                .listener(this)
                .setup(ptrLayout);
        ptrLayout.setRefreshing(true);
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
        ptrLayout.setRefreshing(false);
    }

    @Override
    public void onError(Throwable e) {
        ptrLayout.setRefreshing(false);
    }

    @Override
    public void onNext(ArrayList<City> cities) {

    }

    @Override
    public void onRefreshStarted(View view) {

    }

}
