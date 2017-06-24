package com.example.moviestestapplication.presentation.presenter;

import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.presentation.model.MovieModel;
import com.example.moviestestapplication.presentation.view.DetailsMovieView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

/**
 * Created by Юленька on 24.06.2017.
 */
public interface DetailsMoviePresenter extends MvpPresenter<DetailsMovieView>{

    void onStart(int movieId);

    MovieModel getCurrentMovieModel();
}
