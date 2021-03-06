package com.example.moviestestapplication.domain.interactor;

import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.domain.repository.MovieRepository;
import com.example.moviestestapplication.presentation.model.MovieModel;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Юленька on 24.06.2017.
 */
public class GetMovie extends UseCase<Movie,GetMovie.RequestValue>{

    private final MovieRepository repository;

    @Inject
    GetMovie(@Named("ThreadScheduler") Scheduler threadScheduler,
             @Named("PostScheduler") Scheduler postScheduler,
             MovieRepository repository) {
        super(threadScheduler, postScheduler);
        this.repository = repository;
    }

    @Override
    Observable<Movie> buildUseCaseObservable(RequestValue requestValue) {
        return repository.getMovieById(requestValue.getApiKey(), requestValue.getLanguage(), requestValue.getMovieId());
    }

    public static class RequestValue {
        private final String apiKey;
        private final String language;
        private final int movieId;

        public RequestValue(String apiKey, String language, int movieId) {
            this.apiKey = apiKey;
            this.language = language;
            this.movieId = movieId;
        }

        String getApiKey() {
            return apiKey;
        }

        String getLanguage() {
            return language;
        }

        public int getMovieId() {
            return movieId;
        }
    }
}
