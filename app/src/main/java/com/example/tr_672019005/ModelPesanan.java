package com.example.tr_672019005;

import java.io.Serializable;

public class ModelPesanan implements Serializable {

    String namabarang, hargabarang, imgbarang, totalbarang, currentDate, currentTime;
    int totalharga;

    public ModelPesanan() {
    }

    public ModelPesanan(String namabarang, String hargabarang, String imgbarang, String totalbarang, String currentDate, String currentTime, int totalharga) {
        this.namabarang = namabarang;
        this.hargabarang = hargabarang;
        this.imgbarang = imgbarang;
        this.totalbarang = totalbarang;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
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

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getTotalharga() {
        return totalharga;
    }

    public void setTotalharga(int totalharga) {
        this.totalharga = totalharga;
    }
}