package com.example.moviestestapplication.presentation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.domain.Resource;
import com.example.moviestestapplication.domain.repository.MoviesRepository;
import com.example.moviestestapplication.presentation.mapper.MovieModelDataMapper;

import java.util.List;

import javax.inject.Inject;

public class MoviesViewModel extends ViewModel{

    private MoviesRepository moviesRepository;
    private MediatorLiveData<Resource<List<Movie>>> movies = new MediatorLiveData<>();
    private MutableLiveData<String> events = new MutableLiveData<>();

    @Inject
    public MoviesViewModel(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;

        this.onFirstLaunch();
    }

    private void onFirstLaunch() {
        movies.setValue(Resource.Companion.loading());

        loadMovies();

        LiveData<Resource<List<Movie>>> movieSource = moviesRepository.getMovies();
        movies.addSource(events, event -> loadMovies());
    }

    private void loadMovies(){
        LiveData<Resource<List<Movie>>> movieSource = moviesRepository.getMovies();
        movies.setValue(Resource.Companion.loading());
        movies.addSource(moviesRepository.getMovies(), resource -> {
            movies.removeSource(movieSource);
            movies.setValue(resource);
        });
    }

    public MutableLiveData<String> getEvents() {
        return events;
    }

    public LiveData<Resource<List<Movie>>> getMovies(){
        return movies;
    }
}
