package com.example.moviestestapplication.presentation.presenter;

import com.example.moviestestapplication.presentation.model.MovieModel;


public interface MoviesPresenter {

    void onPopularOptionItemSelected();

    void onTopRatedOptionItemSelected();

    void onBottomContentReached();

    void onMovieClicked(MovieModel movieModel);

}
