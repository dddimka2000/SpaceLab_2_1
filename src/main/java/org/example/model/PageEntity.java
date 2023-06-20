package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "new_page")
@Data
public class PageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_page")
    Integer idPage;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "url")
    private String url;
    @Column(name = "title")
    private String title;
    @Column(name = "keywords")
    private String keywords;
    @Column(name = "description_seo")
    private String description_seo;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
    Boolean status;
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

}
