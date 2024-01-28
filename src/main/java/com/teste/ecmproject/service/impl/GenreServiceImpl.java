package com.teste.ecmproject.service.impl;

import com.teste.ecmproject.api.dto.GenreDto;
import com.teste.ecmproject.api.exception.BusinessException;
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
    public void createGenre(GenreDto dto) throws BusinessException {
        if (genreRepository.existsByName(dto.getName())){
            throw new BusinessException("Genre with name '" + dto.getName() + "' already exists.");
        }
        GenreEntity genre = new GenreEntity();
        genre.SetEntity(dto);
        genreRepository.save(genre);
    }

    @Override
    public void update(UUID id, String genreName, Object newValue) throws BusinessException {
        GenreEntity entity = this.genreRepository.findById(id).orElseThrow(() -> new BusinessException("Genre with ID '" + id + "' not found."));
        BeanUtils.copyProperties(newValue,entity,genreName);
        this.genreRepository.save(entity);
    }

    @Override
    public void delete(UUID id) throws BusinessException {
        if (id == null){
            throw new BusinessException("Genre id cannot be null.");
        }
        GenreEntity genre = genreRepository.findById(id).orElseThrow(() -> new BusinessException("Genre with ID '" + id + "' not found."));
        this.genreRepository.deleteById(genre.getId());

    }

    @Override
    public Page<GenreEntity> listAllGenre(Sort.Direction direction, String properties, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction, properties));
        return genreRepository.findAll(pageable);
    }
}
