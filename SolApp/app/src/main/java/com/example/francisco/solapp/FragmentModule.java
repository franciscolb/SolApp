package com.example.francisco.solapp;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract WeatherProfileFragment contributeUserProfileFragment();
}