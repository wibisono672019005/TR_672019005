package com.example.tr_672019005;

public class ModelKeranjang {

    String namabarang, hargabarang, totalbarang;
    int totalharga;

    public ModelKeranjang() {
    }

    public ModelKeranjang(String namabarang, String hargabarang, String totalbarang, int totalharga) {
        this.namabarang = namabarang;
        this.hargabarang = hargabarang;
        this.totalbarang = totalbarang;
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

    public String getTotalbarang() {
        return totalbarang;
    }

    public void setTotalbarang(String totalbarang) {
        this.totalbarang = totalbarang;
    }

    public int getTotalharga() {
        return totalharga;
    }

    public void setTotalharga(int totalharga) {
        this.totalharga = totalharga;
    }
}