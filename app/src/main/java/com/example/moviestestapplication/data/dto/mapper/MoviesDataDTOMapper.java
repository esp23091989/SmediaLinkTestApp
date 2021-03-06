package com.example.moviestestapplication.data.dto.mapper;

import com.example.moviestestapplication.data.dto.MovieDTO;
import com.example.moviestestapplication.data.dto.MoviesDataDTO;
import com.example.moviestestapplication.data.exception.MoviesDataNotFoundException;
import com.example.moviestestapplication.domain.Movie;
import com.example.moviestestapplication.domain.MoviesData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class MoviesDataDTOMapper {

    private MovieDTOMapper movieDTOMapper;

    @Inject
    MoviesDataDTOMapper(MovieDTOMapper movieDTOMapper) {
        this.movieDTOMapper = movieDTOMapper;
    }

    public MoviesData transform(MoviesDataDTO moviesDataDTO){
        if(moviesDataDTO == null){
            throw new MoviesDataNotFoundException();
        }

        MoviesData moviesData;
        moviesData = new MoviesData();

        moviesData.setPage(moviesDataDTO.getPage());
        moviesData.setTotalPages(moviesDataDTO.getTotalPages());
        moviesData.setMovies(transformMoviesDTO(moviesDataDTO.getMovies()));

        return moviesData;

    }

    private List<Movie> transformMoviesDTO(List<MovieDTO> moviesDTO){
        if(moviesDTO == null)
            return Collections.emptyList();

        List<Movie> movies = new ArrayList<>();
        for(MovieDTO movieDTO : moviesDTO){
            Movie movie = movieDTOMapper.transform(movieDTO);
                movies.add(movie);
        }
        return movies;
    }
}
