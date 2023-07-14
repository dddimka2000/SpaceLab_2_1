package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "film")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Film")
    private Integer idFilm;

    @OneToMany(mappedBy = "film")
    private List<RelatedFilmsHallsEntity> sessions = new ArrayList<>();

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String linkTrailer;
    @Column
    private String type;
    @Column
    private String url;
    @Column
    private String title;
    @Column
    private String descriptionSeo;
    @Column
    private String keywords;
    @Column
    private String img;
    @Column
    private String img1;
    @Column
    private String img2;
    @Column
    private String img3;
    @Column
    private String img4;
    @Column
    private String img5;
    @Column
    private Boolean isUpcoming;

}
