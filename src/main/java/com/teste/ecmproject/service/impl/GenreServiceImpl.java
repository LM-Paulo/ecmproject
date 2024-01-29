package com.teste.ecmproject.service.impl;

import com.teste.ecmproject.api.dto.GenreDto;
import com.teste.ecmproject.api.exception.BusinessException;
import com.teste.ecmproject.model.entity.GenreEntity;
import com.teste.ecmproject.model.repository.GenreRepository;
import com.teste.ecmproject.service.GenreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

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
            throw new BusinessException("Genre with name '" + dto.getName() + " already exists.");
        }
        GenreEntity genre = new GenreEntity();
        genre.SetEntity(dto);
        genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void update(String id,GenreDto dto) throws BusinessException {
        Optional<GenreEntity> genre = genreRepository.findById(id);
        if (genre.isPresent()){
            GenreEntity entity = new GenreEntity();
            entity.SetEntity(dto);
            genreRepository.updateGenre(id,dto.getName());
        }else {
            throw new BusinessException( id + "DOES NOT EXIST");
        }
    }

    @Override
    public void delete(String id) throws BusinessException {
        Optional<GenreEntity> genre = Optional.ofNullable(genreRepository.findById(id).orElseThrow(() -> new BusinessException("Genre with ID '" + id + "' not found.")));
        this.genreRepository.deleteById(genre.get().getId());

    }

    @Override
    public Page<GenreEntity> listAllGenre(Sort.Direction direction, String properties, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction, properties));
        return genreRepository.findAll(pageable);
    }
}
