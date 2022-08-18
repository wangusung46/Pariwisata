package pariwisata.view.transaksi;

import Koneksi.Conn;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import pariwisata.model.pengunjung.Pengunjung;
import pariwisata.model.pengunjung.PengunjungJdbc;
import pariwisata.model.pengunjung.PengunjungJdbcImplement;
import pariwisata.model.transaksi.Transaksi;
import pariwisata.model.transaksi.TransaksiJdbc;
import pariwisata.model.transaksi.TransaksiJdbcImplement;
import pariwisata.model.wisata.Wisata;
import pariwisata.model.wisata.WisataJdbc;
import pariwisata.model.wisata.WisataJdbcImplement;
import pariwisata.view.menu.FormMenu;

public class FormTransaksi extends javax.swing.JFrame {

    private final TransaksiJdbc transaksiJdbc;
    private final WisataJdbc wisataJdbc;
    private final PengunjungJdbc pengunjungJdbc;
    private Boolean clickTable;
    private DefaultTableModel defaultTableModel;
    private static Connection connection;

    public FormTransaksi() {
        initComponents();
        connection = Conn.getConnection();
        transaksiJdbc = new TransaksiJdbcImplement();
        wisataJdbc = new WisataJdbcImplement();
        pengunjungJdbc = new PengunjungJdbcImplement();
        initTable();
        loadTable();
        loadComboBoxWisata();
        loadComboBoxPengunjung();
        GetId();
    }

    private void GetId() {
        List<Transaksi> responses = transaksiJdbc.selectAll();
        if (responses != null) {
            for (Transaksi response : responses) {
                cbxIdPrint.addItem(response.getId().toString());
            }
        }
    }

    private void printInvoice() {
        try {
            HashMap parameter = new HashMap();
            parameter.put("id", cbxIdPrint.getSelectedItem());
            parameter.put("Logo", "src\\pariwisata\\report\\pulaupari.jpeg");
            InputStream file = getClass().getResourceAsStream("/pariwisata/report/Invoice.jrxml");
            JasperDesign JasperDesign = JRXmlLoader.load(file);
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parameter, connection);
            JasperViewer.viewReport(JasperPrint, false);
        } catch (JRException e) {
            System.out.println(e);
        }

    }

    private void loadComboBoxWisata() {
        List<Wisata> responses = wisataJdbc.selectAll();
        for (Wisata response : responses) {
            cbxIdWisata.addItem(String.valueOf(response.getId()));
        }
    }

    private void loadComboBoxPengunjung() {
        List<Pengunjung> responses = pengunjungJdbc.selectAll();
        for (Pengunjung response : responses) {
            cbxIdPengunjung.addItem(String.valueOf(response.getId()));
        }
    }

    private void loadTextWisata() {
        Wisata response = wisataJdbc.select(Long.parseLong(cbxIdWisata.getSelectedItem().toString()));
        txtPaketWisata.setText(response.getPaket());
        txtHargaWisata.setText(response.getHarga().toString());
        txtidPenginapan.setText(response.getId_penginapan().toString());
        txtNamaPenginapan.setText(response.getNama_penginapan());
        txtDeskrispsiMakanan.setText(response.getDeskripsi_makanan_minuman());
        txtDeskripsiTambahan.setText(response.getDeskripsi_tambahan());
    }

    private void loadTextPengunjung() {
        Pengunjung response = pengunjungJdbc.select(Long.parseLong(cbxIdPengunjung.getSelectedItem().toString()));
        txtNamaPengunjung.setText(response.getNama());
    }

    private void initTable() {
        // id, id_wisata, paket_wisata, harga_wisata, via_pembayaran, id_penginapan, 
        // nama_penginapan, id_pengunjung, nama_pengunjung, deskripsi_makanan_minuman, 
        // deskripsi_tambahan, dari, sampai, total_tanggal
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("No");
        defaultTableModel.addColumn("ID Wisata");
        defaultTableModel.addColumn("Paket Wisata");
        defaultTableModel.addColumn("Harga Wisata");
        defaultTableModel.addColumn("Via Pembayaran");
        defaultTableModel.addColumn("ID Penginapan");
        defaultTableModel.addColumn("Nama Penginapan");
        defaultTableModel.addColumn("ID Pengunjung");
        defaultTableModel.addColumn("Nama Pengunjung");
        defaultTableModel.addColumn("Makanan");
        defaultTableModel.addColumn("Tambahan");
        defaultTableModel.addColumn("Dari Tanggal");
        defaultTableModel.addColumn("Sampai Tanggal");
        tblTransaksi.setModel(defaultTableModel);
    }

    private void loadTable() {
        defaultTableModel.getDataVector().removeAllElements();
        defaultTableModel.fireTableDataChanged();
        List<Transaksi> responses = transaksiJdbc.selectAll();
        if (responses != null) {
            Object[] objects = new Object[14];
            for (Transaksi response : responses) {
                objects[0] = response.getId();
                objects[1] = response.getId_wisata();
                objects[2] = response.getPaket_wisata();
                objects[3] = response.getHarga_wisata();
                objects[4] = response.getVia_pembayaran();
                objects[5] = response.getId_penginapan();
                objects[6] = response.getNama_penginapan();
                objects[7] = response.getId_pengunjung();
                objects[8] = response.getNama_pengunjung();
                objects[9] = response.getDeskripsi_makanan_minuman();
                objects[10] = response.getDeskripsi_tambahan();
                objects[11] = response.getDari();
                objects[12] = response.getSampai();
                defaultTableModel.addRow(objects);
            }
            clickTable = false;
        }
    }

    private void clickTable() {
        Transaksi response = transaksiJdbc.select(Long.parseLong(defaultTableModel.getValueAt(tblTransaksi.getSelectedRow(), 0).toString()));

        cbxIdWisata.setSelectedItem(response.getId_wisata());
        txtPaketWisata.setText(response.getPaket_wisata());
        txtHargaWisata.setText(response.getHarga_wisata().toString());
        cbxViaPembayaran.setSelectedItem(response.getVia_pembayaran());
        txtidPenginapan.setText(response.getId_penginapan().toString());
        txtNamaPenginapan.setText(response.getNama_penginapan());
        cbxIdPengunjung.setSelectedItem(response.getId_pengunjung());
        txtNamaPengunjung.setText(response.getNama_pengunjung());
        txtDeskrispsiMakanan.setText(response.getDeskripsi_makanan_minuman());
        txtDeskripsiTambahan.setText(response.getDeskripsi_tambahan());
        jDateDari.setDate(response.getDari());
        jDateSampai.setDate(response.getSampai());
        clickTable = true;
    }
//
//    private void empty() {
//        cbxIdWisata.setSelectedIndex(0);
//        txtPaketWisata.setText("");
//        txtHargaWisata.setText("");
//        cbxViaPembayaran.setSelectedIndex(0);
//        txtidPenginapan.setText("");
//        txtNamaPenginapan.setText("");
//        cbxIdPengunjung.setSelectedIndex(0);
//        txtNamaPengunjung.setText("");
//        txtDeskrispsiMakanan.setText("");
//        txtDeskripsiTambahan.setText("");
//    }

    private void performSave() {
        if (jDateDari.getDate() != null && jDateSampai.getDate() != null) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to save new data ?", "Info", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                Transaksi request = new Transaksi();
                request.setPaket_wisata(txtPaketWisata.getText());
                request.setId_wisata(Long.parseLong(cbxIdWisata.getSelectedItem().toString()));
                request.setHarga_wisata(Long.parseLong(txtHargaWisata.getText()));
                request.setId_penginapan(Long.parseLong(txtidPenginapan.getText()));
                request.setVia_pembayaran(cbxViaPembayaran.getSelectedItem().toString());
                request.setId_penginapan(Long.parseLong(txtidPenginapan.getText()));
                request.setNama_penginapan(txtNamaPenginapan.getText());
                request.setId_pengunjung(Long.parseLong(cbxIdPengunjung.getSelectedItem().toString()));
                request.setNama_pengunjung(txtNamaPengunjung.getText());
                request.setDeskripsi_makanan_minuman(txtDeskrispsiMakanan.getText());
                request.setDeskripsi_tambahan(txtDeskripsiTambahan.getText());
                request.setDari(jDateDari.getDate());
                request.setSampai(jDateSampai.getDate());
                request.setTotal_tanggal((jDateSampai.getDate().getTime() - jDateDari.getDate().getTime()) / (24 * 60 * 60 * 1000));
                transaksiJdbc.insert(request);
                loadTable();
                GetId();
                JOptionPane.showMessageDialog(null, "Successfully save data", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data not empty", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void performUpdate() {
        if (clickTable) {
            if (jDateDari.getDate() != null && jDateSampai.getDate() != null) {
                if (JOptionPane.showConfirmDialog(null, "Do you want to update data by id " + defaultTableModel.getValueAt(tblTransaksi.getSelectedRow(), 0).toString() + " ?", "Warning", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    Transaksi request = new Transaksi();
                    request.setId(Long.parseLong(defaultTableModel.getValueAt(tblTransaksi.getSelectedRow(), 0).toString()));
                    request.setPaket_wisata(txtPaketWisata.getText());
                    request.setId_wisata(Long.parseLong(cbxIdWisata.getSelectedItem().toString()));
                    request.setHarga_wisata(Long.parseLong(txtHargaWisata.getText()));
                    request.setId_penginapan(Long.parseLong(txtidPenginapan.getText()));
                    request.setVia_pembayaran(cbxViaPembayaran.getSelectedItem().toString());
                    request.setId_penginapan(Long.parseLong(txtidPenginapan.getText()));
                    request.setNama_penginapan(txtNamaPenginapan.getText());
                    request.setId_pengunjung(Long.parseLong(cbxIdPengunjung.getSelectedItem().toString()));
                    request.setNama_pengunjung(txtNamaPengunjung.getText());
                    request.setDeskripsi_makanan_minuman(txtDeskrispsiMakanan.getText());
                    request.setDeskripsi_tambahan(txtDeskripsiTambahan.getText());
                    request.setDari(jDateDari.getDate());
                    request.setSampai(jDateSampai.getDate());
                    request.setTotal_tanggal((jDateSampai.getDate().getTime() - jDateDari.getDate().getTime()) / (24 * 60 * 60 * 1000));
                    transaksiJdbc.update(request);
                    loadTable();
//                    empty();
                    JOptionPane.showMessageDialog(null, "Successfully update data", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data not empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Delete or edit must click table", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void performDelete() {
        if (clickTable) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to delete data by id " + defaultTableModel.getValueAt(tblTransaksi.getSelectedRow(), 0).toString() + " ?", "Warning", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                transaksiJdbc.delete(Long.parseLong(defaultTableModel.getValueAt(tblTransaksi.getSelectedRow(), 0).toString()));
                loadTable();
//                empty();
                JOptionPane.showMessageDialog(null, "Successfully delete data", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Delete or edit must click table", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPaketWisata = new javax.swing.JTextField();
        btnInsert = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        cbxIdWisata = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbxViaPembayaran = new javax.swing.JComboBox<>();
        txtHargaWisata = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNamaPenginapan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbxIdPengunjung = new javax.swing.JComboBox<>();
        txtNamaPengunjung = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtDeskrispsiMakanan = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtDeskripsiTambahan = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateDari = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jDateSampai = new com.toedter.calendar.JDateChooser();
        txtidPenginapan = new javax.swing.JTextField();
        btnClear1 = new javax.swing.JButton();
        cbxIdPrint = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FormTransaksi");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Transaksi");

        jButton1.setBackground(new java.awt.Color(255, 0, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pariwisata/img/payment-method.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(102, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Id Wisata                   :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Paket Wisata             :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Harga Wisata            :");

        txtPaketWisata.setEditable(false);
        txtPaketWisata.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtPaketWisata.setForeground(new java.awt.Color(0, 0, 0));

        btnInsert.setBackground(new java.awt.Color(102, 255, 102));
        btnInsert.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnInsert.setForeground(new java.awt.Color(0, 0, 0));
        btnInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pariwisata/img/insert.png"))); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Via Pembayaran       :");

        btnUpdate.setBackground(new java.awt.Color(102, 255, 102));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pariwisata/img/update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(102, 255, 102));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pariwisata/img/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Id Wisata", "Paket Wisata", "Harga Wisata", "Via Pemabayara", "Id Penginapan", "Nama Penginapan", "Id User", "Nama User", "Deskripsi Makanan", "Deskripsi Tambahan"
            }
        ));
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransaksi);

        cbxIdWisata.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbxIdWisata.setForeground(new java.awt.Color(0, 0, 0));
        cbxIdWisata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxIdWisataActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Id Penginapan          :");

        cbxViaPembayaran.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbxViaPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        cbxViaPembayaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Transfer" }));

        txtHargaWisata.setEditable(false);
        txtHargaWisata.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtHargaWisata.setForeground(new java.awt.Color(0, 0, 0));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Nama Penginapan    :");

        txtNamaPenginapan.setEditable(false);
        txtNamaPenginapan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtNamaPenginapan.setForeground(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Id Pengunjung         :");

        cbxIdPengunjung.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbxIdPengunjung.setForeground(new java.awt.Color(0, 0, 0));
        cbxIdPengunjung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxIdPengunjungActionPerformed(evt);
            }
        });

        txtNamaPengunjung.setEditable(false);
        txtNamaPengunjung.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtNamaPengunjung.setForeground(new java.awt.Color(0, 0, 0));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Nama Pengunjung   :");

        txtDeskrispsiMakanan.setEditable(false);
        txtDeskrispsiMakanan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtDeskrispsiMakanan.setForeground(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Deskrispsi Tambahan:");

        txtDeskripsiTambahan.setEditable(false);
        txtDeskripsiTambahan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtDeskripsiTambahan.setForeground(new java.awt.Color(0, 0, 0));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Deskrispsi Makanan :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Dari      :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Sampai : ");

        txtidPenginapan.setEditable(false);
        txtidPenginapan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtidPenginapan.setForeground(new java.awt.Color(0, 0, 0));

        btnClear1.setBackground(new java.awt.Color(102, 255, 102));
        btnClear1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnClear1.setForeground(new java.awt.Color(0, 0, 0));
        btnClear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pariwisata/img/printer.png"))); // NOI18N
        btnClear1.setText("Print");
        btnClear1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClear1ActionPerformed(evt);
            }
        });

        cbxIdPrint.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbxIdPrint.setForeground(new java.awt.Color(0, 0, 0));
        cbxIdPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxIdPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxIdPengunjung, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaPengunjung, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaPenginapan, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidPenginapan, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxViaPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHargaWisata, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPaketWisata, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxIdWisata, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDeskrispsiMakanan, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDeskripsiTambahan, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateDari, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateSampai, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxIdPrint, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClear1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateDari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxIdWisata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxIdPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPaketWisata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateSampai, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHargaWisata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbxViaPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtidPenginapan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txtNamaPenginapan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxIdPengunjung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtNamaPengunjung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtDeskrispsiMakanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtDeskripsiTambahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(602, 602, 602)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        performSave();

    }//GEN-LAST:event_btnInsertActionPerformed

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        clickTable();

    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        performUpdate();

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        performDelete();

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cbxIdWisataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxIdWisataActionPerformed
        loadTextWisata();
    }//GEN-LAST:event_cbxIdWisataActionPerformed

    private void cbxIdPengunjungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxIdPengunjungActionPerformed
        loadTextPengunjung();
    }//GEN-LAST:event_cbxIdPengunjungActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new FormMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClear1ActionPerformed
        printInvoice();
    }//GEN-LAST:event_btnClear1ActionPerformed

    private void cbxIdPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxIdPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxIdPrintActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbxIdPengunjung;
    private javax.swing.JComboBox<String> cbxIdPrint;
    private javax.swing.JComboBox<String> cbxIdWisata;
    private javax.swing.JComboBox<String> cbxViaPembayaran;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateDari;
    private com.toedter.calendar.JDateChooser jDateSampai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtDeskripsiTambahan;
    private javax.swing.JTextField txtDeskrispsiMakanan;
    private javax.swing.JTextField txtHargaWisata;
    private javax.swing.JTextField txtNamaPenginapan;
    private javax.swing.JTextField txtNamaPengunjung;
    private javax.swing.JTextField txtPaketWisata;
    private javax.swing.JTextField txtidPenginapan;
    // End of variables declaration//GEN-END:variables

}
