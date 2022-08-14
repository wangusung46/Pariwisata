package pariwisata.model.wisata;

import java.util.List;

/**
 *
 * @author Khanza
 */
public interface WisataJdbc {

    public List<Wisata> selectAll();
    
    public Wisata select(Long request);

    public void insert(Wisata request);

    public void update(Wisata request);
    
    public void delete(Long request);

}
