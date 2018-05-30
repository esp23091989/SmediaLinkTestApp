package com.example.moviestestapplication.presentation.di.components;

import com.example.moviestestapplication.presentation.di.components.ApplicationComponent;
import com.example.moviestestapplication.presentation.di.modules.DetailsMovieActivityModule;
import com.example.moviestestapplication.presentation.di.scope.PerActivity;
import com.example.moviestestapplication.presentation.presenter.DetailsMoviePresenter;
import com.example.moviestestapplication.presentation.view.DetailsMovieActivity;

import dagger.Component;

/**
 * Created by Юленька on 24.06.2017.
 */

//todo Почему dependencies? Проще с сабкомпонентом
@PerActivity
@Component(modules = DetailsMovieActivityModule.class, dependencies = ApplicationComponent.class)
public interface DetailsMovieActivityComponent {

    DetailsMoviePresenter getPresenter();

    void inject(DetailsMovieActivity activity);
}
