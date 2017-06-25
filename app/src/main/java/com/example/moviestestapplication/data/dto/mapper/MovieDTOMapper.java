package com.example.moviestestapplication.data.dto.mapper;

import com.example.moviestestapplication.data.dto.MovieDTO;
import com.example.moviestestapplication.data.exception.MovieNotFoundException;
import com.example.moviestestapplication.domain.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by Юленька on 24.06.2017.
 */

public class MovieDTOMapper {

    @Inject
    MovieDTOMapper() {
    }

    public Movie transform(MovieDTO movieDTO){
        if(movieDTO == null){
            throw new MovieNotFoundException();
        }

        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setOverview(movieDTO.getOverview());
        movie.setPosterPath(movieDTO.getPosterPath());
        movie.setVoteAverage(movieDTO.getVoteAverage());
        movie.setReleaseDate(movieDTO.getReleaseDate());

        return movie;
    }
}
