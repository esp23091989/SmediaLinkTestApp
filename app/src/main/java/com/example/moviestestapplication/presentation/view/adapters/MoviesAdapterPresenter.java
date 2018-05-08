package com.example.moviestestapplication.presentation.view.adapters;

import com.arellomobile.mvp.MvpPresenter;
import com.example.moviestestapplication.navigation.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

/**
 * Created by esp on 07.05.2018.
 */

public class MoviesAdapterPresenter extends MvpPresenter<MoviesAdapterView> {

    private final Router router;

    @Inject
    public MoviesAdapterPresenter(Router router) {
        this.router = router;
    }

    public void movieClicked(Integer id) {
        router.navigateTo(Screens.DETAILS_MOVIE_SCREEN, id);
    }
}
