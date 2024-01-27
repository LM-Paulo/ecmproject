package com.teste.ecmproject.model.entity;

import com.teste.ecmproject.api.dto.GenreDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_genre")
public class GenreEntity {

    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @Column(name = "genre_id", unique = true, updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true)
    @NotBlank
    private String name;

     public void SetEntity(GenreDto dto){
         this.name = dto.getName();
     }
}
