package com.example.moviestestapplication.presentation.di.modules;

import android.content.Context;
import android.view.View;

import com.example.moviestestapplication.R;
import com.example.moviestestapplication.app.TheApp;
import com.example.moviestestapplication.presentation.di.scope.PerActivity;
import com.example.moviestestapplication.presentation.view.MoviesActivity;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MoviesActivityModule {

    @Provides
    static View providesRootView(MoviesActivity activity){
        return activity.findViewById(R.id.rootView);
    }

    @Provides
    static Context provideContext(MoviesActivity activity){
        return activity;
    }

}
