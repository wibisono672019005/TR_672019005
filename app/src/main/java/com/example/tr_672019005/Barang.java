package com.example.tr_672019005;

import java.io.Serializable;

public class Barang implements Serializable {

    String namabarang, deskripsibarang;
    int hargabarang;

    public Barang() {

    }


    public Barang(String namabarang, String deskripsibarang, int hargabarang) {
        this.namabarang = namabarang;
        this.deskripsibarang = deskripsibarang;
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

    public int getHargabarang() {
        return hargabarang;
    }

    public void setHargabarang(int hargabarang) {
        this.hargabarang = hargabarang;
    }
}
