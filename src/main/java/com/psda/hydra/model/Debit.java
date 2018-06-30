package com.psda.hydra.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Debit extends BaseEntity{

    private Double deb;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Sungai sungai;

    protected Debit(){
        super();
    }

    public Debit(Double deb, Sungai sungai) {
        this.deb = deb;
        this.sungai = sungai;
    }

    public Double getDeb() {
        return deb;
    }

    public void setDeb(Double deb) {
        this.deb = deb;
    }

    public Sungai getSungai() {
        return sungai;
    }

    public void setSungai(Sungai sungai) {
        this.sungai = sungai;
    }
}
