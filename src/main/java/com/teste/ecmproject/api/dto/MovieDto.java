package com.teste.ecmproject.api.dto;


import com.teste.ecmproject.model.entity.GenreEntity;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class MovieDto {


    private String code;

    private String name;

    private String synopsis;

    private Set<GenreEntity> genres = new HashSet<>();

    private Date yearLaunch;

    private String parentalRating;

    private Integer duration;

    private boolean  availability;
}
