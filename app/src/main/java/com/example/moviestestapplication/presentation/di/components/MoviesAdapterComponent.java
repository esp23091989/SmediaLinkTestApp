package com.example.moviestestapplication.presentation.di.components;

import com.example.moviestestapplication.presentation.di.modules.MoviesAdapterModule;
import com.example.moviestestapplication.presentation.di.scope.PerActivity;
import com.example.moviestestapplication.presentation.di.scope.PerAdapter;
import com.example.moviestestapplication.presentation.view.adapters.MoviesAdapter;

import dagger.Component;

/**
 * Created by esp on 07.05.2018.
 */

@PerAdapter
@Component(modules = MoviesAdapterModule.class, dependencies = HasMoviesAdapterDepenedencies.class)
public interface MoviesAdapterComponent {

    void inject(MoviesAdapter moviesAdapter);
}
