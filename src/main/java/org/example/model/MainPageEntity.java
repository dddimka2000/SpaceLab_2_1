package org.example.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "main_page")
public class MainPageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_main_page")
    private Integer idMainPage;
    @Column(name = "telephone1")
    private String telephone1;
    @Column(name = "telephone2")
    private String telephone2;
    @Column(name = "seo_text")
    private String seoText;
    @Column(name = "url")
    private String url;
    @Column(name = "title")
    private String title;
    @Column(name = "keywords")
    private String keywords;
    @Column(name = "description")
    private String description;
    @Column(name = "name_page")
    private String namePage;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
    Boolean status;
}
