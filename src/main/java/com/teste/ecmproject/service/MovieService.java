package com.teste.ecmproject.service;

import com.teste.ecmproject.api.dto.MovieDto;
import com.teste.ecmproject.api.exception.BusinessException;
import com.teste.ecmproject.model.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface MovieService {

    void createMovie(MovieDto movieDto) throws BusinessException;

    void update(String id,MovieDto dto) throws BusinessException;

    void delete(String id) throws BusinessException;

    List<MovieEntity> findMoviesByName(String name) throws BusinessException;

    List<MovieEntity> findMovieByGenre(String genre) throws BusinessException;

    Page<MovieEntity> listAllMovies(Sort.Direction direction, String properties, Integer page, Integer size);

    List<MovieEntity> findAllUnavailableMovies();

}
