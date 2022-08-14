package pariwisata.model.pengunjung;

import java.util.List;

public interface PengunjungJdbc {

    public List<Pengunjung> selectAll();
    
    public Pengunjung select(Long request);

    public void insert(Pengunjung request);

    public void update(Pengunjung request);
    
    public void delete(Long request);

}
