package com.example.moviestestapplication.presentation.di.components;

import com.example.moviestestapplication.app.TheApp;
import com.example.moviestestapplication.data.api.ApiInterface;
import com.example.moviestestapplication.data.repository.datasource.MoviesDataStore;
import com.example.moviestestapplication.domain.repository.MovieRepository;
import com.example.moviestestapplication.domain.repository.MoviesDataRepository;
import com.example.moviestestapplication.presentation.di.modules.ApplicationModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @Named("ThreadScheduler")
    Scheduler getThreadScheduler();

    @Named("PostScheduler")
    Scheduler getPostScheduler();

    MoviesDataRepository getMoviesDataRepository();

    MovieRepository getMovieRepository();

    @Named("RemoteMoviesDataStore")
    MoviesDataStore getMoviesDataStore();

    ApiInterface getApiInterface();

    void inject(TheApp app);
}
