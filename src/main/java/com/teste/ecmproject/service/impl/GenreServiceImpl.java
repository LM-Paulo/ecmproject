package com.teste.ecmproject.service.impl;

import com.teste.ecmproject.api.dto.GenreDto;
import com.teste.ecmproject.model.entity.GenreEntity;
import com.teste.ecmproject.model.repository.GenreRepository;
import com.teste.ecmproject.model.repository.MovieRepository;
import com.teste.ecmproject.service.GenreService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    public void createGenre(GenreDto dto) {
        GenreEntity genre = new GenreEntity();
        genre.SetEntity(dto);
        genreRepository.save(genre);

    }

    @Override
    public void update(UUID id, String genreName, Object newValue) {
        GenreEntity entity = this.genreRepository.findById(id).orElseThrow(() -> new NoResultException("Entity not found with ID: " + id));
        BeanUtils.copyProperties(newValue,entity,genreName);
        this.genreRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        if (id == null){
            throw new IllegalArgumentException("Genre id cant be null.");
        }
        this.genreRepository.deleteById(id);

    }

    @Override
    public Page<GenreEntity> listAllGenre(Sort.Direction direction, String properties, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction, properties));
        return genreRepository.findAll(pageable);
    }
}
