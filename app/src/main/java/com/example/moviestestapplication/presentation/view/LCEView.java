package com.example.moviestestapplication.presentation.view;


interface LCEView {

    void showLoading();
    void showContent();
    void showError(Throwable e);
}
