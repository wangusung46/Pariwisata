package pariwisata.model.transport;

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
public class TransportasiJdbcImplement implements TransportasiJdbc {

    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;
    private static final Logger logger = Logger.getLogger(TransportasiJdbcImplement.class);

    public TransportasiJdbcImplement() {
        connection = Conn.getConnection();
    }

    @Override
    public List<Transportasi> selectAll() {
        List<Transportasi> response = new ArrayList<>();
        try {
            sql = "SELECT * FROM transportasi";
            preparedStatement = connection.prepareStatement(sql);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transportasi transportasi = new Transportasi();
                transportasi.setId(resultSet.getLong("id"));
                transportasi.setNama(resultSet.getString("nama"));
                response.add(transportasi);
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
    public Transportasi select(Long request) {
        logger.debug(request.toString());
        Transportasi response = new Transportasi();
        try {
            sql = "select * from transportasi where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                response.setId(resultSet.getLong("id"));
                response.setNama(resultSet.getString("nama"));
            }
            logger.debug(response.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
        }
        return response;
    }


    @Override
    public void insert(Transportasi request) {
        logger.debug(request.toString());
        try {
            sql = "INSERT INTO transportasi (nama) VALUES(?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getNama());
            logger.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(Transportasi request) {
        logger.debug(request.toString());
        try {
            sql = "UPDATE transportasi SET nama=? WHERE id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, request.getNama());
            preparedStatement.setLong(2, request.getId());
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
            sql = "DELETE FROM transportasi WHERE id=?;";
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
