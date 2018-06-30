package com.psda.hydra.payload;


import java.time.LocalDateTime;

public class ChannelDTO{

    private LocalDateTime created;
    private Double valMax;
    private Double valMin;


    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Double getValMax() {
        return valMax;
    }

    public void setValMax(Double valMax) {
        this.valMax = valMax;
    }

    public Double getValMin() {
        return valMin;
    }

    public void setValMin(Double valMin) {
        this.valMin = valMin;
    }
}
