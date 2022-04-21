package com.example.tr_672019005;

import java.io.Serializable;

public class ModelPencarian implements Serializable {

    String namabarang, deskripsibarang, imgbarang, type;
    int hargabarang;

    public ModelPencarian() {

    }

    public ModelPencarian(String namabarang, String deskripsibarang, String imgbarang, String type, int hargabarang) {
        this.namabarang = namabarang;
        this.deskripsibarang = deskripsibarang;
        this.imgbarang = imgbarang;
        this.type = type;
        this.hargabarang = hargabarang;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getDeskripsibarang() {
        return deskripsibarang;
    }

    public void setDeskripsibarang(String deskripsibarang) {
        this.deskripsibarang = deskripsibarang;
    }

    public String getImgbarang() {
        return imgbarang;
    }

    public void setImgbarang(String imgbarang) {
        this.imgbarang = imgbarang;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHargabarang() {
        return hargabarang;
    }

    public void setHargabarang(int hargabarang) {
        this.hargabarang = hargabarang;
    }
}
