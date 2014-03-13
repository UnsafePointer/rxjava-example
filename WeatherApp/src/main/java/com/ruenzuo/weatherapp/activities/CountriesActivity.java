package com.ruenzuo.weatherapp.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.fragments.CountriesListFragment;
import com.ruenzuo.weatherapp.models.Country;

import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

public class CountriesActivity extends ActionBarActivity implements OnRefreshListener,
        CountriesListFragment.OnCitySelectedListener, CountriesListFragment.OnLoadCitiesListener {


    private PullToRefreshLayout ptrLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        setTitle("Countries");
        ptrLayout = (PullToRefreshLayout) findViewById(R.id.ptrLayout);
        ActionBarPullToRefresh.from(this)
                .listener(this)
                .setup(ptrLayout);
    }

    @Override
    public void onRefreshStarted(View view) {

    }

    @Override
    public void onCitySelected(Country country) {

    }

    @Override
    public void onBeginLoadCities() {
        ptrLayout.setRefreshing(true);
    }

    @Override
    public void onFinishLoadCities() {
        ptrLayout.setRefreshing(false);
    }
}
