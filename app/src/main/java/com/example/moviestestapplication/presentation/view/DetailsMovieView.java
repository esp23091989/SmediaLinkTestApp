package com.example.moviestestapplication.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.moviestestapplication.presentation.model.MovieModel;

/**
 * Created by Юленька on 24.06.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface DetailsMovieView extends MvpView, LCEView {

    void setData(MovieModel movieModel);
}
