package org.example.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contacts_cinema")
@Data
public class ContactCinemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cinema")
    private Integer idCinema;
    @Column
    private String name;
    @Column
    private String address ;
    @Column(name = "map_coordinates")
    private String mapCoordinates;
    @Column
    private String logo;
    @Column
    private Boolean status;

}
