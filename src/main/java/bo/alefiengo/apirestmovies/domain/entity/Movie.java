package bo.alefiengo.apirestmovies.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Basic
    @NotNull(message = "Not permitted")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "Only characters and numbers are permitted")
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @NonNull
    @Basic
    @NotNull(message = "Not permitted")
    @Column(name = "title", nullable = false)
    private String title;

    @Basic
    @Column(name = "description")
    private String description;

}
