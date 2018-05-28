package com.example.moviestestapplication.data.api;

import android.arch.lifecycle.LiveData;

import com.example.moviestestapplication.data.dto.MovieDTO;
import com.example.moviestestapplication.data.dto.MoviesDataDTO;
import com.example.moviestestapplication.presentation.model.MovieModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular")
    LiveData<ApiResponse<MoviesDataDTO>> getPopularMovies(@Query("api_key") String apiKey,
                                             @Query("language") String language,
                                             @Query("page") Integer page);

    @GET("movie/top_rated")
    Observable<MoviesDataDTO> getTopRatedMovies(@Query("api_key") String apiKey,
                                                @Query("language") String language,
                                                @Query("page") Integer page);

    @GET("movie/{movieId}")
    Observable<MovieDTO> getMovie(@Path("movieId") int movieId,
                                  @Query("api_key") String apiKey,
                                  @Query("language") String language
                                  );
}
