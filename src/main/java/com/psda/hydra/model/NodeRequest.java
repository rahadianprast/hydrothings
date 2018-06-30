package com.psda.hydra.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class NodeRequest {
    @NotNull
    private String namaNode;
    @NotNull
    private String jenis;

    private List<ChannelRequest> channels;

    public String getNamaNode() {
        return namaNode;
    }

    public void setNamaNode(String namaNode) {
        this.namaNode = namaNode;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public List<ChannelRequest> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelRequest> channels) {
        this.channels = channels;
    }
}
