package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Data
@Entity
@Table(name = "hall")
public class HallEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_hall", nullable = false)
    private Integer idHall;

    @ManyToOne
    @JoinColumn(name = "cinema_id_cinema")
    private CinemaEntity cinemaEntity;

    private String name;
    private String description;
    private String schemeHall;
    private String topBanner;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String url;
    private String title;
    private String keywords;
    private String descriptionSeo;

    @OneToMany(mappedBy = "hall")
    private List<RelatedFilmsHallsEntity> sessions = new ArrayList<>();


    @Column(name = "date_creation")
    private LocalDateTime date;

    public Integer getIdHall() {
        return idHall;
    }

    public void setIdHall(Integer idHall) {
        this.idHall = idHall;
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
    @Column(name = "scheme_hall", nullable = true, length = 150)
    public String getSchemeHall() {
        return schemeHall;
    }

    public void setSchemeHall(String schemeHall) {
        this.schemeHall = schemeHall;
    }

    @Basic
    @Column(name = "top_banner", nullable = true, length = 150)
    public String getTopBanner() {
        return topBanner;
    }

    public void setTopBanner(String topBanner) {
        this.topBanner = topBanner;
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

    @Basic
    @Column(name = "description_seo", nullable = true, length = 255)
    public String getDescriptionSeo() {
        return descriptionSeo;
    }

    public void setDescriptionSeo(String descriptionSeo) {
        this.descriptionSeo = descriptionSeo;
    }

}
