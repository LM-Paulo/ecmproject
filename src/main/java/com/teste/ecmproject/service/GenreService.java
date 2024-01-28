package com.teste.ecmproject.service;

import com.teste.ecmproject.api.dto.GenreDto;
import com.teste.ecmproject.model.entity.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface GenreService {

    void createGenre(GenreDto dto);

    void update(UUID id, String genreName, Object newValue);

    void delete(UUID id);

    Page<GenreEntity> listAllGenre(Sort.Direction direction, String properties, Integer page, Integer size);
}
