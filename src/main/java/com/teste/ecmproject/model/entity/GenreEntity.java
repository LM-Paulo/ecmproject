package com.teste.ecmproject.model.entity;

import com.teste.ecmproject.api.dto.GenreDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.domain.Persistable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "tbl_genre")
public class GenreEntity implements Persistable<String> {

    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    @NotBlank
    private String name;

     public void SetEntity(GenreDto dto){
         this.name = dto.getName();
     }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
