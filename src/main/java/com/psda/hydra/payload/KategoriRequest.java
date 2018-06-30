package com.psda.hydra.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class KategoriRequest {

    @NotNull
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
