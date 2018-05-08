package com.example.moviestestapplication.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.moviestestapplication.presentation.model.MovieModel;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MoviesView extends MvpView, LCEView {

    void setData(List<MovieModel> data);

    void addData(List<MovieModel> data);

    void showLoadMoreError();
}
