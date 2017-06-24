package com.example.moviestestapplication.presentation.presenter;

import com.example.moviestestapplication.presentation.model.MovieModel;
import com.example.moviestestapplication.presentation.view.MoviesView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface MoviesPresenter extends MvpPresenter<MoviesView> {

    void onStart();

    void onPopularOptionItemSelected();

    void onTopRatedOptionItemSelected();

    void onBottomContentReached();

    void onMovieClicked(MovieModel movieModel);

}
