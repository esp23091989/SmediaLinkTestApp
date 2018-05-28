package com.example.moviestestapplication.presentation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.moviestestapplication.domain.MoviesData;
import com.example.moviestestapplication.domain.Resource;
import com.example.moviestestapplication.domain.Status;
import com.example.moviestestapplication.domain.interactor.GetPopularMovies;
import com.example.moviestestapplication.presentation.mapper.MovieModelDataMapper;
import com.example.moviestestapplication.presentation.model.MovieModel;

import java.util.List;

import javax.inject.Inject;

public class MoviesViewModel extends ViewModel{

    private static final String API_KEY = "739fbf641b9f6c591db3df89748f399f";
    private static final String LANGUAGE = "ru";

    private GetPopularMovies getPopularMoviesUseCase;
    private final MovieModelDataMapper movieModelDataMapper;
    private MediatorLiveData<Resource<List<MovieModel>>> movies = new MediatorLiveData<>();

    @Inject
    public MoviesViewModel(GetPopularMovies getPopularMovies,
                           MovieModelDataMapper movieModelDataMapper) {
        this.getPopularMoviesUseCase = getPopularMovies;
        this.movieModelDataMapper = movieModelDataMapper;
        this.onFirstLaunch();
    }

    private void onFirstLaunch() {
        this.movies.addSource(
                Transformations.map(
                        getPopularMoviesUseCase.getData(new GetPopularMovies.RequestValue(API_KEY, LANGUAGE, null)),
                        moviesDataResource -> {
                            Resource<List<MovieModel>> resource;
                            if(moviesDataResource.getStatus() == Status.SUCCESS)
                                resource = Resource.Companion.success(
                                        movieModelDataMapper.transform(moviesDataResource.getData().getMovies())
                                );
                            else if(moviesDataResource.getStatus() == Status.LOADING)
                                resource = Resource.Companion.loading();
                            else{
                                String errorMessage = moviesDataResource.getMessage() != null
                                        ? moviesDataResource.getMessage()
                                        : "unknown error";

                                resource = Resource.Companion.error(errorMessage,null);
                            }


                            return resource;
                        }
                ),

                value -> {
                    movies.setValue(value);
                }
        );
    }


    public LiveData<Resource<List<MovieModel>>> getMovies(){
        return movies;
    }
}
