package com.example.moviestestapplication.presentation.di.components;

import com.example.moviestestapplication.presentation.view.MoviesActivity;
import com.example.moviestestapplication.presentation.di.modules.MoviesActivityModule;
import com.example.moviestestapplication.presentation.di.scope.PerActivity;
import com.example.moviestestapplication.presentation.presenter.MoviesPresenter;

import dagger.Component;

@PerActivity
@Component(modules = MoviesActivityModule.class, dependencies = ApplicationComponent.class)
public interface MoviesActivityComponent {

    MoviesPresenter getPresenter();

    void inject(MoviesActivity moviesActivity);
}
