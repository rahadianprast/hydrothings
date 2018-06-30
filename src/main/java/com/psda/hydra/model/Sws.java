package com.psda.hydra.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Sws extends BaseEntity {


    private String indukSungai;
    private String panjangSungai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Sungai sungai;

    protected Sws(){
      super();
    }

    public Sws(String indukSungai, String panjangSungai, Sungai sungai) {
        this.indukSungai = indukSungai;
        this.panjangSungai = panjangSungai;
        this.sungai = sungai;
    }


    public String getIndukSungai() {
        return indukSungai;
    }

    public void setIndukSungai(String indukSungai) {
        this.indukSungai = indukSungai;
    }

    public String getPanjangSungai() {
        return panjangSungai;
    }

    public void setPanjangSungai(String panjangSungai) {
        this.panjangSungai = panjangSungai;
    }

    public Sungai getSungai() {
        return sungai;
    }

    public void setSungai(Sungai sungai) {
        this.sungai = sungai;
    }
}
