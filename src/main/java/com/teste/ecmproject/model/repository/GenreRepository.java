package com.teste.ecmproject.model.repository;

import com.teste.ecmproject.model.entity.GenreEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, String> {
    boolean existsByName(String name);

    Optional<GenreEntity>findById(String id);

    @Modifying
    @Transactional
    @Query("UPDATE GenreEntity g SET g.name = :name WHERE g.id = :id")
    void updateGenre(@Param("id") String id, @Param("name") String name);
}
