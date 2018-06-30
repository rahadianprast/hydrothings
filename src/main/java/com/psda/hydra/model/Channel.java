package com.psda.hydra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Optional;


@Entity
public class Channel extends BaseEntity {

    @NotNull
    private Double value;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Node node;

    public Channel() {
    }

    public Channel(Double value, Node node) {
        this.value = value;
        this.node = node;
    }



    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

   }
