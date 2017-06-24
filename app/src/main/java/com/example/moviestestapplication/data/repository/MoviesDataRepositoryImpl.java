package com.example.moviestestapplication.data.repository;

import com.example.moviestestapplication.data.dto.mapper.MoviesDataDTOMapper;
import com.example.moviestestapplication.data.repository.datasource.MoviesDataStore;
import com.example.moviestestapplication.domain.MoviesData;
import com.example.moviestestapplication.domain.repository.MoviesDataRepository;
import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

public class MoviesDataRepositoryImpl implements MoviesDataRepository {

    private final MoviesDataStore remoteDataStore;
    private final MoviesDataDTOMapper moviesDataDTOMapper;

    @Inject
    MoviesDataRepositoryImpl(@Named("RemoteMoviesDataStore") MoviesDataStore remoteDataStore,
                             MoviesDataDTOMapper moviesDataDTOMapper) {

        this.remoteDataStore = remoteDataStore;
        this.moviesDataDTOMapper = moviesDataDTOMapper;
    }

    @Override
    public Observable<MoviesData> getPopular(String apiKey, String language, Integer page) {
        return remoteDataStore.getPopularMoviesDataDTO(apiKey, language, page)
                .map(moviesDataDTOMapper::transform);
    }

    @Override
    public Observable<MoviesData> getTopRated(String apiKey, String language, Integer page) {
        return remoteDataStore.getTopRatedMoviesDataDTO(apiKey, language, page)
                .map(moviesDataDTOMapper::transform);
    }
}
