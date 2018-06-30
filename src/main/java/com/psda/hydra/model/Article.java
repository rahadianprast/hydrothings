package com.psda.hydra.model;


import javax.persistence.*;

import java.util.*;

@Entity
public class Article extends BaseEntity{

    private String judul;
    private String body;
    @Lob
    private Byte[] gambar;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kategori> kategoris = new ArrayList<>();

    public Article() {
    }

    public Article(String judul, String body, Byte[] gambar) {
        this.judul = judul;
        this.body = body;
        this.gambar = gambar;

    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Byte[] getGambar() {
        return gambar;
    }

    public void setGambar(Byte[] gambar) {
        this.gambar = gambar;
    }

    public List<Kategori> getKategoris() {
        return kategoris;
    }

    public void setKategoris(List<Kategori> kategoris) {
        this.kategoris = kategoris;
    }

    public void addKategori(Kategori kategori){
        this.kategoris.add(kategori);
        kategori.setArticle(this);
    }


    @Override
    public String toString() {
        return "Article{" +
                "judul='" + judul + '\'' +
                ", body='" + body + '\'' +
                ", gambar=" + Arrays.toString(gambar) +
                ", kategoris=" + kategoris +
                '}';
    }
}
