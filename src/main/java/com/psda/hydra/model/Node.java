package com.psda.hydra.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity
public class Node{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String namaNode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Kadaster kadaster;

    @OneToMany(mappedBy = "node", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Channel> channels = new ArrayList<>();

    public Node(){

    }


    public Node(String namaNode, Kadaster kadaster) {
        this.namaNode = namaNode;
        this.kadaster = kadaster;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaNode() {
        return namaNode;
    }

    public void setNamaNode(String namaNode) {
        this.namaNode = namaNode;
    }

    public Kadaster getKadaster() {
        return kadaster;
    }

    public void setKadaster(Kadaster kadaster) {
        this.kadaster = kadaster;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    }
