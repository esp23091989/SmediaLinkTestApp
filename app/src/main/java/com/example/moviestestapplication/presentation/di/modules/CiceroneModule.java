package com.example.moviestestapplication.presentation.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created by esp on 07.05.2018.
 */

@Module
public class CiceroneModule {

    @Singleton
    @Provides
    Cicerone<Router> provideCicerone(){
        return Cicerone.create();
    }

    @Singleton
    @Provides
    NavigatorHolder provideNavigatorHolder(Cicerone<Router> cicerone){
        return cicerone.getNavigatorHolder();
    }

    @Singleton
    @Provides
    Router provideRouter(Cicerone<Router> cicerone) {
        return cicerone.getRouter();
    }
}
