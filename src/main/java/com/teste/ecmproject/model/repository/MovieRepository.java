package com.teste.ecmproject.model.repository;


import com.teste.ecmproject.model.entity.GenreEntity;
import com.teste.ecmproject.model.entity.MovieEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MovieRepository  extends JpaRepository<MovieEntity, UUID> {

    List<MovieEntity> findByAvailabilityIsTrueAndNameContainingIgnoreCase(String name);

    List<MovieEntity> findByAvailabilityIsTrueAndGenresNameIgnoreCase(String genre);

    List<MovieEntity> findByAvailabilityIsFalse();

    @Modifying
    @Transactional
    @Query("UPDATE MovieEntity m " +
            "SET m.name = :name, m.synopsis = :synopsis, m.genres = :genres, " +
            "m.yearLaunch = :yearLaunch, m.parentalRating = :parentalRating, " +
            "m.duration = :duration, m.availability = :availability " +
            "WHERE m.code = :code")
    void updateMovie(
            @Param("code") String code,
            @Param("name") String name,
            @Param("synopsis") String synopsis,
            @Param("genres") Set<GenreEntity> genres,
            @Param("yearLaunch") Date yearLaunch,
            @Param("parentalRating") String parentalRating,
            @Param("duration") Integer duration,
            @Param("availability") boolean availability
    );
}
