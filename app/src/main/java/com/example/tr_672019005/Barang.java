package com.example.tr_672019005;

public class Barang {

    String namabarang, deskripsibarang;
    long hargabarang;

    public Barang() {

    }

    public Barang(String namabarang, String deksripsibarang, long hargabarang) {
        this.namabarang = namabarang;
        this.deskripsibarang = deksripsibarang;
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

    public long getHargabarang() {
        return hargabarang;
    }

    public void setHargabarang(long hargabarang) {
        this.hargabarang = hargabarang;
    }
}
