package com.example.moviestestapplication.presentation.di.modules;

import android.view.View;

import com.example.moviestestapplication.R;
import com.example.moviestestapplication.presentation.di.scope.PerActivity;
import com.example.moviestestapplication.presentation.view.DetailsMovieActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailsMovieActivityModule {

    private DetailsMovieActivity activity;

    public DetailsMovieActivityModule(DetailsMovieActivity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    Integer provideMovieId(){
        return activity.obtainMovieId();
    }

    @PerActivity
    @Provides
    View providesRootView(){
        return activity.findViewById(R.id.rootView);
    }

}
