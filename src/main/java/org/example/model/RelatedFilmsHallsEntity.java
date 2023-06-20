package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "halls_films")
@Data
public class RelatedFilmsHallsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @ManyToOne
    @JoinColumn(name = "id_hall")
    HallEntity hall;
    @ManyToOne
    @JoinColumn(name = "id_film")
    FilmEntity film;
    @Column
    Date time;
    @Column
    Integer price;

}
