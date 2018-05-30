package com.example.moviestestapplication.presentation.view;

import com.example.moviestestapplication.presentation.di.components.HasMoviesAdapterDepenedencies;
import com.example.moviestestapplication.presentation.view.adapters.MoviesAdapter;

/**
 * Created by esp on 07.05.2018.
 */

public interface HasComponent<Component> {

    Component getComponent();

}
