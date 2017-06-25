package com.example.moviestestapplication.presentation.view;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.moviestestapplication.R;
import com.example.moviestestapplication.app.TheApp;
import com.example.moviestestapplication.data.exception.MovieNotFoundException;
import com.example.moviestestapplication.presentation.di.components.DaggerDetailsMovieActivityComponent;
import com.example.moviestestapplication.presentation.di.components.DetailsMovieActivityComponent;
import com.example.moviestestapplication.presentation.model.MovieModel;
import com.example.moviestestapplication.presentation.presenter.DetailsMoviePresenter;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.AbsLceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.HttpException;

public class DetailsMovieActivity extends MvpLceViewStateActivity<RelativeLayout,MovieModel,DetailsMovieView,DetailsMoviePresenter>
        implements  DetailsMovieView{

    public static final String EXTRA_MOVIE_ID = "extra_movie_id";

    @BindView(R.id.ivPoster) ImageView ivPoster;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvVoteAverage) TextView tvVoteAverage;
    @BindView(R.id.tvDate) TextView tvDate;
    @BindView(R.id.tvOverview) TextView tvOverview;

    DetailsMovieActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buildGraph();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        ButterKnife.bind(this);
    }

    private void buildGraph() {
        component = DaggerDetailsMovieActivityComponent.builder()
                .applicationComponent(((TheApp) getApplication()).getComponent())
                .build();

        component.inject(this);
    }

    @NonNull
    @Override
    public DetailsMoviePresenter createPresenter() {
        return component.getPresenter();
    }

    @Override
    public MovieModel getData() {
        return presenter.getCurrentMovieModel();
    }

    @NonNull
    @Override
    public LceViewState<MovieModel, DetailsMovieView> createViewState() {
        return new AbsLceViewState<MovieModel, DetailsMovieView>(){};
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        if(e instanceof MovieNotFoundException) {
            return getString(R.string.error_movie_not_found);
        }else if(e instanceof HttpException) {
            return getHttpExceptionErrorMessage((HttpException)e);
        }
        return getString(R.string.error_default);
    }

    private String getHttpExceptionErrorMessage(HttpException httpException) {
        if(httpException.code() == 401){
            return getString(R.string.erro_unauthorized);
        }
        return getString(R.string.error_network);
    }
    @Override
    public void setData(MovieModel movieModel) {
        tvTitle.setText(movieModel.getTitle());
        tvVoteAverage.setText(String.valueOf(movieModel.getVoteAverage()));
        tvDate.setText(movieModel.getReleaseDate());
        tvOverview.setText(movieModel.getOverview());
        loadPoster(movieModel.getPosterPath());
    }

    private void loadPoster(String posterPath){
        String picassoBaseUrl = getString(R.string.picasso_base_url);
        Picasso.with(this).load(picassoBaseUrl + "/w185" + posterPath)
                .fit()
                .into(ivPoster);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.onStart(obtainMovieId());
    }

    private int obtainMovieId() {
        return getIntent().getIntExtra(EXTRA_MOVIE_ID, 0);
    }
}
