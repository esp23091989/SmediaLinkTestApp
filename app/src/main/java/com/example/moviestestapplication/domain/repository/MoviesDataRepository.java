package com.example.moviestestapplication.domain.repository;

import com.example.moviestestapplication.domain.MoviesData;
import io.reactivex.Observable;

public interface MoviesDataRepository {

    //todo Здесь явно нарушается OCP(open-closed principle)
    //просто передавай тип мувис, который тебе нужен, чтобы избежать дублирования методов
    Observable<MoviesData> getPopular(String apiKey, String language, Integer page);

    Observable<MoviesData> getTopRated(String apiKey, String language, Integer page);

}
