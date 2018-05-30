package com.example.moviestestapplication.app;

import android.app.Application;
import com.example.moviestestapplication.presentation.di.components.ApplicationComponent;
import com.example.moviestestapplication.presentation.di.components.DaggerApplicationComponent;
import com.example.moviestestapplication.presentation.di.modules.ApplicationModule;

public class TheApp extends Application{
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraph();
    }

    private void buildGraph(){
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();

        component.inject(this);
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
