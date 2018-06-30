package com.psda.hydra.model;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Entity
public class Kadaster implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String namaPos;
    @NotNull
    private String reg;
    @NotNull
    private String lokasi;
    private Double lat;
    private Double lng;
    private Double elevasi;
    private String apiKey;

    @OneToMany(mappedBy = "kadaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Node> nodes = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "kadaster")
    private Sungai sungai;

    @ManyToOne(fetch = FetchType.EAGER)
    private Jenis jenis;

    public Kadaster() {
    }

    public Kadaster(String namaPos,String reg, String lokasi, Jenis jenis) {
        this.namaPos = namaPos;
        this.reg = reg;
        this.lokasi = lokasi;
        this.jenis = jenis;
    }

    @PrePersist
    public void onPrePersist(){
        setApiKey(RandomStringUtils.randomAlphanumeric(6));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPos() {
        return namaPos;
    }

    public void setNamaPos(String namaPos) {
        this.namaPos = namaPos;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getElevasi() {
        return elevasi;
    }

    public void setElevasi(Double elevasi) {
        this.elevasi = elevasi;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Sungai getSungai() {
        return sungai;
    }

    public void setSungai(Sungai sungai) {
        this.sungai = sungai;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Jenis getJenis() {
        return jenis;
    }

    public void setJenis(Jenis jenis) {
        this.jenis = jenis;
    }


    public void addNodes(Node node){
        this.nodes.add(node);
        node.setKadaster(this);
    }


}
