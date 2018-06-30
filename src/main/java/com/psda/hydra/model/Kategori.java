package com.psda.hydra.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import javax.validation.constraints.NotNull;



@Entity
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Article article;

    public Kategori() {
    }

    public Kategori(String tag) {
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
