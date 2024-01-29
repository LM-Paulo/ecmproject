package com.teste.ecmproject.service;

import com.teste.ecmproject.api.dto.GenreDto;
import com.teste.ecmproject.api.exception.BusinessException;
import com.teste.ecmproject.model.entity.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface GenreService {

    void createGenre(GenreDto dto) throws BusinessException;

    void update(String id,GenreDto dto) throws BusinessException;

    void delete(String id) throws BusinessException;

    Page<GenreEntity> listAllGenre(Sort.Direction direction, String properties, Integer page, Integer size);
}
