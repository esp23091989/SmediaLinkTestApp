package com.example.moviestestapplication.domain.repository;

import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.presentation.model.MovieModel;

import io.reactivex.Observable;

/**
 * Created by Юленька on 24.06.2017.
 */
public interface MovieRepository {
    Observable<Movie> getMovieById(String apiKey, String language, int movieId);
}
