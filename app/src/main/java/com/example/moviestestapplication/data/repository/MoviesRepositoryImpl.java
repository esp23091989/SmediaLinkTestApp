package com.example.moviestestapplication.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Transformations;

import com.example.moviestestapplication.data.api.ApiEmptyResponse;
import com.example.moviestestapplication.data.api.ApiSuccessResponse;
import com.example.moviestestapplication.data.dto.MoviesDataDTO;
import com.example.moviestestapplication.data.dto.mapper.MoviesDataDTOMapper;
import com.example.moviestestapplication.data.repository.datasource.MoviesDataStore;
import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.domain.MoviesData;
import com.example.moviestestapplication.domain.Resource;
import com.example.moviestestapplication.domain.repository.MoviesRepository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class MoviesRepositoryImpl implements MoviesRepository {

    private final MoviesDataStore remoteDataStore;
    private final MoviesDataDTOMapper moviesDataDTOMapper;
    private final MediatorLiveData<Resource<MoviesData>> popularMovies = new MediatorLiveData<>();

    @Inject
    MoviesRepositoryImpl(@Named("RemoteMoviesDataStore") MoviesDataStore remoteDataStore,
                             MoviesDataDTOMapper moviesDataDTOMapper) {

        this.remoteDataStore = remoteDataStore;
        this.moviesDataDTOMapper = moviesDataDTOMapper;
    }

    @Override
    public LiveData<Resource<List<Movie>>> getMovies() {

        return Transformations.map(remoteDataStore.getMovies(), apiResponse -> {
            if(apiResponse instanceof ApiSuccessResponse){
                List<Movie> movies = moviesDataDTOMapper.transform(((ApiSuccessResponse<MoviesDataDTO>) apiResponse).getBody());
                return Resource.Companion.success(movies);
            }else if(apiResponse instanceof ApiEmptyResponse)
                return Resource.Companion.success(Collections.emptyList());
            else
                return Resource.Companion.error("unknown error", null);
        });
    }

}
