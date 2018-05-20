package com.example.moviestestapplication.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.moviestestapplication.R;
import com.example.moviestestapplication.app.TheApp;
import com.example.moviestestapplication.navigation.Screens;
import com.example.moviestestapplication.presentation.delegate.LCEDelegate;
import com.example.moviestestapplication.presentation.di.components.DaggerMoviesActivityComponent;
import com.example.moviestestapplication.presentation.di.components.HasMoviesAdapterDepenedencies;
import com.example.moviestestapplication.presentation.di.components.MoviesActivityComponent;
import com.example.moviestestapplication.presentation.di.modules.MoviesActivityModule;
import com.example.moviestestapplication.presentation.model.MovieModel;
import com.example.moviestestapplication.presentation.presenter.DetailsMoviePresenter;
import com.example.moviestestapplication.presentation.presenter.Movies1Presenter;
import com.example.moviestestapplication.presentation.presenter.MoviesPresenter;
import com.example.moviestestapplication.presentation.view.adapters.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportAppNavigator;

public class MoviesActivity extends MvpAppCompatActivity implements MoviesView, HasComponent<HasMoviesAdapterDepenedencies>{

    @BindView(R.id.contentView)
    RecyclerView rvMovies;

    @InjectPresenter
    @Inject
    MoviesPresenter presenter;

    @InjectPresenter(type = PresenterType.GLOBAL)
    @Inject
    Movies1Presenter presenter1;

    @Inject
    Provider<LCEDelegate> lceDelegate;

    @Inject
    NavigatorHolder navigatorHolder;

    SupportAppNavigator navigator = new SupportAppNavigator(this, 0) {
        @Override
        protected Intent createActivityIntent(Context context, String screenKey, Object data) {
            switch (screenKey){
                case Screens.DETAILS_MOVIE_SCREEN:
                    Intent intent = new Intent(context, DetailsMovieActivity.class);
                    intent.putExtra(DetailsMovieActivity.EXTRA_MOVIE_ID,(int) data);
                    return intent;
            }
            return null;
        }

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            return null;
        }
    };

    private MoviesActivityComponent component;
    private MoviesAdapter adapter;
    private GridLayoutManager layoutManager;
    private boolean loading = true;


    @ProvidePresenter
    public MoviesPresenter providePresenter(){
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buildGraph();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }

    private void buildGraph(){
        component = DaggerMoviesActivityComponent.builder()
                .moviesActivityModule(new MoviesActivityModule(this))
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
        adapter = new MoviesAdapter(getMvpDelegate(), this,new ArrayList<>());
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

    @Override
    public void showError(Throwable e) {
        lceDelegate.get().showError(e);
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
    public HasMoviesAdapterDepenedencies getComponent() {
        return component;
    }
}
