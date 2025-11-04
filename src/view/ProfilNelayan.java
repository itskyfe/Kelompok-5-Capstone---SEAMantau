/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.NelayanController;
import controller.UserController;
import dao.NelayanDAO;
import java.util.ArrayList;
import java.util.List;
import model.User;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Kapal;
import org.mindrot.jbcrypt.BCrypt;
import model.Nelayan;
import model.enums.StatusKapal;
/**
 *
 * @author rfebr
 */
public class ProfilNelayan extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProfilNelayan.class.getName());
    private Nelayan nelayanLogin;
    private final UserController userController = new UserController();
    private List<Kapal> currentKapalList;



        public ProfilNelayan(Nelayan nelayanLogin) {
            initComponents();
            this.nelayanLogin = nelayanLogin;
            setLocationRelativeTo(null);
            loadData();
            loadTableKapal();
        }
        
        private void loadData() {
            if (nelayanLogin != null) {
                txtNama.setText(nelayanLogin.getNama());
                txtUsername.setText(nelayanLogin.getUsername());
                txtEmail.setText(nelayanLogin.getEmail());
                txtHP.setText(nelayanLogin.getNoHp());
                txtAlamat.setText(nelayanLogin.getAlamat());
                txtNIB.setText(nelayanLogin.getNib() != null ? nelayanLogin.getNib().toString() : "");
                txtNIB.setEditable(false); 
                jPasswordField1.setText("");
            }
        }
        private void updateProfil() {
        String nama = txtNama.getText().trim();
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String noHp = txtHP.getText().trim();
        String alamat = txtAlamat.getText().trim();
        String newPassword = new String(jPasswordField1.getPassword()).trim();

        // buat salinan user sementara
        Nelayan tempNelayan = new Nelayan();
        tempNelayan.setUserId(nelayanLogin.getUserId());
        tempNelayan.setNama(nama);
        tempNelayan.setUsername(username);
        tempNelayan.setEmail(email);
        tempNelayan.setNoHp(noHp);
        tempNelayan.setAlamat(alamat);
        tempNelayan.setNib(nelayanLogin.getNib()); 
        tempNelayan.setRole(nelayanLogin.getRole()); 
        tempNelayan.setStatusNelayan(nelayanLogin.getStatusNelayan()); 
        
        if (!newPassword.isEmpty()) {
            String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            tempNelayan.setPassword(hashed);
        } else {
            tempNelayan.setPassword(nelayanLogin.getPassword());
        }

        try {
            userController.updateProfil(tempNelayan, newPassword);

            JOptionPane.showMessageDialog(this, "Profil berhasil diperbarui!");
            this.nelayanLogin = tempNelayan; 
            jPasswordField1.setText(""); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui profil: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            loadData();
        }
    }
        private Kapal getSelectedKapal() {
        int selectedRow = tblKapal.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih salah satu kapal dari tabel.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        List<Kapal> kapalYangTampil = new ArrayList<>();
        if (this.currentKapalList != null) {
            for (Kapal k : this.currentKapalList) {
                if (k.getStatusKapal() != StatusKapal.Dihapus) {
                    kapalYangTampil.add(k);
                }
            }
        }
        if (selectedRow < kapalYangTampil.size()) {
             return kapalYangTampil.get(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Error: Gagal mencocokkan data kapal.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
        
 private void loadTableKapal() { 
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"No. Registrasi", "Nama Kapal", "Jenis Kapal", "Status"}, 0 
        ) {
             @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        NelayanDAO nelayanDAO = new NelayanDAO();
        Nelayan nelayan = nelayanDAO.findById(this.nelayanLogin.getUserId());
        
        this.currentKapalList = new ArrayList<>(); 

        if (nelayan != null && nelayan.getDaftarKapal() != null) {
            for (Kapal kapal : nelayan.getDaftarKapal()) {
                if (kapal.getStatusKapal() != StatusKapal.Dihapus) { 
                    model.addRow(new Object[]{
                        kapal.getNoRegistrasi(),
                        kapal.getNamaKapal(),
                        kapal.getJenisKapal(),
                        kapal.getStatusKapal() 
                    });
                    this.currentKapalList.add(kapal); 
                }
            }
        }
        tblKapal.setModel(model);
    }

        

       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        txtHP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNIB = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblKapal = new javax.swing.JTable();
        btnHapusKapal = new javax.swing.JButton();
        btnTambahKapal = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Alamat");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, -1, -1));

        jScrollPane6.setViewportView(txtAlamat);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 300, 80));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("No. Handphone");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        txtHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHPActionPerformed(evt);
            }
        });
        getContentPane().add(txtHP, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, 300, 30));

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 300, 30));

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 40, 20));
        getContentPane().add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 280, 30));

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 60, 20));
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 280, 30));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 60, 20));

        txtNIB.setEditable(false);
        getContentPane().add(txtNIB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 280, 30));

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("NIB");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, -1));

        btnSimpan.setBackground(new java.awt.Color(52, 99, 146));
        btnSimpan.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan");
        btnSimpan.setBorderPainted(false);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 380, 130, 30));

        tblKapal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "No. Registrasi", "Nama Kapal", "Jenis Kapal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKapal.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(tblKapal);

        getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 470, 110));

        btnHapusKapal.setBackground(new java.awt.Color(204, 0, 0));
        btnHapusKapal.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnHapusKapal.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusKapal.setText("Hapus Kapal");
        btnHapusKapal.setBorderPainted(false);
        btnHapusKapal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusKapalActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapusKapal, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 130, 30));

        btnTambahKapal.setBackground(new java.awt.Color(52, 99, 146));
        btnTambahKapal.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnTambahKapal.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahKapal.setText("Tambah Kapal");
        btnTambahKapal.setBorderPainted(false);
        btnTambahKapal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahKapalActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambahKapal, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 130, 30));
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 280, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(52, 99, 146));
        jLabel8.setText("Profil Nelayan");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 15, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Logo Dark.png"))); // NOI18N
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, -1, -1));

        btnKembali.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(52, 99, 146));
        btnKembali.setText("Kembali");
        btnKembali.setFocusPainted(false);
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        jPanel2.add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 25, 90, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 80));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 810, 80));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Background Nelayan.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
        new MenuNelayan(nelayanLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnHapusKapalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusKapalActionPerformed
        Kapal kapal = getSelectedKapal();
        if (kapal == null) return; 

        int response = JOptionPane.showConfirmDialog(
                this, 
                "Apakah Anda yakin ingin menghapus kapal " + kapal.getNamaKapal() + "?\nData akan 'Dihapus' dan tidak bisa dipakai.", 
                "Konfirmasi Hapus Kapal", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE
        );
        
        if (response == JOptionPane.YES_OPTION) {
            try {
                NelayanController nelayanController = new NelayanController();
                nelayanController.hapusKapalSecaraLogis(kapal);
                
                JOptionPane.showMessageDialog(this, "Kapal " + kapal.getNamaKapal() + " berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
                loadTableKapal(); 
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus kapal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnHapusKapalActionPerformed

    private void btnTambahKapalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKapalActionPerformed
        // TODO add your handling code here:
        new TambahKapal(this.nelayanLogin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTambahKapalActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHPActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
            updateProfil();

    }//GEN-LAST:event_btnSimpanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnHapusKapal;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahKapal;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable tblKapal;
    private javax.swing.JTextPane txtAlamat;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHP;
    private javax.swing.JTextField txtNIB;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
