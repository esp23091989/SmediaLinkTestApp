package com.example.moviestestapplication.presentation.di.modules;

import com.example.moviestestapplication.presentation.di.scope.PerActivity;
import com.example.moviestestapplication.presentation.presenter.DetailsMoviePresenter;
import com.example.moviestestapplication.presentation.presenter.DetailsMoviePresenterImpl;
import com.example.moviestestapplication.presentation.presenter.MoviesPresenter;
import com.example.moviestestapplication.presentation.presenter.MoviesPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Юленька on 24.06.2017.
 */

@Module
public class DetailsMovieActivityModule {

    @Provides
    @PerActivity
    DetailsMoviePresenter providePresenter(DetailsMoviePresenterImpl presenter){
        return presenter;
    }
}
