package pariwisata.model.wisata;

import Koneksi.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author Khanza
 */
public class WisataJdbcImplement implements WisataJdbc {

    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;
    private static final Logger logger = Logger.getLogger(WisataJdbcImplement.class);

    public WisataJdbcImplement() {
        connection = Conn.getConnection();
    }

    @Override
    public List<Wisata> selectAll() {
        List<Wisata> response = new ArrayList<>();
        try {
            sql = "SELECT * FROM wisata";
            preparedStatement = connection.prepareStatement(sql);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Wisata wisata = new Wisata();
                wisata.setId(resultSet.getLong("id"));
                wisata.setPaket(resultSet.getString("paket"));
                wisata.setHarga(resultSet.getLong("harga"));
                wisata.setId_penginapan(resultSet.getLong("id_penginapan"));
                wisata.setNama_penginapan(resultSet.getString("nama_penginapan"));
                wisata.setId_transportasi(resultSet.getLong("id_transportasi"));
                wisata.setNama_transportasi(resultSet.getString("nama_transportasi"));
                wisata.setDeskripsi_makanan_minuman(resultSet.getString("deskripsi_makanan_minuman"));
                wisata.setDeskripsi_tambahan(resultSet.getString("deskripsi_tambahan"));
                response.add(wisata);
            }
            resultSet.close();
            preparedStatement.close();
            logger.debug(response.toString());
            return response;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Wisata select(Long request) {
        logger.debug(request.toString());
        Wisata response = new Wisata();
        try {
            sql = "select * from wisata where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                response.setId(resultSet.getLong("id"));
                response.setPaket(resultSet.getString("paket"));
                response.setHarga(resultSet.getLong("harga"));
                response.setId_penginapan(resultSet.getLong("id_penginapan"));
                response.setNama_penginapan(resultSet.getString("nama_penginapan"));
                response.setId_transportasi(resultSet.getLong("id_transportasi"));
                response.setNama_transportasi(resultSet.getString("nama_transportasi"));
                response.setDeskripsi_makanan_minuman(resultSet.getString("deskripsi_makanan_minuman"));
                response.setDeskripsi_tambahan(resultSet.getString("deskripsi_tambahan"));
            }
            logger.debug(response.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
        }
        return response;
    }

    @Override
    public void insert(Wisata request) {
        logger.debug(request.toString());
        try {
            sql = "INSERT INTO wisata (paket, harga, id_penginapan, nama_penginapan, id_transportasi, nama_transportasi, deskripsi_makanan_minuman, deskripsi_tambahan) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getPaket());
            preparedStatement.setLong(2, request.getHarga());
            preparedStatement.setLong(3, request.getId_penginapan());
            preparedStatement.setString(4, request.getNama_penginapan());
            preparedStatement.setLong(5, request.getId_transportasi());
            preparedStatement.setString(6, request.getNama_transportasi());
            preparedStatement.setString(7, request.getDeskripsi_makanan_minuman());
            preparedStatement.setString(8, request.getDeskripsi_tambahan());
            logger.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(Wisata request) {
        logger.debug(request.toString());
        try {
            sql = "UPDATE wisata SET paket=?, harga=?, id_penginapan=?, nama_penginapan=?, id_transportasi=?, nama_transportasi=?, deskripsi_makanan_minuman=?, deskripsi_tambahan=? WHERE id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getPaket());
            preparedStatement.setLong(2, request.getHarga());
            preparedStatement.setLong(3, request.getId_penginapan());
            preparedStatement.setString(4, request.getNama_penginapan());
            preparedStatement.setLong(5, request.getId_transportasi());
            preparedStatement.setString(6, request.getNama_transportasi());
            preparedStatement.setString(7, request.getDeskripsi_makanan_minuman());
            preparedStatement.setString(8, request.getDeskripsi_tambahan());
            preparedStatement.setLong(9, request.getId());
            logger.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long request) {
        logger.debug(request.toString());
        try {
            sql = "DELETE FROM wisata WHERE id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request);
            logger.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
