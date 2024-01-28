package com.teste.ecmproject.api.resource;

import com.teste.ecmproject.api.dto.GenreDto;
import com.teste.ecmproject.api.exception.BusinessException;
import com.teste.ecmproject.model.entity.GenreEntity;
import com.teste.ecmproject.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public void createGenre(@RequestBody GenreDto genreDto) throws BusinessException {
        genreService.createGenre(genreDto);
    }

    @PutMapping("/{id}")
    public void updateGenre(@PathVariable UUID id, @RequestParam String genreName, @RequestParam Object newValue) throws BusinessException {
        genreService.update(id, genreName, newValue);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable UUID id) throws BusinessException {
        genreService.delete(id);
    }

    @GetMapping
    public Page<GenreEntity> listAllGenres(
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "properties", defaultValue = "name") String properties,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        return genreService.listAllGenre(sortDirection, properties, page, size);
    }
}
