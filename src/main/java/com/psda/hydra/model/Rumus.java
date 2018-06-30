package com.psda.hydra.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Rumus extends BaseEntity {

    private Double koefA;
    private Double koefB;
    private Double koefC;



    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Sungai sungai;

    protected Rumus(){
     super();
    }

    public Rumus(Double koefA, Double koefB, Double koefC, Sungai sungai) {
        this.koefA = koefA;
        this.koefB = koefB;
        this.koefC = koefC;
        this.sungai = sungai;
    }


    public Double getKoefA() {
        return koefA;
    }

    public void setKoefA(Double koefA) {
        this.koefA = koefA;
    }

    public Double getKoefB() {
        return koefB;
    }

    public void setKoefB(Double koefB) {
        this.koefB = koefB;
    }

    public Double getKoefC() {
        return koefC;
    }

    public void setKoefC(Double koefC) {
        this.koefC = koefC;
    }

    public Sungai getSungai() {
        return sungai;
    }

    public void setSungai(Sungai sungai) {
        this.sungai = sungai;
    }

    @Override
    public String toString() {
        return "Rumus{" +
                "koefA=" + koefA +
                ", koefB=" + koefB +
                ", koefC=" + koefC +
                ", sungai=" + sungai +
                '}';
    }
}
