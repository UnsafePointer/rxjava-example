package com.ruenzuo.weatherapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.adapters.CitiesAdapter;
import com.ruenzuo.weatherapp.managers.WeatherAPIManager;
import com.ruenzuo.weatherapp.models.City;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CitiesListFragment extends ListFragment implements Observer<ArrayList<City>> {

    private Subscription subscription;

    public interface OnCitySelectedListener {
        public void onCitySelected(City city);
    }

    public interface OnLoadCitiesListener {
        public void onBeginLoadCities();
        public void onFinishLoadCities();
    }

    private OnCitySelectedListener citySelectedListener;
    private OnLoadCitiesListener loadCitiesListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnCitySelectedListener) {
            citySelectedListener = (OnCitySelectedListener)activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement MyListFragment.OnCitySelectedListener");
        }
        if (activity instanceof OnLoadCitiesListener) {
            loadCitiesListener = (OnLoadCitiesListener)activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement MyListFragment.OnLoadCitiesListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadCitiesListener.onBeginLoadCities();
        CitiesAdapter adapter = new CitiesAdapter(getActivity(), R.layout.row_city);
        setListAdapter(adapter);
        subscription = AndroidObservable.fromFragment(this, WeatherAPIManager.INSTANCE.getCities())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public void onCompleted() {
        loadCitiesListener.onFinishLoadCities();
    }

    @Override
    public void onError(Throwable e) {
        loadCitiesListener.onFinishLoadCities();
    }

    @Override
    public void onNext(ArrayList<City> cities) {
        CitiesAdapter citiesAdapter = (CitiesAdapter)getListAdapter();
        citiesAdapter.clear();
        citiesAdapter.addAll(cities);
        citiesAdapter.notifyDataSetChanged();
    }

}
