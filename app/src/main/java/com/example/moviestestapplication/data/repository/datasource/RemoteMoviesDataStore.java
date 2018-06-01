package com.example.moviestestapplication.data.repository.datasource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.example.moviestestapplication.data.api.ApiInterface;
import com.example.moviestestapplication.data.api.ApiResponse;
import com.example.moviestestapplication.data.dto.MovieDTO;
import com.example.moviestestapplication.data.dto.MoviesDataDTO;
import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.domain.MoviesData;
import com.example.moviestestapplication.domain.Resource;
import com.example.moviestestapplication.presentation.model.MovieModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoteMoviesDataStore implements MoviesDataStore{
    private final ApiInterface apiInterface;
    private static final String API_KEY = "739fbf641b9f6c591db3df89748f399f";

    @Inject
    RemoteMoviesDataStore(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    @Override
    public LiveData<ApiResponse<MoviesDataDTO>> getMovies() {
        return apiInterface.getMovies(API_KEY);
    }

}
