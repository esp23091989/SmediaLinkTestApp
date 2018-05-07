package com.example.moviestestapplication.presentation.delegate;

import android.content.Context;

import com.example.moviestestapplication.R;
import com.example.moviestestapplication.data.exception.MovieNotFoundException;
import com.example.moviestestapplication.data.exception.MoviesDataNotFoundException;

import javax.inject.Inject;

import retrofit2.HttpException;

/**
 * Created by esp on 06.05.2018.
 */

public class ErrorDelegate {

    private Context context;

    @Inject
    public ErrorDelegate(Context context) {
        this.context = context;
    }

    public void showError(Throwable throwable){
        String message = getErrorMessage(throwable);
    }

    public String getErrorMessage(Throwable e) {
        if(e instanceof MovieNotFoundException || e instanceof MoviesDataNotFoundException) {
            return context.getString(R.string.error_movie_not_found);
        }else if(e instanceof HttpException) {
            return getHttpExceptionErrorMessage((HttpException)e);
        }
        return context.getString(R.string.error_default);
    }

    private String getHttpExceptionErrorMessage(HttpException httpException) {
        if(httpException.code() == 401){
            return context.getString(R.string.erro_unauthorized);
        }
        return context.getString(R.string.error_network);
    }
}
