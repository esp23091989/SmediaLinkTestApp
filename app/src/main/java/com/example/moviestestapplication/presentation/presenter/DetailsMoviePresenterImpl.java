package com.example.moviestestapplication.presentation.presenter;

import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.domain.interactor.GetMovie;
import com.example.moviestestapplication.presentation.mapper.MovieModelDataMapper;
import com.example.moviestestapplication.presentation.model.MovieModel;
import com.example.moviestestapplication.presentation.view.DetailsMovieView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Юленька on 24.06.2017.
 */
public class DetailsMoviePresenterImpl extends MvpBasePresenter<DetailsMovieView> implements DetailsMoviePresenter{

    private static final String API_KEY = "739fbf641b9f6c591db3df89748f399f";
    private static final String LANGUAGE = "ru";

    private final GetMovie getMovieUseCase;
    private final MovieModelDataMapper movieModelDataMapper;
    private MovieModel currentMovieModel;

    @Inject
    DetailsMoviePresenterImpl(GetMovie getMovieUseCase, MovieModelDataMapper movieModelDataMapper) {
        this.getMovieUseCase = getMovieUseCase;
        this.movieModelDataMapper = movieModelDataMapper;
    }

    @Override
    public void onStart(int movieId) {
        loadMovie(movieId);
    }

    private void loadMovie(int movieId) {
        if(isViewAttached())
            getView().showLoading(false);

        getMovieUseCase.execute(movieObserver,new GetMovie.RequestValue(API_KEY, LANGUAGE, movieId));
    }

    private void showMovieInView(Movie movie) {
        currentMovieModel = movieModelDataMapper.transform(movie);

        if(isViewAttached())
            getView().setData(currentMovieModel);
    }

    @Override
    public MovieModel getCurrentMovieModel() {
        return currentMovieModel;
    }

    private DisposableObserver<Movie> movieObserver = new DisposableObserver<Movie>() {
        @Override
        public void onNext(Movie movie) {
            showMovieInView(movie);
        }

        @Override
        public void onError(Throwable e) {
            if(isViewAttached())
                getView().showError(e,false);
        }

        @Override
        public void onComplete() {
            if(isViewAttached())
                getView().showContent();
        }
    };
}
