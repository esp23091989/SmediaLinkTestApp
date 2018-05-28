package com.example.moviestestapplication.presentation.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.moviestestapplication.presentation.viewmodel.MoviesViewModel;
import com.example.moviestestapplication.presentation.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ClassKey(MoviesViewModel.class)
    public abstract ViewModel provideMoviesViewModel(MoviesViewModel viewModel);

    @Binds
    public abstract ViewModelProvider.Factory provideFactory(ViewModelFactory factory);
}
