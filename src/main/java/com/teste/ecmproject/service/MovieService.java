package com.teste.ecmproject.service;

import com.teste.ecmproject.api.dto.MovieDto;
import com.teste.ecmproject.api.exception.BusinessException;
import com.teste.ecmproject.model.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface MovieService {

    void createMovie(MovieDto movieDto) throws BusinessException;

    void update(MovieDto dto) throws BusinessException;

    void delete(UUID id) throws BusinessException;

    List<MovieEntity> findMoviesByName(String name);

    List<MovieEntity> findMovieByGenre(String genre);

    Page<MovieEntity> listAllMovies(Sort.Direction direction, String properties, Integer page, Integer size);

    List<MovieEntity> findAllUnavailableMovies();





}
