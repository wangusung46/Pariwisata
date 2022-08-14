package pariwisata.model.pengunjung;

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
public class PengunjungJdbcImplement implements PengunjungJdbc {

    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;
    private static final Logger logger = Logger.getLogger(PengunjungJdbcImplement.class);

    public PengunjungJdbcImplement() {
        connection = Conn.getConnection();
    }

    @Override
    public List<Pengunjung> selectAll() {
        List<Pengunjung> response = new ArrayList<>();
        try {
            sql = "SELECT * FROM pengunjung;";
            preparedStatement = connection.prepareStatement(sql);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pengunjung pengunjung = new Pengunjung();
                pengunjung.setId(resultSet.getLong("id"));
                pengunjung.setNama(resultSet.getString("nama"));
                pengunjung.setNik(resultSet.getString("nik"));
                pengunjung.setTelepon(resultSet.getString("telepon"));
                pengunjung.setAlamat(resultSet.getString("alamat"));
                response.add(pengunjung);
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
    public Pengunjung select(Long request) {
        logger.debug(request.toString());
        Pengunjung response = new Pengunjung();
        try {
            sql = "select * from pengunjung where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                response.setId(resultSet.getLong("id"));
                response.setNama(resultSet.getString("nama"));
                response.setNik(resultSet.getString("nik"));
                response.setTelepon(resultSet.getString("telepon"));
                response.setAlamat(resultSet.getString("alamat"));
            }
            logger.debug(response.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
        }
        return response;
    }

    @Override
    public void insert(Pengunjung request) {
        logger.debug(request.toString());
        try {
            sql = "INSERT INTO pengunjung (nama, nik, telepon, alamat) VALUES(?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getNama());
            preparedStatement.setString(2, request.getNik());
            preparedStatement.setString(3, request.getTelepon());
            preparedStatement.setString(4, request.getAlamat());
            logger.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(Pengunjung request) {
        logger.debug(request.toString());
        try {
            sql = "UPDATE pengunjung SET nama=?, nik=?, telepon=?, alamat=? WHERE id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getNama());
            preparedStatement.setString(2, request.getNik());
            preparedStatement.setString(3, request.getTelepon());
            preparedStatement.setString(4, request.getAlamat());
            preparedStatement.setLong(5, request.getId());
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
            sql = "DELETE FROM pengunjung WHERE id=?;";
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
