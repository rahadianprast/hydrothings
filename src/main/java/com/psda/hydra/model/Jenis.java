package com.psda.hydra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Jenis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nama;
    private Byte img;

    @Enumerated(EnumType.STRING)
    private NamaJenis  type;

    @OneToMany(mappedBy = "jenis", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Kadaster> kadasters;

    public Jenis() {
    }

    public Jenis(String nama, Byte img, NamaJenis type) {
        this.nama = nama;
        this.img = img;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Byte getImg() {
        return img;
    }

    public void setImg(Byte img) {
        this.img = img;
    }

    public NamaJenis getType() {
        return type;
    }

    public void setType(NamaJenis type) {
        this.type = type;
    }

    public List<Kadaster> getKadasters() {
        return kadasters;
    }

    public void setKadasters(List<Kadaster> kadasters) {
        this.kadasters = kadasters;
    }

    public void add(Kadaster kadaster){
        this.kadasters.add(kadaster);
        kadaster.setJenis(this);
    }

    @Override
    public String toString() {
        return "Jenis{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", img=" + img +
                ", type=" + type +
                ", kadasters=" + kadasters +
                '}';
    }
}
