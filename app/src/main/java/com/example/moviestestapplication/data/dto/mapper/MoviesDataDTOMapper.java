package com.example.moviestestapplication.data.dto.mapper;

import android.support.annotation.NonNull;

import com.example.moviestestapplication.data.dto.MovieDTO;
import com.example.moviestestapplication.data.dto.MoviesDataDTO;
import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.domain.MoviesData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class MoviesDataDTOMapper {

    private MovieDTOMapper movieDTOMapper;

    @Inject
    MoviesDataDTOMapper(MovieDTOMapper movieDTOMapper) {
        this.movieDTOMapper = movieDTOMapper;
    }

    public MoviesData transform(@NonNull MoviesDataDTO moviesDataDTO) throws ParseException {
        MoviesData moviesData;
        moviesData = new MoviesData();

        moviesData.setPage(moviesDataDTO.getPage());
        moviesData.setTotalPages(moviesDataDTO.getTotalPages());
        moviesData.setMovies(transformMoviesDTO(moviesDataDTO.getMovies()));

        return moviesData;

    }

    private List<Movie> transformMoviesDTO(List<MovieDTO> moviesDTO) throws ParseException {
        List<Movie> movies = new ArrayList<>();
        if(moviesDTO != null && !moviesDTO.isEmpty()){
            for(MovieDTO movieDTO : moviesDTO){
                Movie movie = movieDTOMapper.transform(movieDTO);
                if(movie != null)
                    movies.add(movie);
            }
        }
        return movies;
    }
}
