package com.example.francisco.solapp;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeatherProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeatherProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherProfileFragment extends Fragment {
    // FOR DATA
    public static final String UID_KEY = "uid";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private WeatherProfileViewModel viewModel;

    // FOR DESIGN
    //@BindView(R.id.fragment_user_profile_image) ImageView imageView;
    @BindView(R.id.owner) TextView owner;
    @BindView(R.id.country) TextView country;
    @BindView(R.id.dataUpdate) TextView dataUpdate;
    @BindView(R.id.forecastDate) TextView forecastDate;
    @BindView(R.id.tempMin) TextView tempMin;
    @BindView(R.id.tempMax) TextView tempMax;


    public WeatherProfileFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel(){
        String userLogin = getArguments().getString(UID_KEY);
        userLogin="1010500";
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherProfileViewModel.class);
        viewModel.init(userLogin);
        viewModel.getWeather().observe(this, weather -> updateUI(weather));
    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable Weather weather){
        if (weather != null){
            //Glide.with(this).load(user.getAvatar_url()).apply(RequestOptions.circleCropTransform()).into(imageView);
            this.owner.setText(weather.getOwner());
            this.country.setText(weather.getCountry());
            this.dataUpdate.setText(weather.getDate());
            this.forecastDate.setText(weather.getData().get(0).getForecastDate());
            this.tempMin.setText(weather.getData().get(0).gettMin()+" ºC");
            this.tempMax.setText(weather.getData().get(0).gettMax()+" ºC");
        }
    }

}
