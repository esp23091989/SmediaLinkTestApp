package com.example.moviestestapplication.data.repository.datasource;

import com.example.moviestestapplication.data.dto.MovieDTO;
import com.example.moviestestapplication.data.dto.MoviesDataDTO;
import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.presentation.model.MovieModel;

import io.reactivex.Observable;

public interface MoviesDataStore {

    Observable<MoviesDataDTO> getPopularMoviesDataDTO(String apiKey, String language, Integer page);

    Observable<MoviesDataDTO> getTopRatedMoviesDataDTO(String apiKey, String language, Integer page);

    Observable<MovieDTO> getMovie(String apiKey, String language, int movieId);
}
