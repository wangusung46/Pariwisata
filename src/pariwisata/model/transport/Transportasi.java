package pariwisata.model.transport;

public class Transportasi {
    
   private Long id;
   private String nama;

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

    @Override
    public String toString() {
        return "Transport{" + "id=" + id + ", nama=" + nama + '}';
    }
   
}
