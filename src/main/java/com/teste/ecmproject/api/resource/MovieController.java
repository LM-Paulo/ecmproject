package com.teste.ecmproject.api.resource;

import com.teste.ecmproject.api.dto.MovieDto;
import com.teste.ecmproject.api.exception.BusinessException;
import com.teste.ecmproject.model.entity.MovieEntity;
import com.teste.ecmproject.service.impl.MovieServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/movie")
public class MovieController {

    private final MovieServiceImpl movieService;
    @Autowired
    public MovieController(MovieServiceImpl movieService){
        this.movieService = movieService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createMovie(@RequestBody MovieDto dto) throws BusinessException {
        movieService.createMovie(dto);
        return ResponseEntity.ok("Successfully created movie");
    }

    @PutMapping
    @Transactional
    public void updateMovie(@RequestBody MovieDto dto) throws BusinessException {
        movieService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable UUID id) throws BusinessException {
        movieService.delete(id);
    }

    @GetMapping("/search")
    public List<MovieEntity> findMoviesByName(@RequestParam("name") String name) {
        return movieService.findMoviesByName(name);
    }

    @GetMapping("/searchByGenre")
    public List<MovieEntity> findMoviesByGenre(@RequestParam("genre") String genre) {
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
