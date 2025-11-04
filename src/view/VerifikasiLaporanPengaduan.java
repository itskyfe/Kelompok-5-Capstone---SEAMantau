/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.LaporanDAO;
import dao.LaporanPengaduanDAO;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Laporan;
import model.LaporanPengaduan;
import model.Pegawai; 
import model.enums.StatusLaporan;
import model.enums.StatusPengaduan;

/**
 *
 * @author rfebr
 */
public class VerifikasiLaporanPengaduan extends javax.swing.JFrame {
    
    private final Pegawai pegawaiLogin;
    private final LaporanDAO laporanDAO = new LaporanDAO();
    private final LaporanPengaduanDAO laporanPengaduanDAO = new LaporanPengaduanDAO();
    private List<LaporanPengaduan> listPengaduan;
    private List<Laporan> listLaporan;



    public VerifikasiLaporanPengaduan(Pegawai pegawai) {
        initComponents();
        setLocationRelativeTo(null);
        this.pegawaiLogin = pegawai;
        setTitle("Verifikasi Laporan - " + this.pegawaiLogin.getNama());

        setupFilters(); 
        
        loadTblPengaduan();
        loadTblPenangkapan();
    }
private void setupFilters() {
        filterPengaduan.removeAllItems();
        filterPengaduan.addItem("Tugas Saya (Default)");
        filterPengaduan.addItem("Selesai");
        filterPengaduan.addItem("Semua Laporan");

        filterPenangkapan.removeAllItems();
        filterPenangkapan.addItem("Tugas (Menunggu/Berlayar)");
        filterPenangkapan.addItem("Selesai (Diverifikasi)");
        filterPenangkapan.addItem("Ditolak"); 
        
        filterPengaduan.addActionListener(e -> loadTblPengaduan());
        filterPenangkapan.addActionListener(e -> loadTblPenangkapan());
    }
    public void loadTblPengaduan() {
        DefaultTableModel model = (DefaultTableModel) tblPengaduan.getModel();
        model.setRowCount(0);
        String filter = (String) filterPengaduan.getSelectedItem();
        
        switch (filter) {
            case "Tugas Saya (Default)":
                this.listPengaduan = laporanPengaduanDAO.findTugasPegawai(this.pegawaiLogin.getUserId());
                break;
            case "Selesai":
                this.listPengaduan = laporanPengaduanDAO.findByStatus(StatusPengaduan.Selesai);
                break;
            case "Semua Laporan":
                this.listPengaduan = laporanPengaduanDAO.findAll();
                break;
            default:
                this.listPengaduan = laporanPengaduanDAO.findTugasPegawai(this.pegawaiLogin.getUserId());
                break;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        if (this.listPengaduan != null) { 
            for (LaporanPengaduan lap : this.listPengaduan) {
                model.addRow(new Object[]{
                    lap.getTanggalPengaduan().format(formatter),
                    lap.getJenisPengaduan(),
                    lap.getDeskripsi(),
                    lap.getTitikKoordinat(),
                    lap.getStatusPengaduan(),
                    lap.getCatatan() != null ? lap.getCatatan() : "-"
                });
            }
        }
    }
    
    public void loadTblPenangkapan() {
        DefaultTableModel model = (DefaultTableModel) tblPenangkapan.getModel();
        model.setRowCount(0);
        String filter = (String) filterPenangkapan.getSelectedItem();
        
        switch (filter) {
            case "Tugas (Menunggu/Berlayar)":
                this.listLaporan = laporanDAO.findTugasPenangkapan();
                break;
            case "Selesai (Diverifikasi)":
                this.listLaporan = laporanDAO.findByStatus(StatusLaporan.Diverifikasi);
                break;
            case "Ditolak": 
                this.listLaporan = laporanDAO.findByStatus(StatusLaporan.Ditolak);
                break;
            default:
                this.listLaporan = laporanDAO.findTugasPenangkapan();
                break;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (this.listLaporan != null) { 
            for (Laporan lap : this.listLaporan) {
                String waktuBerangkat = lap.getWaktuBerangkat() != null ? lap.getWaktuBerangkat().format(formatter) : "-";
                String waktuBerlabuh = lap.getWaktuBerlabuh() != null ? lap.getWaktuBerlabuh().format(formatter) : "Masih Berlayar";
                
                model.addRow(new Object[]{
                    waktuBerangkat,
                    lap.getNamaPelabuhan(),
                    waktuBerlabuh,
                    lap.getWilayah().getNamaWilayah(),
                    lap.getAlatTangkap(),
                    lap.getStatusLaporan(),
                    lap.getCatatan() != null ? lap.getCatatan() : "-"
                });
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPengaduan = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPenangkapan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        btnOpenPenangkapan = new javax.swing.JButton();
        btnOpenPengaduan = new javax.swing.JButton();
        filterPenangkapan = new javax.swing.JComboBox<>();
        filterPengaduan = new javax.swing.JComboBox<>();
        btnRefreshPenangkapan = new javax.swing.JButton();
        btnRefreshPengaduan = new javax.swing.JButton();
        bg = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPengaduan.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tblPengaduan.setForeground(new java.awt.Color(0, 51, 102));
        tblPengaduan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal Pengaduan", "Jenis Pengaduan", "Deskripsi", "Titik Koordinat", "Status", "Catatan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPengaduan.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblPengaduan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 700, 110));

        tblPenangkapan.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tblPenangkapan.setForeground(new java.awt.Color(0, 51, 102));
        tblPenangkapan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Waktu Berangkat", "Nama Pelabuhan", "Waktu Berlabuh", "Titik Koordinat", "Alat Tangkap", "Status", "Catatan "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPenangkapan.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblPenangkapan);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 700, 110));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Verifikasi Laporan");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Laporan Pengaduan");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Laporan Penangkapan");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        btnKembali.setBackground(new java.awt.Color(52, 99, 146));
        btnKembali.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(255, 255, 255));
        btnKembali.setText("Kembali");
        btnKembali.setBorderPainted(false);
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 100, 30));

        btnOpenPenangkapan.setBackground(new java.awt.Color(52, 99, 146));
        btnOpenPenangkapan.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnOpenPenangkapan.setForeground(new java.awt.Color(255, 255, 255));
        btnOpenPenangkapan.setText("Open");
        btnOpenPenangkapan.setBorderPainted(false);
        btnOpenPenangkapan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenPenangkapanActionPerformed(evt);
            }
        });
        getContentPane().add(btnOpenPenangkapan, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, -1, 30));

        btnOpenPengaduan.setBackground(new java.awt.Color(52, 99, 146));
        btnOpenPengaduan.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnOpenPengaduan.setForeground(new java.awt.Color(255, 255, 255));
        btnOpenPengaduan.setText("Open");
        btnOpenPengaduan.setBorderPainted(false);
        btnOpenPengaduan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenPengaduanActionPerformed(evt);
            }
        });
        getContentPane().add(btnOpenPengaduan, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, -1, 30));

        filterPenangkapan.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        filterPenangkapan.setForeground(new java.awt.Color(0, 51, 102));
        filterPenangkapan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(filterPenangkapan, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 150, 30));

        filterPengaduan.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        filterPengaduan.setForeground(new java.awt.Color(0, 51, 102));
        filterPengaduan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(filterPengaduan, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 150, 30));

        btnRefreshPenangkapan.setBackground(new java.awt.Color(52, 99, 146));
        btnRefreshPenangkapan.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnRefreshPenangkapan.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshPenangkapan.setText("Segarkan");
        btnRefreshPenangkapan.setBorderPainted(false);
        btnRefreshPenangkapan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshPenangkapanActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefreshPenangkapan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 100, 30));

        btnRefreshPengaduan.setBackground(new java.awt.Color(52, 99, 146));
        btnRefreshPengaduan.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnRefreshPengaduan.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshPengaduan.setText("Segarkan");
        btnRefreshPengaduan.setBorderPainted(false);
        btnRefreshPengaduan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshPengaduanActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefreshPengaduan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 130, 100, 30));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Background Pegawai.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 740, 400));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Logo Dark.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        new MenuPegawai(this.pegawaiLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnOpenPenangkapanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenPenangkapanActionPerformed
        int selectedRow = tblPenangkapan.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih laporan penangkapan dari tabel.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Laporan laporan = this.listLaporan.get(selectedRow);
        
        String filter = (String) filterPenangkapan.getSelectedItem();
        boolean isEditable = filter.equals("Tugas (Menunggu/Berlayar)");
        
        new OpenPenangkapan(this, this.pegawaiLogin, laporan, isEditable).setVisible(true);
    }//GEN-LAST:event_btnOpenPenangkapanActionPerformed

    private void btnOpenPengaduanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenPengaduanActionPerformed
        int selectedRow = tblPengaduan.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih laporan pengaduan dari tabel.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        LaporanPengaduan pengaduan = this.listPengaduan.get(selectedRow);
        
        String filter = (String) filterPengaduan.getSelectedItem();
        boolean isEditable = filter.equals("Tugas Saya (Default)");
        
        new OpenPengaduan(this, this.pegawaiLogin, pengaduan, isEditable).setVisible(true);
    }//GEN-LAST:event_btnOpenPengaduanActionPerformed

    private void btnRefreshPenangkapanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshPenangkapanActionPerformed
        loadTblPenangkapan();
    }//GEN-LAST:event_btnRefreshPenangkapanActionPerformed

    private void btnRefreshPengaduanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshPengaduanActionPerformed
        loadTblPengaduan();
    }//GEN-LAST:event_btnRefreshPengaduanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnOpenPenangkapan;
    private javax.swing.JButton btnOpenPengaduan;
    private javax.swing.JButton btnRefreshPenangkapan;
    private javax.swing.JButton btnRefreshPengaduan;
    private javax.swing.JComboBox<String> filterPenangkapan;
    private javax.swing.JComboBox<String> filterPengaduan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPenangkapan;
    private javax.swing.JTable tblPengaduan;
    // End of variables declaration//GEN-END:variables
}
