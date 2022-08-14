package pariwisata.model.penginapan;

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
public class PenginapanJdbcImplement implements PenginapanJdbc {

    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;
    private static final Logger logger = Logger.getLogger(PenginapanJdbcImplement.class);

    public PenginapanJdbcImplement() {
        connection = Conn.getConnection();
    }

    @Override
    public List<Penginapan> selectAll() {
        List<Penginapan> response = new ArrayList<>();
        try {
            sql = "SELECT * FROM penginapan;";
            preparedStatement = connection.prepareStatement(sql);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Penginapan penginapan = new Penginapan();
                penginapan.setId(resultSet.getLong("id"));
                penginapan.setNama(resultSet.getString("nama"));
                response.add(penginapan);
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
    public Penginapan select(Long request) {
        logger.debug(request.toString());
        Penginapan response = new Penginapan();
        try {
            sql = "select * from penginapan where id = ?;";
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
    public void insert(Penginapan request) {
        logger.debug(request.toString());
        try {
            sql = "INSERT INTO penginapan (nama) VALUES(?);";
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
    public void update(Penginapan request) {
        logger.debug(request.toString());
        try {
            sql = "UPDATE penginapan SET nama=? WHERE id=?;";
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
            sql = "DELETE FROM penginapan WHERE id=?;";
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
