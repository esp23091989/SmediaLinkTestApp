package com.example.moviestestapplication.presentation.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.moviestestapplication.R;
import com.example.moviestestapplication.app.TheApp;
import com.example.moviestestapplication.presentation.di.components.DaggerMoviesActivityComponent;
import com.example.moviestestapplication.presentation.di.components.MoviesActivityComponent;
import com.example.moviestestapplication.presentation.model.MovieModel;
import com.example.moviestestapplication.presentation.presenter.MoviesPresenter;
import com.example.moviestestapplication.presentation.view.adapters.MoviesAdapter;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.AbsLceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends MvpLceViewStateActivity<RecyclerView,List<MovieModel>,MoviesView,MoviesPresenter>
        implements MoviesView{

    @BindView(R.id.contentView) RecyclerView rvMovies;

    private MoviesActivityComponent component;
    private MoviesAdapter adapter;
    private GridLayoutManager layoutManager;
    private boolean loading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buildGraph();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void buildGraph(){
        component = DaggerMoviesActivityComponent.builder()
                .applicationComponent(((TheApp) getApplication()).getComponent())
                .build();
        component.inject(this);
    }

    private void initRecyclerView() {
        layoutManager = new GridLayoutManager(this,2);

        initAdapter();
        rvMovies.setAdapter(adapter);
        rvMovies.setLayoutManager(layoutManager);
        rvMovies.addOnScrollListener(scrollListener);
    }

    private void initAdapter(){
        adapter = new MoviesAdapter(this,new ArrayList<>());
        adapter.setOnItemClickListener(onItemClickListener);
    }

    @NonNull
    @Override
    public MoviesPresenter createPresenter() {
        return component.getPresenter();
    }

    @Override
    public List<MovieModel> getData() {
        return adapter.getMovies();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @NonNull
    @Override
    public LceViewState<List<MovieModel>, MoviesView> createViewState() {
        return new AbsLceViewState<List<MovieModel>, MoviesView>(){};
    }

    @Override
    public void setData(List<MovieModel> data) {
        adapter.setMovies(data);
    }

    @Override
    public void addData(List<MovieModel> data) {
        adapter.addMovies(data);
        loading = true;
    }

    @Override
    public void showLoadMoreError() {
        Snackbar.make(rvMovies, R.string.snackbar_text, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.snackbar_try_again, v -> presenter.onBottomContentReached())
                .show();
    }

    @Override
    public void openDetailMovieView(Integer id) {
        Intent intent = new Intent(this, DetailsMovieActivity.class);
        intent.putExtra(DetailsMovieActivity.EXTRA_MOVIE_ID,id);

        startActivity(intent);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popular:
                presenter.onPopularOptionItemSelected();
                break;
            case R.id.topRated:
                presenter.onTopRatedOptionItemSelected();
        }

        return super.onOptionsItemSelected(item);
    }

    private final MoviesAdapter.OnItemClickListener onItemClickListener = new MoviesAdapter.OnItemClickListener() {
        @Override
        public void onMovieItemClicked(MovieModel movieModel) {
            presenter.onMovieClicked(movieModel);
        }
    };

    private final RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if(dy > 0) //check for scroll down
            {
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                if (loading && (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                {
                    loading = false;
                    presenter.onBottomContentReached();
                }
            }
        }
    };
}
