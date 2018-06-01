package com.example.moviestestapplication.domain.repository;

import android.arch.lifecycle.LiveData;

import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.domain.Resource;

import java.util.List;


public interface MoviesRepository {

    LiveData<Resource<List<Movie>>> getMovies();

}
