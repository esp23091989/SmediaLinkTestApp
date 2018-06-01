//package com.example.moviestestapplication.presentation.presenter;
//
//import com.arellomobile.mvp.InjectViewState;
//import com.arellomobile.mvp.MvpPresenter;
//import com.example.moviestestapplication.domain.Movie;
//import com.example.moviestestapplication.presentation.mapper.MovieModelDataMapper;
//import com.example.moviestestapplication.presentation.model.MovieModel;
//import com.example.moviestestapplication.presentation.view.DetailsMovieView;
//
//import javax.inject.Inject;
//
//import io.reactivex.observers.DisposableObserver;
//
//@InjectViewState
//public class DetailsMoviePresenter extends MvpPresenter<DetailsMovieView> {
//
//    private static final String API_KEY = "739fbf641b9f6c591db3df89748f399f";
//    private static final String LANGUAGE = "ru";
//
//    private final GetMovie getMovieUseCase;
//    private final MovieModelDataMapper movieModelDataMapper;
//    private MovieModel currentMovieModel;
//    private Integer movieId;
//
//
//    @Inject
//    DetailsMoviePresenter(Integer movieId, GetMovie getMovieUseCase, MovieModelDataMapper movieModelDataMapper) {
//        this.movieId = movieId;
//        this.getMovieUseCase = getMovieUseCase;
//        this.movieModelDataMapper = movieModelDataMapper;
//
//    }
//
//    @Override
//    protected void onFirstViewAttach() {
//        loadMovie(movieId);
//    }
//
//    private void loadMovie(int movieId) {
//        getViewState().showLoading();
//        getMovieUseCase.execute(movieObserver,new GetMovie.RequestValue(API_KEY, LANGUAGE, movieId));
//    }
//
//    private void showMovieInView(Movie movie) {
//        currentMovieModel = movieModelDataMapper.transform(movie);
//
//        getViewState().setData(currentMovieModel);
//    }
//
//    public MovieModel getCurrentMovieModel() {
//        return currentMovieModel;
//    }
//
//    private DisposableObserver<Movie> movieObserver = new DisposableObserver<Movie>() {
//        @Override
//        public void onNext(Movie movie) {
//            showMovieInView(movie);
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            getViewState().showError(e);
//        }
//
//        @Override
//        public void onComplete() {
//            getViewState().showContent();
//        }
//    };
//}
