package org.example.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "distribution")
@Data
public class DistributionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column
    private String name;
    @Column
    private String path;
    @Column(name = "number_of_times")
    private Integer num_times;
}
