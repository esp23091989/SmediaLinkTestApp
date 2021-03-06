package com.example.moviestestapplication.data.repository.datasource;

import com.example.moviestestapplication.data.api.ApiInterface;
import com.example.moviestestapplication.data.dto.MovieDTO;
import com.example.moviestestapplication.data.dto.MoviesDataDTO;
import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.presentation.model.MovieModel;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoteMoviesDataStore implements MoviesDataStore{
    private final ApiInterface apiInterface;

    @Inject
    RemoteMoviesDataStore(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    @Override
    public Observable<MoviesDataDTO> getPopularMoviesDataDTO(String apiKey, String language, Integer page) {
        return apiInterface.getPopularMovies(apiKey, language, page);
    }

    @Override
    public Observable<MoviesDataDTO> getTopRatedMoviesDataDTO(String apiKey, String language, Integer page) {
        return apiInterface.getTopRatedMovies(apiKey, language, page);
    }

    @Override
    public Observable<MovieDTO> getMovie(String apiKey, String language, int movieId) {
        return apiInterface.getMovie(movieId,apiKey, language);
    }

}
