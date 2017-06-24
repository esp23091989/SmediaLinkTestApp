package com.example.moviestestapplication.domain.repository;

import com.example.moviestestapplication.domain.MoviesData;
import io.reactivex.Observable;

public interface MoviesDataRepository {

    Observable<MoviesData> getPopular(String apiKey, String language, Integer page);

    Observable<MoviesData> getTopRated(String apiKey, String language, Integer page);

}
