package pariwisata.model.wisata;

public class Wisata {
    
    private Long id;
    private String paket;
    private Long harga;
    private Long id_penginapan;
    private String nama_penginapan;
    private Long id_transportasi;
    private String nama_transportasi;
    private String deskripsi_makanan_minuman;
    private String deskripsi_tambahan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public Long getHarga() {
        return harga;
    }

    public void setHarga(Long harga) {
        this.harga = harga;
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

    public Long getId_transportasi() {
        return id_transportasi;
    }

    public void setId_transportasi(Long id_transportasi) {
        this.id_transportasi = id_transportasi;
    }

    public String getNama_transportasi() {
        return nama_transportasi;
    }

    public void setNama_transportasi(String nama_transportasi) {
        this.nama_transportasi = nama_transportasi;
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

    @Override
    public String toString() {
        return "Wisata{" + "id=" + id + ", paket=" + paket + ", harga=" + harga + ", id_penginapan=" + id_penginapan + ", nama_penginapan=" + nama_penginapan + ", id_transportasi=" + id_transportasi + ", nama_transportasi=" + nama_transportasi + ", deskripsi_makanan_minuman=" + deskripsi_makanan_minuman + ", deskripsi_tambahan=" + deskripsi_tambahan + '}';
    }
     
}
