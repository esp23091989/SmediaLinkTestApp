package com.example.moviestestapplication.domain.repository;

import android.arch.lifecycle.LiveData;

import com.example.moviestestapplication.domain.MoviesData;
import com.example.moviestestapplication.domain.Resource;

import io.reactivex.Observable;

public interface MoviesDataRepository {

    LiveData<Resource<MoviesData>> getPopular(String apiKey, String language, Integer page);

    Observable<MoviesData> getTopRated(String apiKey, String language, Integer page);

}
