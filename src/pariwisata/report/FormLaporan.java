package pariwisata.report;

import Koneksi.Conn;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.Logger;
import pariwisata.model.admin.Admin;
import pariwisata.view.menu.FormMenu;

public class FormLaporan extends javax.swing.JFrame {

    private static Connection connection;
    private static final Logger logger = Logger.getLogger(FormLaporan.class);

    public FormLaporan() {
        initComponents();
        connection = Conn.getConnection();
        if (Admin.userLogin.equals("Bagian Keuangan")) {
            btnLNPemesanan.setEnabled(false);
            btnLPVTransfer.setEnabled(false);
            btnLWisata.setEnabled(false);
            btnLPengunjung.setEnabled(false);
            btnLPengunjung.setEnabled(false);
        }
    }

    private void printPemesanan() {
        try {
            HashMap parameter = new HashMap();
            parameter.put("Logo", "src\\pariwisata\\report\\pulaupari.jpeg");
            InputStream file = getClass().getResourceAsStream("/pariwisata/report/Nota Pemesanan.jrxml");
            JasperDesign JasperDesign = JRXmlLoader.load(file);
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            @SuppressWarnings("unchecked")
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parameter, connection);
            JasperViewer.viewReport(JasperPrint, false);
        } catch (JRException e) {
        }
    }

    private void printWisata() {
        try {
            HashMap parameter = new HashMap();
            parameter.put("dari", jDateFrom.getDate());
            parameter.put("sampai", jDateTo.getDate());
            parameter.put("Logo", "src\\pariwisata\\report\\pulaupari.jpeg");
            InputStream file = getClass().getResourceAsStream("/pariwisata/report/Wisata.jrxml");
            JasperDesign JasperDesign = JRXmlLoader.load(file);
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parameter, connection);
            JasperViewer.viewReport(JasperPrint, false);
        } catch (JRException e) {
            System.out.println(e);
        }
    }

    private void printPengunjung() {
        try {
            HashMap parameter = new HashMap();
            parameter.put("Logo", "src\\pariwisata\\report\\pulaupari.jpeg");
            InputStream file = getClass().getResourceAsStream("/pariwisata/report/Pengunjung.jrxml");
            JasperDesign JasperDesign = JRXmlLoader.load(file);
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parameter, connection);
            JasperViewer.viewReport(JasperPrint, false);
        } catch (JRException e) {
            System.out.println(e);
        }
    }

    private void printTransfer() {
        try {
            HashMap parameter = new HashMap();
            parameter.put("Logo", "src\\pariwisata\\report\\pulaupari.jpeg");
            InputStream file = getClass().getResourceAsStream("/pariwisata/report/Transfer.jrxml");
            JasperDesign JasperDesign = JRXmlLoader.load(file);
            JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
            JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parameter, connection);
            JasperViewer.viewReport(JasperPrint, false);
        } catch (JRException e) {
            System.out.println(e);
        }
    }

    private void printKeuangan() {
        if (jDateFrom.getDate() != null && jDateTo.getDate() != null) {
            try {
                HashMap parameter = new HashMap();
                parameter.put("dari", jDateFrom.getDate());
                parameter.put("sampai", jDateTo.getDate());
                parameter.put("Logo", "src\\pariwisata\\report\\pulaupari.jpeg");
                InputStream file = getClass().getResourceAsStream("/pariwisata/report/Keuangan.jrxml");
                JasperDesign JasperDesign = JRXmlLoader.load(file);
                JasperReport JasperReport = JasperCompileManager.compileReport(JasperDesign);
                JasperPrint JasperPrint = JasperFillManager.fillReport(JasperReport, parameter, connection);
                JasperViewer.viewReport(JasperPrint, false);
            } catch (JRException e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Date tidak boleh kosong", "Success", JOptionPane.INFORMATION_MESSAGE);
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
        btnLNPemesanan = new javax.swing.JButton();
        btnLWisata = new javax.swing.JButton();
        btnLPVTransfer = new javax.swing.JButton();
        btnLPengunjung = new javax.swing.JButton();
        btnLKeuangan = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jDateFrom = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jDateTo = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Laporan");

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
                .addGap(244, 244, 244)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 8, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pariwisata/img/report.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(102, 255, 255));

        btnLNPemesanan.setBackground(new java.awt.Color(102, 255, 102));
        btnLNPemesanan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLNPemesanan.setForeground(new java.awt.Color(0, 0, 0));
        btnLNPemesanan.setText("Laporan Nota Pemesanan");
        btnLNPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLNPemesananActionPerformed(evt);
            }
        });

        btnLWisata.setBackground(new java.awt.Color(102, 255, 102));
        btnLWisata.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLWisata.setForeground(new java.awt.Color(0, 0, 0));
        btnLWisata.setText("Laporan Wisata");
        btnLWisata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLWisataActionPerformed(evt);
            }
        });

        btnLPVTransfer.setBackground(new java.awt.Color(102, 255, 102));
        btnLPVTransfer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLPVTransfer.setForeground(new java.awt.Color(0, 0, 0));
        btnLPVTransfer.setText("Laporan Pembayaran Via Transfer");
        btnLPVTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLPVTransferActionPerformed(evt);
            }
        });

        btnLPengunjung.setBackground(new java.awt.Color(102, 255, 102));
        btnLPengunjung.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLPengunjung.setForeground(new java.awt.Color(0, 0, 0));
        btnLPengunjung.setText("Laporan Pengunjung");
        btnLPengunjung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLPengunjungActionPerformed(evt);
            }
        });

        btnLKeuangan.setBackground(new java.awt.Color(102, 255, 102));
        btnLKeuangan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLKeuangan.setForeground(new java.awt.Color(0, 0, 0));
        btnLKeuangan.setText("Laporan Keuangan");
        btnLKeuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLKeuanganActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Dari");

        jDateFrom.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Sampai");

        jDateTo.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                            .addComponent(jDateTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnLNPemesanan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLPVTransfer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLPengunjung, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLKeuangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(btnLWisata, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLPVTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLNPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLPengunjung, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLWisata, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(371, 371, 371))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLNPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLNPemesananActionPerformed
        printPemesanan();
    }//GEN-LAST:event_btnLNPemesananActionPerformed

    private void btnLWisataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLWisataActionPerformed
        printWisata();
    }//GEN-LAST:event_btnLWisataActionPerformed

    private void btnLPVTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLPVTransferActionPerformed
        printTransfer();
    }//GEN-LAST:event_btnLPVTransferActionPerformed

    private void btnLPengunjungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLPengunjungActionPerformed
        printPengunjung();
    }//GEN-LAST:event_btnLPengunjungActionPerformed

    private void btnLKeuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLKeuanganActionPerformed
        printKeuangan();
    }//GEN-LAST:event_btnLKeuanganActionPerformed

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
            java.util.logging.Logger.getLogger(FormLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLaporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLKeuangan;
    private javax.swing.JButton btnLNPemesanan;
    private javax.swing.JButton btnLPVTransfer;
    private javax.swing.JButton btnLPengunjung;
    private javax.swing.JButton btnLWisata;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateFrom;
    private com.toedter.calendar.JDateChooser jDateTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
