package com.example.moviestestapplication.presentation.di.modules

import com.example.moviestestapplication.presentation.view.MoviesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesInjectionModule {
    @ContributesAndroidInjector(modules = [MoviesActivityModule::class])
    abstract fun contribureMoviesActivity(): MoviesActivity
}
