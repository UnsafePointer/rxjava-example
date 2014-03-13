package com.ruenzuo.weatherapp.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.fragments.CitiesListFragment;
import com.ruenzuo.weatherapp.models.City;

import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

public class CitiesActivity extends ActionBarActivity implements OnRefreshListener,
        CitiesListFragment.OnCitySelectedListener, CitiesListFragment.OnLoadCitiesListener {


    private PullToRefreshLayout ptrLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        setTitle("Cities");
        ptrLayout = (PullToRefreshLayout) findViewById(R.id.ptrLayout);
        ActionBarPullToRefresh.from(this)
                .listener(this)
                .setup(ptrLayout);
    }

    @Override
    public void onRefreshStarted(View view) {

    }

    @Override
    public void onCitySelected(City city) {

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
