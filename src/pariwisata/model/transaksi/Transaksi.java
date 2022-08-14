package pariwisata.model.transaksi;

import java.util.Date;

public class Transaksi {
    
    private Long id;
    private Long id_wisata;
    private String paket_wisata;
    private Long harga_wisata;
    private String via_pembayaran;
    private Long id_penginapan;
    private String nama_penginapan;
    private Long id_pengunjung;
    private String nama_pengunjung;
    private String deskripsi_makanan_minuman;
    private String deskripsi_tambahan;
    private Date dari;
    private Date sampai;
    private Long total_tanggal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_wisata() {
        return id_wisata;
    }

    public void setId_wisata(Long id_wisata) {
        this.id_wisata = id_wisata;
    }

    public String getPaket_wisata() {
        return paket_wisata;
    }

    public void setPaket_wisata(String paket_wisata) {
        this.paket_wisata = paket_wisata;
    }

    public Long getHarga_wisata() {
        return harga_wisata;
    }

    public void setHarga_wisata(Long harga_wisata) {
        this.harga_wisata = harga_wisata;
    }

    public String getVia_pembayaran() {
        return via_pembayaran;
    }

    public void setVia_pembayaran(String via_pembayaran) {
        this.via_pembayaran = via_pembayaran;
    }

    public Long getId_penginapan() {
        return id_penginapan;
    }

    public void setId_penginapan(Long id_penginapan) {
        this.id_penginapan = id_penginapan;
    }

    public String getNama_penginapan() {
        return nama_penginapan;
    }

    public void setNama_penginapan(String nama_penginapan) {
        this.nama_penginapan = nama_penginapan;
    }

    public Long getId_pengunjung() {
        return id_pengunjung;
    }

    public void setId_pengunjung(Long id_pengunjung) {
        this.id_pengunjung = id_pengunjung;
    }

    public String getNama_pengunjung() {
        return nama_pengunjung;
    }

    public void setNama_pengunjung(String nama_pengunjung) {
        this.nama_pengunjung = nama_pengunjung;
    }

    public String getDeskripsi_makanan_minuman() {
        return deskripsi_makanan_minuman;
    }

    public void setDeskripsi_makanan_minuman(String deskripsi_makanan_minuman) {
        this.deskripsi_makanan_minuman = deskripsi_makanan_minuman;
    }

    public String getDeskripsi_tambahan() {
        return deskripsi_tambahan;
    }

    public void setDeskripsi_tambahan(String deskripsi_tambahan) {
        this.deskripsi_tambahan = deskripsi_tambahan;
    }

    public Date getDari() {
        return dari;
    }

    public void setDari(Date dari) {
        this.dari = dari;
    }

    public Date getSampai() {
        return sampai;
    }

    public void setSampai(Date sampai) {
        this.sampai = sampai;
    }

    public Long getTotal_tanggal() {
        return total_tanggal;
    }

    public void setTotal_tanggal(Long total_tanggal) {
        this.total_tanggal = total_tanggal;
    }
    
    
}
