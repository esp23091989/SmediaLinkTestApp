package com.example.moviestestapplication.data.repository;

import com.example.moviestestapplication.data.dto.mapper.MovieDTOMapper;
import com.example.moviestestapplication.data.repository.datasource.MoviesDataStore;
import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.domain.repository.MovieRepository;
import com.example.moviestestapplication.presentation.model.MovieModel;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

/**
 * Created by Юленька on 24.06.2017.
 */
public class MovieRepositoryImpl implements MovieRepository{

    private final MoviesDataStore remoteDataStore;
    private final MovieDTOMapper movieDTOMapper;

    @Inject
    MovieRepositoryImpl(@Named("RemoteMoviesDataStore") MoviesDataStore remoteDataStore, MovieDTOMapper movieDTOMapper) {
        this.remoteDataStore = remoteDataStore;
        this.movieDTOMapper = movieDTOMapper;
    }

    @Override
    public Observable<Movie> getMovieById(String apiKey, String language, int movieId) {
        return remoteDataStore.getMovie(apiKey, language, movieId)
                .map(movieDTOMapper::transform);
    }
}
