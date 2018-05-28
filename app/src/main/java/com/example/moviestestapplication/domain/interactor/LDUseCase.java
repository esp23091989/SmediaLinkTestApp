package com.example.moviestestapplication.domain.interactor;

import android.arch.lifecycle.LiveData;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

public abstract class LDUseCase<T, Params> {

    LDUseCase() {
    }

    abstract LiveData<T> buildUseCaseObservable(Params params);

    public LiveData<T> getData(Params params) {
        return buildUseCaseObservable(params);
    }

}

