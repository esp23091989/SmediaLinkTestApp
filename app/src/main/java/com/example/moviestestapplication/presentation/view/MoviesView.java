package com.example.moviestestapplication.presentation.view;

import com.example.moviestestapplication.presentation.model.MovieModel;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import java.util.List;

public interface MoviesView extends MvpLceView<List<MovieModel>> {
    void addData(List<MovieModel> data);

    void showLoadMoreError();

    void openDetailMovieView(Integer id);
}
