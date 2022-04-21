package com.example.tr_672019005;

import java.io.Serializable;

public class ModelKeranjang implements Serializable {

    String namabarang, hargabarang, imgbarang, totalbarang, currentTime, barangKeranjangId;
    int totalharga;

    public ModelKeranjang() {
    }

    public ModelKeranjang(String namabarang, String hargabarang, String imgbarang, String totalbarang, String currentTime, String barangKeranjangId, int totalharga) {
        this.namabarang = namabarang;
        this.hargabarang = hargabarang;
        this.imgbarang = imgbarang;
        this.totalbarang = totalbarang;
        this.currentTime = currentTime;
        this.barangKeranjangId = barangKeranjangId;
        this.totalharga = totalharga;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getHargabarang() {
        return hargabarang;
    }

    public void setHargabarang(String hargabarang) {
        this.hargabarang = hargabarang;
    }

    public String getImgbarang() {
        return imgbarang;
    }

    public void setImgbarang(String imgbarang) {
        this.imgbarang = imgbarang;
    }

    public String getTotalbarang() {
        return totalbarang;
    }

    public void setTotalbarang(String totalbarang) {
        this.totalbarang = totalbarang;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getBarangKeranjangId() {
        return barangKeranjangId;
    }

    public void setBarangKeranjangId(String barangKeranjangId) {
        this.barangKeranjangId = barangKeranjangId;
    }

    public int getTotalharga() {
        return totalharga;
    }

    public void setTotalharga(int totalharga) {
        this.totalharga = totalharga;
    }
}