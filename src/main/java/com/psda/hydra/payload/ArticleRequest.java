package com.psda.hydra.payload;

import javax.persistence.Lob;

import java.util.List;

public class ArticleRequest {

    private String Judul;
    private String body;
    @Lob
    private Byte[] gambar;
    private List<KategoriRequest> kategoris;

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
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

    public List<KategoriRequest> getKategoris() {
        return kategoris;
    }

    public void setKategoris(List<KategoriRequest> kategoris) {
        this.kategoris = kategoris;
    }
}
