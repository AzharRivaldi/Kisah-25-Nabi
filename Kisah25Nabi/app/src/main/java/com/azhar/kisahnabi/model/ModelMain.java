package com.azhar.kisahnabi.model;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class ModelMain implements Serializable {

    private String name;
    private String thn_kelahiran;
    private String usia;
    private String description;
    private String tmp;
    private String image_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThn_kelahiran() {
        return thn_kelahiran;
    }

    public void setThn_kelahiran(String thn_kelahiran) {
        this.thn_kelahiran = thn_kelahiran;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
