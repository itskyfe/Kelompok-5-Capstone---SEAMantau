/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.LaporanPenangkapanController;
import dao.LaporanDAO;
import dao.NelayanDAO;
import dao.WilayahTangkapDAO;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jnafilechooser.api.JnaFileChooser;
import model.Kapal;
import model.Laporan;
import model.Nelayan;
import model.WilayahTangkap;
import model.enums.StatusKapal;
import java.time.LocalDateTime;       
import javax.swing.Timer;          
import java.awt.event.ActionEvent;       
import java.awt.event.ActionListener; 
import model.enums.StatusLaporan;
/**
 *
 * @author rfebr
 */
public class LaporanPenangkapan extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LaporanPenangkapan.class.getName());
    private String selectedImagePath;  
    private final Nelayan nelayanLogin;
    private final LaporanPenangkapanController controller = new LaporanPenangkapanController();
    private final LaporanDAO laporanDAO = new LaporanDAO();
    private final NelayanDAO nelayanDAO = new NelayanDAO(); 
    private final WilayahTangkapDAO wilayahDAO = new WilayahTangkapDAO();
    private final Map<String, Kapal> kapalMap = new HashMap<>();
    private final Map<String, WilayahTangkap> wilayahMap = new HashMap<>();
    private final String DRIVE_FOLDER_ID = "1rAlt3osDC_xcvmY8t3XfvkqcwAwLWYmh";
    private final DateTimeFormatter clockFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");    
    private List<Laporan> currentLaporanList; 



        public LaporanPenangkapan(Nelayan nelayan) {
                initComponents();
                this.nelayanLogin = nelayan;
                lblPreview.setVisible(false);
                setLocationRelativeTo(null);
                setTitle("Laporan Penangkapan - " + nelayan.getNama());

                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String waktuSekarang = LocalDateTime.now().format(clockFormatter);
                        txtWaktuBerlabuh.setText(waktuSekarang);
                    }
                });
                timer.start();

                loadDropdowns();
                loadTableData();
            }private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) tblLaporanPenangkapan.getModel();
        model.setRowCount(0); 
        

        this.currentLaporanList = laporanDAO.findByNelayanId(nelayanLogin.getUserId());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Laporan lap : this.currentLaporanList) { 
            String waktuBerangkat = lap.getWaktuBerangkat() != null ? lap.getWaktuBerangkat().format(formatter) : "-";
            String waktuBerlabuh = lap.getWaktuBerlabuh() != null ? lap.getWaktuBerlabuh().format(formatter) : "Masih Berlayar";
            
            model.addRow(new Object[]{
                waktuBerangkat,
                lap.getNamaPelabuhan(),
                waktuBerlabuh,
                lap.getWilayah().getNamaWilayah(), 
                lap.getAlatTangkap(),
                lap.getStatusLaporan(),
                lap.getCatatan()
            });
        }
    }
    
    private void loadDropdowns() {
        drpKapal.removeAllItems();
        kapalMap.clear();
        drpKapal.addItem("Pilih Kapal (Hanya yang Aktif)");
        Nelayan nelayanFresh = nelayanDAO.findById(nelayanLogin.getUserId());
        if (nelayanFresh != null && nelayanFresh.getDaftarKapal() != null) {
            for (Kapal kapal : nelayanFresh.getDaftarKapal()) {
                if (kapal.getStatusKapal() == StatusKapal.Aktif) {
                    String namaKapalDisplay = kapal.getNamaKapal() + " (" + kapal.getNoRegistrasi() + ")";
                    drpKapal.addItem(namaKapalDisplay);
                    kapalMap.put(namaKapalDisplay, kapal); 
                }
            }
        }
        drpWilayah.removeAllItems();
        wilayahMap.clear();
        drpWilayah.addItem("Pilih Wilayah Tangkap");
        List<WilayahTangkap> wilayahList = wilayahDAO.findAll();
        for (WilayahTangkap wilayah : wilayahList) {
            String namaWilayah = wilayah.toString();  
            drpWilayah.addItem(namaWilayah);
            wilayahMap.put(namaWilayah, wilayah); 
        }
    }

    private void clearForm() {
        txtPelabuhan.setText("");
        txtAlatTangkap.setText("");
        drpKapal.setSelectedIndex(0);
        drpWilayah.setSelectedIndex(0);
        selectedImagePath = null;
        lblPreview.setVisible(false);
        lblPreview.setText("");
    }
    
    private Laporan getSelectedLaporan() {
        int selectedRow = tblLaporanPenangkapan.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih salah satu laporan dari tabel.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return this.currentLaporanList.get(selectedRow); 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPelabuhan = new javax.swing.JTextField();
        txtWaktuBerlabuh = new javax.swing.JTextField();
        txtAlatTangkap = new javax.swing.JTextField();
        btnUpload = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLaporanPenangkapan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        btnHapusLaporan = new javax.swing.JButton();
        btnTambahLaporan = new javax.swing.JButton();
        drpKapal = new javax.swing.JComboBox<>();
        drpWilayah = new javax.swing.JComboBox<>();
        btnSelesai = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblPreview = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Kapal Yang Digunakan");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 175, -1, -1));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Waktu Berangkat/Berlabuh");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Wilayah Tangkap");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 110, -1));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Jenis Alat Tangkap");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Foto Alat Tangkap");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 295, -1, -1));

        txtPelabuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPelabuhanActionPerformed(evt);
            }
        });
        getContentPane().add(txtPelabuhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 310, 30));

        txtWaktuBerlabuh.setEditable(false);
        txtWaktuBerlabuh.setBackground(new java.awt.Color(255, 255, 255));
        txtWaktuBerlabuh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWaktuBerlabuhActionPerformed(evt);
            }
        });
        getContentPane().add(txtWaktuBerlabuh, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 310, 30));
        getContentPane().add(txtAlatTangkap, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 310, 30));

        btnUpload.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnUpload.setForeground(new java.awt.Color(0, 51, 102));
        btnUpload.setText("Upload");
        btnUpload.setFocusPainted(false);
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 88, 30));

        tblLaporanPenangkapan.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tblLaporanPenangkapan.setForeground(new java.awt.Color(52, 99, 146));
        tblLaporanPenangkapan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLaporanPenangkapan.getTableHeader().setReorderingAllowed(false);
        tblLaporanPenangkapan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLaporanPenangkapanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLaporanPenangkapan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 680, 120));

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Laporan Penangkapan");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        btnKembali.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(52, 99, 146));
        btnKembali.setText("Kembali");
        btnKembali.setFocusPainted(false);
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 25, 100, 30));

        btnHapusLaporan.setBackground(new java.awt.Color(204, 0, 0));
        btnHapusLaporan.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnHapusLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusLaporan.setText("Hapus");
        btnHapusLaporan.setBorderPainted(false);
        btnHapusLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusLaporanActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapusLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(658, 290, -1, 30));

        btnTambahLaporan.setBackground(new java.awt.Color(52, 99, 146));
        btnTambahLaporan.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnTambahLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahLaporan.setText("Tambah");
        btnTambahLaporan.setBorderPainted(false);
        btnTambahLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahLaporanActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambahLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 90, 30));

        drpKapal.setForeground(new java.awt.Color(0, 51, 102));
        drpKapal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        drpKapal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drpKapalActionPerformed(evt);
            }
        });
        getContentPane().add(drpKapal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 195, 310, 30));

        drpWilayah.setForeground(new java.awt.Color(0, 51, 102));
        drpWilayah.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        drpWilayah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drpWilayahActionPerformed(evt);
            }
        });
        getContentPane().add(drpWilayah, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 310, 30));

        btnSelesai.setBackground(new java.awt.Color(0, 102, 0));
        btnSelesai.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnSelesai.setForeground(new java.awt.Color(255, 255, 255));
        btnSelesai.setText("Selesaikan");
        btnSelesai.setBorderPainted(false);
        btnSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelesaiActionPerformed(evt);
            }
        });
        getContentPane().add(btnSelesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, -1, 30));

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama Pelabuhan");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        lblPreview.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPreview.setForeground(new java.awt.Color(242, 242, 242));
        lblPreview.setText("jLabel7");
        getContentPane().add(lblPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Background Nelayan.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 720, 400));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Logo Dark.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.setTitle("Pilih Foto Alat Tangkap"); 
        fileChooser.addFilter("Gambar (*.jpg, *.png, *.jpeg)", "jpg", "png", "jpeg");
        boolean result = fileChooser.showOpenDialog(this);
        if (result) { 
            File selectedFile = fileChooser.getSelectedFile();
            
            if (selectedFile != null) {
                
                
                long fileSizeInBytes = selectedFile.length();
                long fileSizeInMB = fileSizeInBytes / (1024 * 1024);
                
                long MAX_FILE_SIZE_MB = 5; 

                if (fileSizeInMB > MAX_FILE_SIZE_MB) {
                    JOptionPane.showMessageDialog(this, 
                        "Ukuran file terlalu besar (" + fileSizeInMB + " MB).\nBatas maksimal adalah " + MAX_FILE_SIZE_MB + " MB.", 
                        "Error Ukuran File", 
                        JOptionPane.ERROR_MESSAGE);
                    

                    selectedImagePath = null;
                    lblPreview.setVisible(false);
                    return; 
                }

                selectedImagePath = selectedFile.getAbsolutePath();
                lblPreview.setText("File terpilih: " + selectedFile.getName());
                lblPreview.setVisible(true);
            }
        
        }
    }//GEN-LAST:event_btnUploadActionPerformed

    private void txtPelabuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPelabuhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPelabuhanActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
        new MenuNelayan(this.nelayanLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnHapusLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusLaporanActionPerformed
        Laporan laporan = getSelectedLaporan(); 
        if (laporan == null) return; 

        if (laporan.getStatusLaporan() != StatusLaporan.Menunggu) {
            JOptionPane.showMessageDialog(this, "Hanya laporan berstatus 'Menunggu' yang bisa dihapus.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int response = JOptionPane.showConfirmDialog(
                this, 
                "Apakah Anda yakin ingin menghapus laporan untuk kapal " + laporan.getKapal().getNamaKapal() + "?", 
                "Konfirmasi Hapus", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE
        );
        
        if (response == JOptionPane.YES_OPTION) {
            try {
                controller.hapusLaporan(laporan);
                JOptionPane.showMessageDialog(this, "Laporan berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadTableData(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus laporan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHapusLaporanActionPerformed

    private void drpKapalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drpKapalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_drpKapalActionPerformed

    private void drpWilayahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drpWilayahActionPerformed

    }//GEN-LAST:event_drpWilayahActionPerformed

    private void btnTambahLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahLaporanActionPerformed
        // TODO add your handling code here:
        String namaPelabuhan = txtPelabuhan.getText().trim();
        String alatTangkap = txtAlatTangkap.getText().trim();
        
        if (namaPelabuhan.isEmpty() || alatTangkap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Pelabuhan dan Alat Tangkap harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (drpKapal.getSelectedIndex() <= 0) { 
            JOptionPane.showMessageDialog(this, "Silakan pilih kapal yang digunakan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (drpWilayah.getSelectedIndex() <= 0) { 
            JOptionPane.showMessageDialog(this, "Silakan pilih wilayah tangkap!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (selectedImagePath == null || selectedImagePath.isBlank()) {
            JOptionPane.showMessageDialog(this, "Silakan upload foto alat tangkap!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Kapal selectedKapal = kapalMap.get((String) drpKapal.getSelectedItem());
        WilayahTangkap selectedWilayah = wilayahMap.get((String) drpWilayah.getSelectedItem());
        
        Laporan laporan = new Laporan();
        laporan.setNelayan(this.nelayanLogin); 
        laporan.setKapal(selectedKapal);         
        laporan.setWilayah(selectedWilayah);   
        laporan.setNamaPelabuhan(namaPelabuhan);
        laporan.setAlatTangkap(alatTangkap);
        
        File fotoFile = new File(selectedImagePath);
        
        try {
            controller.buatLaporanBerangkat(laporan, fotoFile, DRIVE_FOLDER_ID);
            JOptionPane.showMessageDialog(this, "Laporan keberangkatan berhasil dibuat!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            
            loadTableData();
            clearForm();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal membuat laporan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTambahLaporanActionPerformed

    private void txtWaktuBerlabuhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWaktuBerlabuhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtWaktuBerlabuhActionPerformed

    private void btnSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesaiActionPerformed
        Laporan laporan = getSelectedLaporan(); 
        if (laporan == null) return; 
        
        if (laporan.getStatusLaporan() != StatusLaporan.Berlayar) {
            JOptionPane.showMessageDialog(this, "Hanya laporan berstatus 'Berlayar' yang bisa diselesaikan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (laporan.getWaktuBerlabuh() != null) {
            JOptionPane.showMessageDialog(this, "Laporan ini sudah diselesaikan (waktu berlabuh sudah tercatat).", "Info", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }
        
        int response = JOptionPane.showConfirmDialog(
                this, 
                "Apakah Anda yakin ingin menyelesaikan pelayaran untuk kapal " + laporan.getKapal().getNamaKapal() + "?\nStatus akan diubah menjadi 'Menunggu Verifikasi'.", 
                "Konfirmasi Selesai Berlayar", 
                JOptionPane.YES_NO_OPTION
        );
        
        if (response == JOptionPane.YES_OPTION) {
            try {
                controller.selesaikanPelayaran(laporan);
                JOptionPane.showMessageDialog(this, "Pelayaran berhasil diselesaikan.\nLaporan menunggu verifikasi.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadTableData(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal menyelesaikan pelayaran: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSelesaiActionPerformed

    private void tblLaporanPenangkapanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLaporanPenangkapanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblLaporanPenangkapanMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapusLaporan;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSelesai;
    private javax.swing.JButton btnTambahLaporan;
    private javax.swing.JButton btnUpload;
    private javax.swing.JComboBox<String> drpKapal;
    private javax.swing.JComboBox<String> drpWilayah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPreview;
    private javax.swing.JTable tblLaporanPenangkapan;
    private javax.swing.JTextField txtAlatTangkap;
    private javax.swing.JTextField txtPelabuhan;
    private javax.swing.JTextField txtWaktuBerlabuh;
    // End of variables declaration//GEN-END:variables
}
