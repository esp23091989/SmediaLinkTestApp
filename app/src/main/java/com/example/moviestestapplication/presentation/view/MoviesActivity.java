package com.example.moviestestapplication.presentation.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.moviestestapplication.R;
import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.domain.Resource;
import com.example.moviestestapplication.presentation.delegate.LCEDelegate;
import com.example.moviestestapplication.presentation.view.adapters.MoviesAdapter;
import com.example.moviestestapplication.presentation.viewmodel.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjection;

public class MoviesActivity extends AppCompatActivity implements MoviesView {

    private RecyclerView rvMovies;
    private Button refresh;

    @Inject
    Provider<LCEDelegate> lceDelegate;

    @Inject
    public ViewModelProvider.Factory viewModelsFactory;

    private MoviesViewModel moviesViewModel;

    private MoviesAdapter adapter;
    private GridLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        AndroidInjection.inject(this);

        this.rvMovies = findViewById(R.id.contentView);
        this.refresh = findViewById(R.id.btnRefresh);
        initRecyclerView();

        moviesViewModel = ViewModelProviders.of(this, viewModelsFactory).get(MoviesViewModel.class);
        moviesViewModel.getMovies().observe(this, this::render);

        refresh.setOnClickListener(v -> moviesViewModel.getEvents().setValue("refresh"));


    }

    private void render(Resource<List<Movie>> moviesDataResource) {
        switch (moviesDataResource.getStatus()){
            case LOADING:
                showLoading();
                break;
            case ERROR:
                showError(moviesDataResource.getMessage());
                break;
            case SUCCESS:
                setData(moviesDataResource.getData());
                showContent();
                break;
        }
    }

    private void initRecyclerView() {
        layoutManager = new GridLayoutManager(this,2);

        initAdapter();
        rvMovies.setAdapter(adapter);
        rvMovies.setLayoutManager(layoutManager);
    }

    private void initAdapter(){
        adapter = new MoviesAdapter(this,new ArrayList<>());
    }



    @Override
    public void setData(List<Movie> data) {
        adapter.setMovies(data);
    }

    @Override
    public void showError(Throwable e) {
        lceDelegate.get().showError(e);
    }
//

    private void showError(String errorMessage){
        lceDelegate.get().showError(errorMessage);
    }

    @Override
    public void showLoading() {
        lceDelegate.get().showLoading();
    }
//
    @Override
    public void showContent() {
        lceDelegate.get().showContent();
    }

}
