package com.teste.ecmproject.api.resource;

import com.teste.ecmproject.api.dto.MovieDto;
import com.teste.ecmproject.api.exception.BusinessException;
import com.teste.ecmproject.model.entity.MovieEntity;
import com.teste.ecmproject.service.impl.MovieServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/movie")
public class MovieController {

    private final MovieServiceImpl movieService;
    @Autowired
    public MovieController(MovieServiceImpl movieService){
        this.movieService = movieService;
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createMovie(@RequestBody @Valid MovieDto dto) throws BusinessException {
        movieService.createMovie(dto);
        return ResponseEntity.ok("Successfully created movie");
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> updateMovie(@PathVariable String id,@RequestBody @Valid MovieDto dto) throws BusinessException {
        movieService.update(id,dto);
        return ResponseEntity.ok("Successfully created movie");
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable String id) throws BusinessException {
        movieService.delete(id);
    }

    @GetMapping("/search")
    public List<MovieEntity> findMoviesByName(@RequestParam("name") String name) throws BusinessException {
        return movieService.findMoviesByName(name);
    }

    @GetMapping("/searchByGenre")
    public List<MovieEntity> findMoviesByGenre(@RequestParam("genre") String genre) throws BusinessException {
        return movieService.findMovieByGenre(genre);
    }

    @GetMapping("/allMovies")
    public Page<MovieEntity> listAllMovies(
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "properties", defaultValue = "name") String properties,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        return movieService.listAllMovies(sortDirection, properties, page, size);
    }

    @GetMapping("/unavailable")
    public List<MovieEntity> findAllUnavailableMovies() {
        return movieService.findAllUnavailableMovies();
    }






}
