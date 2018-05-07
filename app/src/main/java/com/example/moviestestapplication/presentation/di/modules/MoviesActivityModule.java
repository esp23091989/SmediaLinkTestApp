package com.example.moviestestapplication.presentation.di.modules;

import android.view.View;

import com.example.moviestestapplication.R;
import com.example.moviestestapplication.presentation.di.scope.PerActivity;
import com.example.moviestestapplication.presentation.presenter.MoviesPresenter;
import com.example.moviestestapplication.presentation.view.MoviesActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesActivityModule {

    private MoviesActivity activity;

    public MoviesActivityModule(MoviesActivity activity){
        this.activity = activity;
    }

    @Provides
    @PerActivity
    View providesRootView(){
        return activity.findViewById(R.id.rootView);
    }
}
