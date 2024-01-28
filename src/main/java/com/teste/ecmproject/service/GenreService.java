package com.teste.ecmproject.service;

import com.teste.ecmproject.api.dto.GenreDto;
import com.teste.ecmproject.api.exception.BusinessException;
import com.teste.ecmproject.model.entity.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface GenreService {

    void createGenre(GenreDto dto) throws BusinessException;

    void update(UUID id, String genreName, Object newValue) throws BusinessException;

    void delete(UUID id) throws BusinessException;

    Page<GenreEntity> listAllGenre(Sort.Direction direction, String properties, Integer page, Integer size);
}
