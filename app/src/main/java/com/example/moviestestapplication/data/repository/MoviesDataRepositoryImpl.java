package com.example.moviestestapplication.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Transformations;
import android.view.animation.Transformation;

import com.example.moviestestapplication.data.api.ApiEmptyResponse;
import com.example.moviestestapplication.data.api.ApiErrorResponse;
import com.example.moviestestapplication.data.api.ApiResponse;
import com.example.moviestestapplication.data.api.ApiSuccessResponse;
import com.example.moviestestapplication.data.dto.MoviesDataDTO;
import com.example.moviestestapplication.data.dto.mapper.MoviesDataDTOMapper;
import com.example.moviestestapplication.data.repository.datasource.MoviesDataStore;
import com.example.moviestestapplication.domain.MoviesData;
import com.example.moviestestapplication.domain.Resource;
import com.example.moviestestapplication.domain.repository.MoviesDataRepository;
import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

public class MoviesDataRepositoryImpl implements MoviesDataRepository {

    private final MoviesDataStore remoteDataStore;
    private final MoviesDataDTOMapper moviesDataDTOMapper;
    private final MediatorLiveData<Resource<MoviesData>> popularMovies = new MediatorLiveData<>();

    @Inject
    MoviesDataRepositoryImpl(@Named("RemoteMoviesDataStore") MoviesDataStore remoteDataStore,
                             MoviesDataDTOMapper moviesDataDTOMapper) {

        this.remoteDataStore = remoteDataStore;
        this.moviesDataDTOMapper = moviesDataDTOMapper;
    }

    @Override
    public LiveData<Resource<MoviesData>> getPopular(String apiKey, String language, Integer page) {
        popularMovies.postValue(Resource.Companion.loading());
        LiveData<ApiResponse<MoviesDataDTO>> networkDataSource = remoteDataStore.getPopularMoviesDataDTO(apiKey, language, page);
        popularMovies.addSource(networkDataSource, apiResponse -> {
            popularMovies.removeSource(networkDataSource);
            if(apiResponse instanceof ApiSuccessResponse){
                MoviesData data = moviesDataDTOMapper.transform(((ApiSuccessResponse<MoviesDataDTO>) apiResponse).getBody());
                popularMovies.setValue(Resource.Companion.success(data));
            }else if(apiResponse instanceof ApiErrorResponse)
                popularMovies.setValue(Resource.Companion.error(((ApiErrorResponse) apiResponse).getErrorMessage(), null));
            else
                popularMovies.setValue(Resource.Companion.error("unknown error", null));
        });

        return popularMovies;
    }

    @Override
    public Observable<MoviesData> getTopRated(String apiKey, String language, Integer page) {
        return remoteDataStore.getTopRatedMoviesDataDTO(apiKey, language, page)
                .map(moviesDataDTOMapper::transform);
    }
}
