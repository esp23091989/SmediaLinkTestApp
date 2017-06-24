package com.example.moviestestapplication.data.dto.mapper;

import com.example.moviestestapplication.data.dto.MovieDTO;
import com.example.moviestestapplication.domain.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by Юленька on 24.06.2017.
 */

public class MovieDTOMapper {
    private final SimpleDateFormat format;

    @Inject
    MovieDTOMapper() {
        format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    }

    public Movie transform(MovieDTO movieDTO) throws ParseException {
        Movie movie = null;
        if(movieDTO != null){
            movie = new Movie();
            movie.setId(movieDTO.getId());
            movie.setTitle(movieDTO.getTitle());
            movie.setOverview(movieDTO.getOverview());
            movie.setPosterPath(movieDTO.getPosterPath());
            movie.setVoteAverage(movieDTO.getVoteAverage());
            movie.setReleaseDate(format.parse(movieDTO.getReleaseDate()));
        }
        return movie;
    }
}
