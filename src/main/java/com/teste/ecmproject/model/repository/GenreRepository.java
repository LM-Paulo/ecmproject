package com.teste.ecmproject.model.repository;

import com.teste.ecmproject.model.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, UUID> {
    boolean existsByName(String name);
}
