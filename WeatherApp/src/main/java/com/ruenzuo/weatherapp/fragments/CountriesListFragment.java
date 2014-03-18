package com.ruenzuo.weatherapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.adapters.CountriesAdapter;
import com.ruenzuo.weatherapp.managers.WeatherAPIManager;
import com.ruenzuo.weatherapp.models.Country;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

public class CountriesListFragment extends ListFragment implements Observer<ArrayList<Country>>, OnRefreshListener {

    private Subscription subscription;
    private Observable observable;

    public interface OnCountrySelectedListener {
        public void onCountrySelected(Country country);
    }

    private OnCountrySelectedListener countrySelectedListener;

    private PullToRefreshLayout pullToRefreshLayout;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnCountrySelectedListener) {
            countrySelectedListener = (OnCountrySelectedListener)activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement MyListFragment.OnCountrySelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CountriesAdapter adapter = new CountriesAdapter(getActivity(), R.layout.row_city);
        setListAdapter(adapter);
        pullToRefreshLayout.setRefreshing(true);
        observable = WeatherAPIManager.INSTANCE.getCities();
        subscription = AndroidObservable.fromFragment(this, observable)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries, container, false);
        pullToRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.ptrLayout);
        ActionBarPullToRefresh.from(getActivity())
                .allChildrenArePullable()
                .listener(this)
                .setup(pullToRefreshLayout);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public void onRefreshStarted(View view) {

    }

    @Override
    public void onCompleted() {
        pullToRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError(Throwable e) {
        pullToRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onNext(ArrayList<Country> cities) {
        CountriesAdapter countriesAdapter = (CountriesAdapter)getListAdapter();
        countriesAdapter.clear();
        countriesAdapter.addAll(cities);
        countriesAdapter.notifyDataSetChanged();
    }

}
