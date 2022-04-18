package com.example.tr_672019005;

public class ModelListKategori {

    String namakategori, imgkategori, deskripsikategori, type;

    public ModelListKategori() {

    }

    public ModelListKategori(String namakategori, String imgkategori, String deskripsikategori, String type) {
        this.namakategori = namakategori;
        this.imgkategori = imgkategori;
        this.deskripsikategori = deskripsikategori;
        this.type = type;
    }

    public String getNamakategori() {
        return namakategori;
    }

    public void setNamakategori(String namakategori) {
        this.namakategori = namakategori;
    }

    public String getImgkategori() {
        return imgkategori;
    }

    public void setImgkategori(String imgkategori) {
        this.imgkategori = imgkategori;
    }

    public String getDeskripsikategori() {
        return deskripsikategori;
    }

    public void setDeskripsikategori(String deskripsikategori) {
        this.deskripsikategori = deskripsikategori;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
