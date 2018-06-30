package com.psda.hydra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Set;


@JsonRootName(value = "sungai")
@Entity
public class Sungai{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namaSungai;
    private String kelas;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kadaster_id", nullable = false)
    @JsonIgnore
    private Kadaster kadaster;

    @OneToMany(mappedBy = "sungai", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rumus> rumus;

    @OneToMany(mappedBy = "sungai", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Level> level;

    @OneToMany(mappedBy = "sungai", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Debit> debit;

    @OneToMany(mappedBy = "sungai", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sws> sws;

    public Sungai() {
    }

    public Sungai(String namaSungai, String kelas) {
        this.namaSungai = namaSungai;
        this.kelas = kelas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaSungai() {
        return namaSungai;
    }

    public void setNamaSungai(String namaSungai) {
        this.namaSungai = namaSungai;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public Kadaster getKadaster() {
        return kadaster;
    }

    public void setKadaster(Kadaster kadaster) {
        this.kadaster = kadaster;
    }

    public Set<Rumus> getRumus() {
        return rumus;
    }

    public void setRumus(Set<Rumus> rumus) {
        this.rumus = rumus;
    }

    public Set<Level> getLevel() {
        return level;
    }

    public void setLevel(Set<Level> level) {
        this.level = level;
    }

    public Set<Debit> getDebit() {
        return debit;
    }

    public void setDebit(Set<Debit> debit) {
        this.debit = debit;
    }

    public Set<Sws> getSws() {
        return sws;
    }

    public void setSws(Set<Sws> sws) {
        this.sws = sws;
    }

    @Override
    public String toString() {
        return "Sungai{" +
                "id=" + id +
                ", namaSungai='" + namaSungai + '\'' +
                ", kelas='" + kelas + '\'' +
                ", kadaster=" + kadaster +
                ", rumus=" + rumus +
                ", level=" + level +
                ", debit=" + debit +
                ", sws=" + sws +
                '}';
    }
}
