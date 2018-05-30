package com.example.moviestestapplication.presentation.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.moviestestapplication.R;
import com.example.moviestestapplication.app.TheApp;
import com.example.moviestestapplication.presentation.delegate.LCEDelegate;
import com.example.moviestestapplication.presentation.di.components.DaggerDetailsMovieActivityComponent;
import com.example.moviestestapplication.presentation.di.components.DetailsMovieActivityComponent;
import com.example.moviestestapplication.presentation.di.modules.DetailsMovieActivityModule;
import com.example.moviestestapplication.presentation.model.MovieModel;
import com.example.moviestestapplication.presentation.presenter.DetailsMoviePresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.HttpException;

public class DetailsMovieActivity extends MvpAppCompatActivity
        implements  DetailsMovieView{

    public static final String EXTRA_MOVIE_ID = "extra_movie_id";

    @BindView(R.id.ivPoster) ImageView ivPoster;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvVoteAverage) TextView tvVoteAverage;
    @BindView(R.id.tvDate) TextView tvDate;
    @BindView(R.id.tvOverview) TextView tvOverview;

    DetailsMovieActivityComponent component;

    @InjectPresenter
    @Inject
    DetailsMoviePresenter presenter;

    @Inject
    Provider<LCEDelegate> lceDelegate;

    @ProvidePresenter
    public DetailsMoviePresenter providePresenter(){
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buildGraph();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        ButterKnife.bind(this);
    }

    private void buildGraph() {
        component = DaggerDetailsMovieActivityComponent.builder()
                .detailsMovieActivityModule(new DetailsMovieActivityModule(this))
                .applicationComponent(((TheApp) getApplication()).getComponent())
                .build();

        component.inject(this);
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

    public Integer obtainMovieId() {
        return getIntent().getIntExtra(EXTRA_MOVIE_ID, 0);
    }

    @Override
    public void showLoading() {
        lceDelegate.get().showLoading();
    }

    @Override
    public void showContent() {
        lceDelegate.get().showContent();
    }

    @Override
    public void showError(Throwable e) {
        lceDelegate.get().showError(e);
    }
}
