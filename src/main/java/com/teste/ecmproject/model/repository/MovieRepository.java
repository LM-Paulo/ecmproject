package com.teste.ecmproject.model.repository;


import com.teste.ecmproject.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository  extends JpaRepository<MovieEntity, UUID> {
}
