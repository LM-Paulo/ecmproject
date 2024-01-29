package com.teste.ecmproject.api.resource;

import com.teste.ecmproject.api.dto.GenreDto;
import com.teste.ecmproject.api.exception.BusinessException;
import com.teste.ecmproject.model.entity.GenreEntity;
import com.teste.ecmproject.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/createGenre")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createGenre(@RequestBody @Valid GenreDto genreDto) throws BusinessException {
        genreService.createGenre(genreDto);
        return ResponseEntity.ok("Successfully created genre");
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable String id,@RequestBody @Valid GenreDto dto) throws BusinessException {
        genreService.update(id,dto);
        return ResponseEntity.ok("Genre updated successfully");
    }

    @DeleteMapping("/deleteById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable String id) throws BusinessException {
        genreService.delete(id);
    }

    @GetMapping("/listAll")
    public Page<GenreEntity> listAllGenres(
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "properties", defaultValue = "name") String properties,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        return genreService.listAllGenre(sortDirection, properties, page, size);
    }
}
