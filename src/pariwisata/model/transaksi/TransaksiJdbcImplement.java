package pariwisata.model.transaksi;

import Koneksi.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

public class TransaksiJdbcImplement implements TransaksiJdbc {

    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;
    private static final Logger logger = Logger.getLogger(TransaksiJdbcImplement.class);

    public TransaksiJdbcImplement() {
        connection = Conn.getConnection();
    }

    @Override
    public List<Transaksi> selectAll() {
        List<Transaksi> response = new ArrayList<>();
        try {
            sql = "SELECT * FROM paket_wisata";
            preparedStatement = connection.prepareStatement(sql);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(resultSet.getLong("id"));
                transaksi.setId_wisata(resultSet.getLong("id_wisata"));
                transaksi.setPaket_wisata(resultSet.getString("paket_wisata"));
                transaksi.setHarga_wisata(resultSet.getLong("harga_wisata"));
                transaksi.setVia_pembayaran(resultSet.getString("via_pembayaran"));
                transaksi.setId_penginapan(resultSet.getLong("id_penginapan"));
                transaksi.setNama_penginapan(resultSet.getString("nama_penginapan"));
                transaksi.setId_pengunjung(resultSet.getLong("id_pengunjung"));
                transaksi.setNama_pengunjung(resultSet.getString("nama_pengunjung"));
                transaksi.setDeskripsi_makanan_minuman(resultSet.getString("deskripsi_makanan_minuman"));
                transaksi.setDeskripsi_tambahan(resultSet.getString("deskripsi_tambahan"));
                transaksi.setDari(resultSet.getDate("dari"));
                transaksi.setSampai(resultSet.getDate("sampai"));
                transaksi.setTotal_tanggal(resultSet.getLong("total_tanggal"));

                response.add(transaksi);
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
    public Transaksi select(Long request) {
        logger.debug(request.toString());
        Transaksi response = new Transaksi();
        try {
            sql = "select * from paket_wisata where id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request);
            logger.debug(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                response.setId(resultSet.getLong("id"));
                response.setId_wisata(resultSet.getLong("id_wisata"));
                response.setPaket_wisata(resultSet.getString("paket_wisata"));
                response.setHarga_wisata(resultSet.getLong("harga_wisata"));
                response.setVia_pembayaran(resultSet.getString("via_pembayaran"));
                response.setId_penginapan(resultSet.getLong("id_penginapan"));
                response.setNama_penginapan(resultSet.getString("nama_penginapan"));
                response.setId_pengunjung(resultSet.getLong("id_pengunjung"));
                response.setNama_pengunjung(resultSet.getString("nama_pengunjung"));
                response.setDeskripsi_makanan_minuman(resultSet.getString("deskripsi_makanan_minuman"));
                response.setDeskripsi_tambahan(resultSet.getString("deskripsi_tambahan"));
                response.setDari(resultSet.getDate("dari"));
                response.setSampai(resultSet.getDate("sampai"));
                response.setTotal_tanggal(resultSet.getLong("total_tanggal"));
            }
            logger.debug(response.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            logger.error(e.getMessage());
        }
        return response;
    }

    /**
     *
     * @param request
     */
    @Override
    public void insert(Transaksi request) {
        logger.debug(request.toString());
        try {
            sql = "INSERT INTO paket_wisata\n"
                    + "(id_wisata, paket_wisata, harga_wisata, via_pembayaran, id_penginapan, nama_penginapan, id_pengunjung, nama_pengunjung, deskripsi_makanan_minuman, deskripsi_tambahan, dari, sampai, total_tanggal)\n"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request.getId_wisata());
            preparedStatement.setString(2, request.getPaket_wisata());
            preparedStatement.setLong(3, request.getHarga_wisata());
            preparedStatement.setString(4, request.getVia_pembayaran());
            preparedStatement.setLong(5, request.getId_penginapan());
            preparedStatement.setString(6, request.getNama_penginapan());
            preparedStatement.setLong(7, request.getId_pengunjung());
            preparedStatement.setString(8, request.getNama_pengunjung());
            preparedStatement.setString(9, request.getDeskripsi_makanan_minuman());
            preparedStatement.setString(10, request.getDeskripsi_tambahan());
            preparedStatement.setDate(11, new java.sql.Date(request.getDari().getTime()));
            preparedStatement.setDate(12, new java.sql.Date(request.getSampai().getTime()));
            long totalDate = Math.abs(request.getSampai().getTime() - request.getDari().getTime());
            request.setTotal_tanggal(totalDate);
            preparedStatement.setLong(13, request.getTotal_tanggal());
            logger.debug(preparedStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update(Transaksi request) {
        logger.debug(request.toString());
        try {
            sql = "UPDATE paket_wisata\n"
                    + "SET id_wisata=?, paket_wisata=?, harga_wisata=?, via_pembayaran=?, id_penginapan=?, nama_penginapan=?, id_pengunjung=?, nama_pengunjung=?, deskripsi_makanan_minuman=?, deskripsi_tambahan=?, dari=?, sampai=?, total_tanggal=?\n"
                    + "WHERE id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, request.getId_wisata());
            preparedStatement.setString(2, request.getPaket_wisata());
            preparedStatement.setLong(3, request.getHarga_wisata());
            preparedStatement.setString(4, request.getVia_pembayaran());
            preparedStatement.setLong(5, request.getId_penginapan());
            preparedStatement.setString(6, request.getNama_penginapan());
            preparedStatement.setLong(7, request.getId_pengunjung());
            preparedStatement.setString(8, request.getNama_pengunjung());
            preparedStatement.setString(9, request.getDeskripsi_makanan_minuman());
            preparedStatement.setString(10, request.getDeskripsi_tambahan());
            preparedStatement.setDate(11, new java.sql.Date(request.getDari().getTime()));
            preparedStatement.setDate(12, new java.sql.Date(request.getSampai().getTime()));
            long totalDate = Math.abs(request.getSampai().getTime() - request.getDari().getTime());
            request.setTotal_tanggal(totalDate);
            preparedStatement.setLong(14, request.getId());
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
            sql = "DELETE FROM paket_wisata WHERE id=?;";
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
