package com.example.tr_672019005;

public class ModelKategoriBarang {

    String namakategori, imgkategori, type;

    public ModelKategoriBarang() {

    }

    public ModelKategoriBarang(String namakategori, String imgkategori, String type) {
        this.namakategori = namakategori;
        this.imgkategori = imgkategori;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
