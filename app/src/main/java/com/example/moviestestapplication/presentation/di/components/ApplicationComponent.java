//package com.example.moviestestapplication.presentation.di.components;
//
//import android.content.Context;
//
//import com.example.moviestestapplication.app.TheApp;
//import com.example.moviestestapplication.data.api.ApiInterface;
//import com.example.moviestestapplication.data.repository.datasource.MoviesDataStore;
//import com.example.moviestestapplication.domain.repository.MovieRepository;
//import com.example.moviestestapplication.domain.repository.MoviesRepository;
//import com.example.moviestestapplication.presentation.di.modules.ApplicationModule;
//import com.example.moviestestapplication.presentation.di.modules.CiceroneModule;
//
//import javax.inject.Named;
//import javax.inject.Singleton;
//
//import dagger.Component;
//import io.reactivex.Scheduler;
//import ru.terrakok.cicerone.NavigatorHolder;
//import ru.terrakok.cicerone.Router;
//
//@Singleton
//@Component(modules = {ApplicationModule.class, CiceroneModule.class})
//public interface ApplicationComponent {
//
//    @Named("ThreadScheduler")
//    Scheduler getThreadScheduler();
//
//    @Named("PostScheduler")
//    Scheduler getPostScheduler();
//
//    MoviesRepository getMoviesDataRepository();
//
//    MovieRepository getMovieRepository();
//
//    @Named("RemoteMoviesDataStore")
//    MoviesDataStore getMoviesDataStore();
//
//    ApiInterface getApiInterface();
//
//    Context getContext();
//
//    Router getRouter();
//
//    NavigatorHolder getNavigatorHolder();
//
//    void inject(TheApp app);
//}
