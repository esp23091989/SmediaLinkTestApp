package com.example.moviestestapplication.data.api;

import android.arch.lifecycle.LiveData;

import com.example.moviestestapplication.data.dto.MoviesDataDTO;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular")
    LiveData<ApiResponse<MoviesDataDTO>> getMovies(@Query("api_key") String apiKey);
}
