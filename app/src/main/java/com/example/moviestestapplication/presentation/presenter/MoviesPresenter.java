package com.example.moviestestapplication.presentation.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.domain.MoviesData;
import com.example.moviestestapplication.domain.interactor.GetPopularMovies;
import com.example.moviestestapplication.domain.interactor.GetTopRatedMovies;
import com.example.moviestestapplication.presentation.mapper.MovieModelDataMapper;
import com.example.moviestestapplication.presentation.model.MovieModel;
import com.example.moviestestapplication.presentation.view.MoviesView;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.observers.DisposableObserver;

@InjectViewState
public class MoviesPresenter extends MvpPresenter<MoviesView> {

    private static final String API_KEY = "739fbf641b9f6c591db3df89748f399f";
    private static final String LANGUAGE = "ru";

    private final GetPopularMovies getPopularMoviesUseCase;
    private final GetTopRatedMovies getTopRatedMoviesUseCase;
    private final MovieModelDataMapper movieModelDataMapper;
    private MoviesType moviesType = MoviesType.POPULAR;

    private Integer currentPage = 0;
    private Integer totalPages ;

    private enum MoviesType{
        POPULAR, TOP_RATED
    }

    @Inject
    MoviesPresenter(GetPopularMovies getPopularMoviesUseCase,
                    GetTopRatedMovies getTopRatedMoviesUseCase,
                    MovieModelDataMapper movieModelDataMapper) {
        this.getPopularMoviesUseCase = getPopularMoviesUseCase;
        this.getTopRatedMoviesUseCase = getTopRatedMoviesUseCase;
        this.movieModelDataMapper = movieModelDataMapper;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadPopularMovies();
    }

    public void onPopularOptionItemSelected() {
        if(moviesType == MoviesType.TOP_RATED){
            moviesType = MoviesType.POPULAR;
            loadPopularMovies();
        }
    }

    public void onTopRatedOptionItemSelected() {
        if(moviesType == MoviesType.POPULAR){
            moviesType = MoviesType.TOP_RATED;
            loadTopRatedMovies();
        }
    }

    public void onBottomContentReached() {
        switch (moviesType){
            case POPULAR:
                loadMorePopularMovies();
                break;
            case TOP_RATED:
                loadMoreTopRatedMovies();
                break;
        }
    }

    public void onMovieClicked(MovieModel movieModel) {
        getViewState().openDetailMovieView(movieModel.getId());
    }

    private void loadMoreTopRatedMovies() {
        if(currentPage < totalPages){
            DisposableObserver<MoviesData> loadMoreTopRatedMovies = createLoadMoreMoviesObserver();

            getTopRatedMoviesUseCase.execute(loadMoreTopRatedMovies,
                    new GetTopRatedMovies.RequestValue(API_KEY, LANGUAGE, currentPage + 1));
        }

    }

    private void loadMorePopularMovies() {
        DisposableObserver<MoviesData> loadMorePopularMovies = createLoadMoreMoviesObserver();

        getPopularMoviesUseCase.execute(loadMorePopularMovies,
                new GetPopularMovies.RequestValue(API_KEY, LANGUAGE, currentPage + 1));
    }

    private void loadPopularMovies() {
        getViewState().showLoading();

        DisposableObserver<MoviesData>  getPopularMoviesObserver = createMoviesObserver();

        getPopularMoviesUseCase.execute(getPopularMoviesObserver,
                new GetPopularMovies.RequestValue(API_KEY, LANGUAGE, null));
    }

    private void loadTopRatedMovies(){
        getViewState().showLoading();

        DisposableObserver<MoviesData>  getTopRatedMoviesObserver = createMoviesObserver();

        getTopRatedMoviesUseCase.execute(getTopRatedMoviesObserver,
                new GetTopRatedMovies.RequestValue(API_KEY, LANGUAGE, null));
    }

    private DisposableObserver<MoviesData> createMoviesObserver(){
        return new DisposableObserver<MoviesData>() {
            @Override
            public void onNext(MoviesData moviesData) {
                initPaginationFields(moviesData);
                showMoviesListInView(moviesData.getMovies());
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showError(e);
            }

            @Override
            public void onComplete() {
                getViewState().showContent();
            }
        };
    }

    private DisposableObserver<MoviesData> createLoadMoreMoviesObserver(){
        return new DisposableObserver<MoviesData>() {
            @Override
            public void onNext(MoviesData moviesData) {
                initPaginationFields(moviesData);
                addMoviesListInView(moviesData.getMovies());
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showLoadMoreError();
            }

            @Override
            public void onComplete() {
                getViewState().showContent();
            }
        };
    }

    private void addMoviesListInView(List<Movie> movies){
        List<MovieModel> movieModelList = movieModelDataMapper.transform(movies);

        getViewState().addData(movieModelList);
    }

    private void showMoviesListInView(List<Movie> movies) {
        List<MovieModel> movieModelList = movieModelDataMapper.transform(movies);
        getViewState().setData(movieModelList);
    }

    private void initPaginationFields(@NonNull MoviesData moviesData) {
        this.currentPage = moviesData.getPage();
        this.totalPages = moviesData.getTotalPages();
    }



}
