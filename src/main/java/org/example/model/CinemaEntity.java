package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "cinema")
public class CinemaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cinema")
    private Integer idCinema;
    @OneToMany(mappedBy = "cinemaEntity")
    List<HallEntity> hallEntityList=new ArrayList<>();
    private String name;
    private String description;
    private String conditions;
    private String logoPath;
    private String topBannerPath;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String url;
    private String title;
    private String keywords;
    @Column
    private String descriptionSeo;



    public Integer getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(Integer idCinema) {
        this.idCinema = idCinema;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "conditions", nullable = true, length = 45)
    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    @Basic
    @Column(name = "logo_path", nullable = true, length = 150)
    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @Basic
    @Column(name = "top_banner_path", nullable = true, length = 150)
    public String getTopBannerPath() {
        return topBannerPath;
    }

    public void setTopBannerPath(String topBannerPath) {
        this.topBannerPath = topBannerPath;
    }

    @Basic
    @Column(name = "img1", nullable = true, length = 150)
    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    @Basic
    @Column(name = "img2", nullable = true, length = 150)
    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    @Basic
    @Column(name = "img3", nullable = true, length = 150)
    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    @Basic
    @Column(name = "img4", nullable = true, length = 150)
    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    @Basic
    @Column(name = "img5", nullable = true, length = 150)
    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 45)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "keywords", nullable = true, length = 45)
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

}
