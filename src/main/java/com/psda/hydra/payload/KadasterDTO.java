package com.psda.hydra.payload;


import com.psda.hydra.model.Jenis;

import java.util.List;

public class KadasterDTO {

    private String namaPos;
    private String reg;
    private String lokasi;

    private List<NodeDTO> nodes;

    private Jenis jenis;

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

    public List<NodeDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeDTO> nodes) {
        this.nodes = nodes;
    }

    public Jenis getJenis() {
        return jenis;
    }

    public void setJenis(Jenis jenis) {
        this.jenis = jenis;
    }
}
