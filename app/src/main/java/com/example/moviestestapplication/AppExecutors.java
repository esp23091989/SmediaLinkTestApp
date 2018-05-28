package com.example.moviestestapplication;


import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppExecutors {

    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;

    @Inject
    public AppExecutors() {
        this.diskIO = Executors.newSingleThreadExecutor();
        this.networkIO = Executors.newFixedThreadPool(3);
        this.mainThread = new Executor() {
            private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
            @Override
            public void execute(@NonNull Runnable runnable) {
                mainThreadHandler.post(runnable);
            }
        };
    }
}
