package pariwisata.model.transport;

import java.util.List;

/**
 *
 * @author Khanza
 */
public interface TransportasiJdbc {

    public List<Transportasi> selectAll();
    
    public Transportasi select(Long request);

    public void insert(Transportasi request);

    public void update(Transportasi request);
    
    public void delete(Long request);

}
