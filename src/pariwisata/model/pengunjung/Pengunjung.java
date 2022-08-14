package pariwisata.model.pengunjung;

public class Pengunjung {
    
    private Long id;
    private String nama;
    private String nik;
    private String telepon;
    private String alamat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    @Override
    public String toString() {
        return "Pengunjung{" + "id=" + id + ", nama=" + nama + ", nik=" + nik + ", telepon=" + telepon + ", alamat=" + alamat + '}';
    }
    
    
}
