package com.example.moviestestapplication.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.presentation.model.MovieModel;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import java.util.List;

public interface MoviesView extends MvpView, CanShowError, CanShowLoading {

    void setData(List<MovieModel> data);

    void addData(List<MovieModel> data);

    void showLoadMoreError();

    void openDetailMovieView(Integer id);
}
