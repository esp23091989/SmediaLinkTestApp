package com.example.moviestestapplication.presentation.mapper;

import android.support.annotation.NonNull;

import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.presentation.model.MovieModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class MovieModelDataMapper {

    @Inject
    MovieModelDataMapper() {
    }

    public List<MovieModel> transform(List<Movie> movieList){
        List<MovieModel> movieModelList = new ArrayList<>();
        for(Movie movie : movieList){
            MovieModel movieModel = transform(movie);
            movieModelList.add(movieModel);
        }
        return movieModelList;
    }

    public MovieModel transform(Movie movie){
        MovieModel movieModel = new MovieModel();
        movieModel.setId(movie.getId());
        movieModel.setTitle(movie.getTitle());
        movieModel.setOverview(movie.getOverview());
        movieModel.setPosterPath(movie.getPosterPath());
        movieModel.setVoteAverage(movie.getVoteAverage());
        movieModel.setReleaseDate(movie.getReleaseDate());

        return movieModel;
    }
}
