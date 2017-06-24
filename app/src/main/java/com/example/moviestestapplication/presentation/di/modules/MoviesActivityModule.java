package com.example.moviestestapplication.presentation.di.modules;

import com.example.moviestestapplication.presentation.di.scope.PerActivity;
import com.example.moviestestapplication.presentation.presenter.MoviesPresenter;
import com.example.moviestestapplication.presentation.presenter.MoviesPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesActivityModule {

    @Provides
    @PerActivity
    MoviesPresenter providePresenter(MoviesPresenterImpl presenter){
        return presenter;
    }
}
