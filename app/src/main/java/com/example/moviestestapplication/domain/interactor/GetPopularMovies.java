package com.example.moviestestapplication.domain.interactor;

import android.arch.lifecycle.LiveData;

import com.example.moviestestapplication.domain.MoviesData;
import com.example.moviestestapplication.domain.Resource;
import com.example.moviestestapplication.domain.repository.MoviesDataRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetPopularMovies extends LDUseCase<Resource<MoviesData>,GetPopularMovies.RequestValue> {

    private final MoviesDataRepository repository;

    @Inject
    GetPopularMovies(MoviesDataRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    LiveData<Resource<MoviesData>> buildUseCaseObservable(RequestValue requestValue) {
        return repository.getPopular(requestValue.getApiKey(), requestValue.getLanguage(), requestValue.getPage());
    }

    public static class RequestValue {
        private final String apiKey;
        private final String language;
        private final Integer page;

        public RequestValue(String apiKey, String language, Integer page) {
            this.apiKey = apiKey;
            this.language = language;
            this.page = page;
        }

        String getApiKey() {
            return apiKey;
        }

        String getLanguage() {
            return language;
        }

        Integer getPage() {
            return page;
        }
    }
}
