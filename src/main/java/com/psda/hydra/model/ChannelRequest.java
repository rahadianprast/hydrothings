package com.psda.hydra.model;

import javax.validation.constraints.NotNull;

public class ChannelRequest {
    @NotNull
    private String ch;
    @NotNull
    private Integer identify;

    private Double value;


    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public Integer getIdentify() {
        return identify;
    }

    public void setIdentify(Integer identify) {
        this.identify = identify;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
