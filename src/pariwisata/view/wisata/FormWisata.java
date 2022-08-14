package pariwisata.view.wisata;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pariwisata.model.penginapan.Penginapan;
import pariwisata.model.penginapan.PenginapanJdbc;
import pariwisata.model.penginapan.PenginapanJdbcImplement;
import pariwisata.model.transport.Transportasi;
import pariwisata.model.transport.TransportasiJdbc;
import pariwisata.model.transport.TransportasiJdbcImplement;
import pariwisata.model.wisata.Wisata;
import pariwisata.model.wisata.WisataJdbc;
import pariwisata.model.wisata.WisataJdbcImplement;
import pariwisata.view.menu.FormMenu;

public class FormWisata extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    private final WisataJdbc wisataJdbc;
    private final PenginapanJdbc penginapanJdbc;
    private final TransportasiJdbc transportasiJdbc;
    private Boolean clickTable;
    private DefaultTableModel defaultTableModel;

    public FormWisata() {
        initComponents();
        wisataJdbc = new WisataJdbcImplement();
        penginapanJdbc = new PenginapanJdbcImplement();
        transportasiJdbc = new TransportasiJdbcImplement();
        initTable();
        loadTable();
        loadComboBoxPenginapan();
        loadComboBoxTransportasi();
    }

    private void initTable() {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("No");
        defaultTableModel.addColumn("Paket");
        defaultTableModel.addColumn("Harga");
        defaultTableModel.addColumn("ID Penginapan");
        defaultTableModel.addColumn("Penginapan");
        defaultTableModel.addColumn("ID Transportasi");
        defaultTableModel.addColumn("Transportasi");
        defaultTableModel.addColumn("Makanan");
        defaultTableModel.addColumn("Tambahan");
        tblWisata.setModel(defaultTableModel);
    }

    private void loadTable() {
        defaultTableModel.getDataVector().removeAllElements();
        defaultTableModel.fireTableDataChanged();
        List<Wisata> responses = wisataJdbc.selectAll();
        if (responses != null) {
            Object[] objects = new Object[10];
            for (Wisata response : responses) {
                objects[0] = response.getId();
                objects[1] = response.getPaket();
                objects[2] = response.getHarga();
                objects[3] = response.getId_penginapan();
                objects[4] = response.getNama_penginapan();
                objects[5] = response.getId_transportasi();
                objects[6] = response.getNama_transportasi();
                objects[7] = response.getDeskripsi_makanan_minuman();
                objects[8] = response.getDeskripsi_tambahan();
                defaultTableModel.addRow(objects);
            }
            clickTable = false;
        }
    }

    private void loadComboBoxPenginapan() {
        List<Penginapan> responses = penginapanJdbc.selectAll();
        for (Penginapan response : responses) {
            cbxIdPenginapan.addItem(String.valueOf(response.getId()));
        }
    }

    private void loadComboBoxTransportasi() {
        List<Transportasi> responses = transportasiJdbc.selectAll();
        for (Transportasi response : responses) {
            cbxIdTransportasi.addItem(String.valueOf(response.getId()));
        }
    }

    private void loadTextPenginapan() {
        Penginapan response = penginapanJdbc.select(Long.parseLong(cbxIdPenginapan.getSelectedItem().toString()));
        txtNmPenginapan.setText(response.getNama());
    }

    private void loadTextTransportasi() {
        Transportasi response = transportasiJdbc.select(Long.parseLong(cbxIdTransportasi.getSelectedItem().toString()));
        txtNmTransportasi.setText(response.getNama());
    }

    private void clickTable() {
        Wisata response = wisataJdbc.select(Long.parseLong(defaultTableModel.getValueAt(tblWisata.getSelectedRow(), 0).toString()));

        txtPktWisata.setText(response.getPaket());
        txtHrgWisata.setText(response.getHarga().toString());
        cbxIdPenginapan.setSelectedItem(response.getId_penginapan());
        txtNmPenginapan.setText(response.getNama_penginapan());
        cbxIdTransportasi.setSelectedItem(response.getId_transportasi());
        txtNmTransportasi.setText(response.getNama_transportasi());
        txtMenuMakanan.setText(response.getDeskripsi_makanan_minuman());
        txtMenuTambahan.setText(response.getDeskripsi_tambahan());
        clickTable = true;
    }

    private void empty() {
        txtPktWisata.setText("");
        txtHrgWisata.setText("");
        cbxIdPenginapan.setSelectedIndex(0);
        txtNmPenginapan.setText("");
        cbxIdTransportasi.setSelectedIndex(0);
        txtNmTransportasi.setText("");
        txtMenuMakanan.setText("");
        txtMenuTambahan.setText("");
    }

    private void performSave() {
        if (!txtPktWisata.getText().isEmpty() && !txtHrgWisata.getText().isEmpty()) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to save new data ?", "Info", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                Wisata request = new Wisata();
                request.setId(0L);
                request.setPaket(txtPktWisata.getText());
                request.setHarga(Long.parseLong(txtHrgWisata.getText()));
                request.setId_penginapan(Long.parseLong(cbxIdPenginapan.getSelectedItem().toString()));
                request.setNama_penginapan(txtNmPenginapan.getText());
                request.setId_transportasi(Long.parseLong(cbxIdTransportasi.getSelectedItem().toString()));
                request.setNama_transportasi(txtNmTransportasi.getText());
                request.setDeskripsi_makanan_minuman(txtMenuMakanan.getText());
                request.setDeskripsi_tambahan(txtMenuTambahan.getText());
                wisataJdbc.insert(request);
                loadTable();
                empty();
                JOptionPane.showMessageDialog(null, "Successfully save data", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data not empty", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void performUpdate() {
        if (clickTable) {
            if (!txtPktWisata.getText().isEmpty() && !txtHrgWisata.getText().isEmpty()) {
                if (JOptionPane.showConfirmDialog(null, "Do you want to update data by id " + defaultTableModel.getValueAt(tblWisata.getSelectedRow(), 0).toString() + " ?", "Warning", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    Wisata request = new Wisata();
                    request.setId(Long.parseLong(defaultTableModel.getValueAt(tblWisata.getSelectedRow(), 0).toString()));
                    request.setPaket(txtPktWisata.getText());
                    request.setHarga(Long.parseLong(txtHrgWisata.getText()));
                    request.setId_penginapan(Long.parseLong(cbxIdPenginapan.getSelectedItem().toString()));
                    request.setNama_penginapan(txtPktWisata.getText());
                    request.setId_transportasi(Long.parseLong(cbxIdTransportasi.getSelectedItem().toString()));
                    request.setNama_transportasi(txtNmTransportasi.getText());
                    request.setDeskripsi_makanan_minuman(txtMenuMakanan.getText());
                    request.setDeskripsi_tambahan(txtMenuTambahan.getText());
                    wisataJdbc.update(request);
                    loadTable();
                    empty();
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
            if (JOptionPane.showConfirmDialog(null, "Do you want to delete data by id " + defaultTableModel.getValueAt(tblWisata.getSelectedRow(), 0).toString() + " ?", "Warning", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                wisataJdbc.delete(Long.parseLong(defaultTableModel.getValueAt(tblWisata.getSelectedRow(), 0).toString()));
                loadTable();
                empty();
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
        jLabel4 = new javax.swing.JLabel();
        txtPktWisata = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNmPenginapan = new javax.swing.JTextField();
        btnInsert = new javax.swing.JButton();
        txtNmTransportasi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblWisata = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtHrgWisata = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbxIdPenginapan = new javax.swing.JComboBox<>();
        cbxIdTransportasi = new javax.swing.JComboBox<>();
        txtMenuMakanan = new javax.swing.JTextField();
        txtMenuTambahan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Wisata");

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
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pariwisata/img/tour-guide.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(102, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Paket Wisata                   :");

        txtPktWisata.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtPktWisata.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Harga Wisata                  :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Id Penginapan    :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Nama Penginapan :");

        txtNmPenginapan.setEditable(false);
        txtNmPenginapan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtNmPenginapan.setForeground(new java.awt.Color(0, 0, 0));

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

        txtNmTransportasi.setEditable(false);
        txtNmTransportasi.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtNmTransportasi.setForeground(new java.awt.Color(0, 0, 0));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Nama Transportasi :");

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

        btnClear.setBackground(new java.awt.Color(102, 255, 102));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(0, 0, 0));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pariwisata/img/close.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        tblWisata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Paket", "Harga", "Id Penginapan", "Nm Penginapan", "Id Transport", "Nm Transport", "Paket Menu", "Add Paket Menu"
            }
        ));
        tblWisata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblWisataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblWisata);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Id Transportasi    :");

        txtHrgWisata.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtHrgWisata.setForeground(new java.awt.Color(0, 0, 0));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Paket Menu          :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Tambah Paket Menu :");

        cbxIdPenginapan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxIdPenginapanActionPerformed(evt);
            }
        });

        cbxIdTransportasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxIdTransportasiActionPerformed(evt);
            }
        });

        txtMenuMakanan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtMenuMakanan.setForeground(new java.awt.Color(0, 0, 0));

        txtMenuTambahan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtMenuTambahan.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtPktWisata)
                    .addComponent(txtNmPenginapan)
                    .addComponent(txtNmTransportasi)
                    .addComponent(txtHrgWisata, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxIdPenginapan, 0, 193, Short.MAX_VALUE)
                    .addComponent(cbxIdTransportasi, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMenuMakanan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMenuTambahan, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPktWisata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtHrgWisata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbxIdPenginapan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtNmPenginapan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbxIdTransportasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtNmTransportasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtMenuMakanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtMenuTambahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(448, 448, 448))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        performUpdate();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        performDelete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        empty();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblWisataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblWisataMouseClicked
        clickTable();
    }//GEN-LAST:event_tblWisataMouseClicked

    private void cbxIdPenginapanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxIdPenginapanActionPerformed
        loadTextPenginapan();
    }//GEN-LAST:event_cbxIdPenginapanActionPerformed

    private void cbxIdTransportasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxIdTransportasiActionPerformed
        loadTextTransportasi();
    }//GEN-LAST:event_cbxIdTransportasiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       new FormMenu().setVisible(true);
       dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormWisata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormWisata().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbxIdPenginapan;
    private javax.swing.JComboBox<String> cbxIdTransportasi;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblWisata;
    private javax.swing.JTextField txtHrgWisata;
    private javax.swing.JTextField txtMenuMakanan;
    private javax.swing.JTextField txtMenuTambahan;
    private javax.swing.JTextField txtNmPenginapan;
    private javax.swing.JTextField txtNmTransportasi;
    private javax.swing.JTextField txtPktWisata;
    // End of variables declaration//GEN-END:variables

}
