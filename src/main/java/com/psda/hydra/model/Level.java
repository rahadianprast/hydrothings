package com.psda.hydra.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Level extends BaseEntity {
    @NotNull
    private Double max;

    @NotNull
    private Double siap;

    @NotNull
    private Double awas;

    @NotNull
    private Double siaga;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Sungai sungai;

    protected Level(){
     super();
    }

    public Level(Double max, Double siap, Double awas, Double siaga, Sungai sungai) {
        this.max = max;
        this.siap = siap;
        this.awas = awas;
        this.siaga = siaga;
        this.sungai = sungai;
    }


    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getSiap() {
        return siap;
    }

    public void setSiap(Double siap) {
        this.siap = siap;
    }

    public Double getAwas() {
        return awas;
    }

    public void setAwas(Double awas) {
        this.awas = awas;
    }

    public Double getSiaga() {
        return siaga;
    }

    public void setSiaga(Double siaga) {
        this.siaga = siaga;
    }

    public Sungai getSungai() {
        return sungai;
    }

    public void setSungai(Sungai sungai) {
        this.sungai = sungai;
    }
}
