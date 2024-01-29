package com.teste.ecmproject.service.impl;

import com.teste.ecmproject.api.dto.MovieDto;
import com.teste.ecmproject.api.exception.BusinessException;
import com.teste.ecmproject.model.entity.GenreEntity;
import com.teste.ecmproject.model.entity.MovieEntity;
import com.teste.ecmproject.model.repository.MovieRepository;
import com.teste.ecmproject.service.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }


    @Override
    public void createMovie(MovieDto dto) throws BusinessException {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setEntity(dto);
        validateMovieCode(dto.getCode());
        movieRepository.save(movieEntity);

    }

    @Override
    @Transactional
    public void update(String id,MovieDto dto) throws BusinessException {
        Optional<MovieEntity> entity = movieRepository.findById(id);
        validateMovieCode(dto.getCode());

        if (entity.isPresent()) {
            MovieEntity movie = new MovieEntity();
            movie.setEntity(dto);

            movieRepository.updateMovie(
                    id,
                    dto.getCode(),
                    dto.getName(),
                    dto.getSynopsis(),
                    dto.getGenres(),
                    dto.getYearLaunch(),
                    dto.getParentalRating(),
                    dto.getDuration()
            );
        } else {
            throw new BusinessException(id + " DOES NOT EXIST");
        }
    }


    @Override
    public void delete(String id) throws BusinessException {
        Optional<MovieEntity> genre = Optional.ofNullable(movieRepository.findById(id).orElseThrow(() -> new BusinessException("Genre with ID '" + id + "' not found.")));
        this.movieRepository.deleteById(genre.get().getId());

    }

    @Override
    public List<MovieEntity> findMoviesByName(String name) throws BusinessException {
        if (name == null){
            throw new BusinessException( name + "DOES NOT EXIST");
        }
        return movieRepository.findByAvailabilityIsTrueAndNameContainingIgnoreCase(name);
    }

    @Override
    public List<MovieEntity> findMovieByGenre(String genre) throws BusinessException {
        if (genre == null){
            throw new BusinessException( genre + "DOES NOT EXIST");
        }
        return movieRepository.findByAvailabilityIsTrueAndGenresNameIgnoreCase(genre);
    }

    @Override
    public Page<MovieEntity> listAllMovies(Sort.Direction direction, String properties, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction, properties));
        return movieRepository.findAll(pageable);
    }

    @Override
    public List<MovieEntity> findAllUnavailableMovies() {
        return movieRepository.findByAvailabilityIsFalse();
    }

    private void validateMovieCode(String code) throws BusinessException {
        if (code == null || !code.matches("[A-Z]{3}-\\d{3}(?!000)")) {
            throw new BusinessException("Invalid movie code format. It should be three uppercase letters, a hyphen, and three digits (excluding 000 at the end).");
        }

        if (code.endsWith("000")) {
            throw new BusinessException("Invalid movie code. It cannot end with 000.");
        }
    }
}
