package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stock")
@Data
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String link;
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
    private Boolean status;
    @Column
    private Date date;
}
