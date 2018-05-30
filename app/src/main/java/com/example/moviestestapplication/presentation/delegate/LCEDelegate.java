package com.example.moviestestapplication.presentation.delegate;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.moviestestapplication.R;
import com.example.moviestestapplication.data.exception.MovieNotFoundException;
import com.example.moviestestapplication.data.exception.MoviesDataNotFoundException;

import org.w3c.dom.Text;

import javax.inject.Inject;

import retrofit2.HttpException;

/**
 * Created by esp on 06.05.2018.
 */

public class LCEDelegate {

    private Context context;
    private LCEState lceState = LCEState.SHOW_LOADING_STATE;
    private TextView errorView;
    private View loadingView;
    private View contentView;

    @Inject
    public LCEDelegate(Context context, View rootView) {
        this.context = context;
        this.errorView = (TextView) rootView.findViewById(R.id.errorView);
        this.loadingView = rootView.findViewById(R.id.loadingView);
        this.contentView = rootView.findViewById(R.id.contentView);
    }

    public void showError(Throwable throwable){
        String message = getErrorMessage(throwable);
        errorView.setText(message);
        lceState = LCEState.SHOW_ERROR_STATE;
        updateViewState();
    }

    public void showContent(){
        lceState = LCEState.SHOW_CONTENT_STATE;
        updateViewState();
    }

    public void showLoading(){
        lceState = LCEState.SHOW_LOADING_STATE;
        updateViewState();
    }

    private void updateViewState(){
        switch (lceState){
            case SHOW_ERROR_STATE:
                loadingView.setVisibility(View.GONE);
                contentView.setVisibility(View.GONE);
                errorView.setVisibility(View.VISIBLE);
                break;
            case SHOW_CONTENT_STATE:
                loadingView.setVisibility(View.GONE);
                contentView.setVisibility(View.VISIBLE);
                errorView.setVisibility(View.GONE);
                break;
            case SHOW_LOADING_STATE:
                loadingView.setVisibility(View.VISIBLE);
                contentView.setVisibility(View.GONE);
                errorView.setVisibility(View.GONE);
                break;
        }
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
