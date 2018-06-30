package com.psda.hydra.payload;

import com.psda.hydra.model.NamaJenis;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

public class JenisRequest {

    @Size(min = 3, max = 15)
    private String nama;
    private Byte img;

    @Enumerated(EnumType.STRING)
    private NamaJenis type;

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
}
