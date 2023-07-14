package org.example.model;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Data
@Table(name="imagine")
public class ImagineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "path")
    String path;
    @Column(name = "background_selection")
    Boolean backgroundSelection;
    @Column(name = "speed")
    Integer speed;
    @Column(name="link")
    String link;
}
