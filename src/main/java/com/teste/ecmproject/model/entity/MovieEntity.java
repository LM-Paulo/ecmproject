package com.teste.ecmproject.model.entity;


import com.teste.ecmproject.api.dto.MovieDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.domain.Persistable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_movies")
public class MovieEntity implements Persistable<UUID> {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGerator")
    @Column(name = "id_movie", unique = true)
    private UUID id;

    @Column(unique = true)
    @NotNull
    @NotBlank(message = "This field is required!")
    @Pattern(regexp = "[A-Z]{3}-\\d{3}(?!000)")
    private String code;

    @NotNull
    @NotBlank(message = "This field is required!")
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank(message = "This field is required!")
    private String synopsis;

    @ManyToMany
    @JoinTable(
            name = "tbl_movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @UniqueElements(message = "The same genre cannot be associated more than once with the same film.")
    @NotEmpty(message = "At least one genre must be associated with the film.")
    @NotNull
    private Set<GenreEntity> genres = new HashSet<>();

    @Min(value = 1900)
    @Max(value = 2024)
    @NotNull
    @NotBlank(message = "This field is required!")
    private Date yearLaunch;

    @NotNull
    @NotBlank(message = "This field is required!")
    @Pattern(regexp = "L|10|12|14|16|18")
    private String parentalRating;

    @Min(1)
    @NotNull
    @NotBlank(message = "This field is required!")
    private Integer duration;

    @NotNull
    @NotBlank(message = "This field is required!")
    private boolean  availability;


    public void setEntity(MovieDto dto){
        this.code = dto.getCode();
        this.name = dto.getName();
        this.synopsis = dto.getSynopsis();
        this.yearLaunch = dto.getYearLaunch();
        this.parentalRating = dto.getParentalRating();
        this.duration = dto.getDuration();
        this.availability = dto.isAvailability();
        this.genres = dto.getGenres();


    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
