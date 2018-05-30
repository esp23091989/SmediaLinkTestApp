package com.example.moviestestapplication.presentation.di.modules;

import com.example.moviestestapplication.data.api.ApiInterface;
import com.example.moviestestapplication.data.api.ApiModule;
import com.example.moviestestapplication.data.repository.MovieRepositoryImpl;
import com.example.moviestestapplication.data.repository.MoviesDataRepositoryImpl;
import com.example.moviestestapplication.data.repository.datasource.MoviesDataStore;
import com.example.moviestestapplication.data.repository.datasource.RemoteMoviesDataStore;
import com.example.moviestestapplication.domain.repository.MovieRepository;
import com.example.moviestestapplication.domain.repository.MoviesDataRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@Module
public class ApplicationModule {

    @Singleton
    @Provides
    //todo Почему не квалификаторы?
    @Named("ThreadScheduler")
    Scheduler provideThreadScheduler(){
        return Schedulers.io();
    }

    @Singleton
    @Provides
    @Named("PostScheduler")
    Scheduler providePostScheduler(){
        return AndroidSchedulers.mainThread();
    }

    @Singleton
    @Provides
    ApiInterface provideApiInterface(){
        return ApiModule.getApiInterface("https://api.themoviedb.org/3/");
    }

    @Singleton
    @Provides
    MoviesDataRepository provideMoviesDataRepository(MoviesDataRepositoryImpl repository){
        return repository;
    }

    @Singleton
    @Provides
    MovieRepository provideMoviesRepository(MovieRepositoryImpl repository){
        return repository;
    }

    @Singleton
    @Provides
    @Named("RemoteMoviesDataStore")
    MoviesDataStore provideRemoteDataStore(RemoteMoviesDataStore remoteMoviesDataStore){
        return remoteMoviesDataStore;
    }
}
