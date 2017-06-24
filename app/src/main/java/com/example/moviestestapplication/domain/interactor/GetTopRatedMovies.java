package com.example.moviestestapplication.domain.interactor;

import com.example.moviestestapplication.domain.MoviesData;
import com.example.moviestestapplication.domain.repository.MoviesDataRepository;
import javax.inject.Inject;
import javax.inject.Named;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetTopRatedMovies extends UseCase<MoviesData,GetTopRatedMovies.RequestValue>{

    private final MoviesDataRepository repository;

    @Inject
    public GetTopRatedMovies(@Named("ThreadScheduler") Scheduler threadScheduler,
                             @Named("PostScheduler") Scheduler postScheduler,
                             MoviesDataRepository repository) {
        super(threadScheduler, postScheduler);
        this.repository = repository;
    }

    @Override
    Observable<MoviesData> buildUseCaseObservable(RequestValue requestValue) {
        return repository.getTopRated(requestValue.getApiKey(), requestValue.getLanguage(), requestValue.getPage());
    }

    public static class RequestValue {
        private String apiKey;
        private String language;
        private Integer page;

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
