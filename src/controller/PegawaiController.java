package controller;
import dao.UserDAO;
import model.Pegawai;
import model.enums.Role;
import org.mindrot.jbcrypt.BCrypt;
import model.User;
import dao.KapalDAO;
import dao.NelayanDAO;
import model.Kapal;
import model.Nelayan;
import model.enums.StatusKapal;
import model.enums.StatusNelayan;
import java.util.regex.Pattern; 
import util.GoogleDriveService;

/**
 *
 * @author TUF
 */
public class PegawaiController {
    private final UserDAO userDAO = new UserDAO();
    private final NelayanDAO nelayanDAO = new NelayanDAO();
    private final KapalDAO kapalDAO = new KapalDAO();
    
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern PHONE_REGEX = Pattern.compile("^\\d{10,12}$"); 
    private static final Pattern NIP_REGEX = Pattern.compile("^\\d{18}$");   
    private static final Pattern NAME_USER_REGEX = Pattern.compile("^.{5,20}$"); 
    private static final Pattern PASS_REGEX = Pattern.compile("^.{5,20}$"); 

    private void validatePegawai(String nama, String username, String email, String noTelepon, String alamat, String nipText) throws Exception {
        if (nama.isBlank() || username.isBlank() || email.isBlank() || noTelepon.isBlank() || alamat.isBlank() || nipText.isBlank()) {
            throw new Exception("Semua field wajib diisi!");
        }
        if (!NAME_USER_REGEX.matcher(nama).matches()) {
            throw new Exception("Nama harus 5-20 karakter.");
        }
        if (!NAME_USER_REGEX.matcher(username).matches()) {
            throw new Exception("Username harus 5-20 karakter.");
        }
        if (!EMAIL_REGEX.matcher(email).matches()) {
            throw new Exception("Format email tidak valid (contoh: user@gmail.com).");
        }
        if (!PHONE_REGEX.matcher(noTelepon).matches()) {
            throw new Exception("Nomor Telepon harus angka, 10-12 digit.");
        }
        if (!NIP_REGEX.matcher(nipText).matches()) {
            throw new Exception("NIP harus angka, tepat 18 digit.");
        }
    }

 
    public void tambahPegawai(String nama, String username, String password, String email,
                              String noTelepon, String alamat, String nipText) throws Exception {
        
        validatePegawai(nama, username, email, noTelepon, alamat, nipText);
        if (password.isBlank() || !PASS_REGEX.matcher(password).matches()) {
            throw new Exception("Password harus 5-20 karakter.");
        }

        if (userDAO.findByUsername(username) != null) {
            throw new Exception("Username '" + username + "' sudah digunakan!");
        }
        if (userDAO.findByEmail(email) != null) {
            throw new Exception("Email '" + email + "' sudah digunakan!");
        }
        if (userDAO.findByNip(nipText) != null) { 
            throw new Exception("NIP '" + nipText + "' sudah digunakan!");
        }

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        Pegawai pegawai = new Pegawai();
        pegawai.setNama(nama);
        pegawai.setUsername(username);
        pegawai.setPassword(hashed);
        pegawai.setEmail(email);
        pegawai.setNoHp(noTelepon);
        pegawai.setAlamat(alamat);
        pegawai.setNip(nipText); 
        pegawai.setRole(Role.Pegawai);

        userDAO.save(pegawai);
        
    }


    public void updatePegawai(Pegawai pegawaiToUpdate, String nama, String username, String passwordBaru,
                              String email, String noTelepon, String alamat, String nipText) throws Exception {
        
        validatePegawai(nama, username, email, noTelepon, alamat, nipText);
        if (!passwordBaru.isEmpty() && !PASS_REGEX.matcher(passwordBaru).matches()) {
            throw new Exception("Password baru harus 5-20 karakter (atau biarkan kosong).");
        }

        User existingByUsername = userDAO.findByUsername(username);
        if (existingByUsername != null && !existingByUsername.getUserId().equals(pegawaiToUpdate.getUserId())) {
            throw new Exception("Username '" + username + "' sudah digunakan!");
        }

        User existingByEmail = userDAO.findByEmail(email);
        if (existingByEmail != null && !existingByEmail.getUserId().equals(pegawaiToUpdate.getUserId())) {
            throw new Exception("Email '" + email + "' sudah digunakan!");
        }

        Pegawai existingByNip = userDAO.findByNip(nipText);
        if (existingByNip != null && !existingByNip.getUserId().equals(pegawaiToUpdate.getUserId())) {
            throw new Exception("NIP '" + nipText + "' sudah digunakan!");
        }
        
        pegawaiToUpdate.setNama(nama);
        pegawaiToUpdate.setUsername(username);
        pegawaiToUpdate.setEmail(email);
        pegawaiToUpdate.setNoHp(noTelepon);
        pegawaiToUpdate.setAlamat(alamat);
        pegawaiToUpdate.setNip(nipText); 

        if (!passwordBaru.isEmpty()) {
            String hashed = BCrypt.hashpw(passwordBaru, BCrypt.gensalt());
            pegawaiToUpdate.setPassword(hashed);
        }
        
        userDAO.update(pegawaiToUpdate);
    }

    public void verifikasiAkunNelayan(Nelayan nelayan) throws Exception {
        if (nelayan.getStatusNelayan() == StatusNelayan.Nonaktif) {
            nelayan.setStatusNelayan(StatusNelayan.Aktif);
            nelayanDAO.update(nelayan);
        } else {
            throw new Exception("Akun ini sudah Aktif.");
        }
    }
    
    public void tolakAkunNelayan(Nelayan nelayan) throws Exception {
        if (nelayan.getStatusNelayan() == StatusNelayan.Nonaktif) {
            
            Nelayan nelayanFull = nelayanDAO.findById(nelayan.getUserId()); 
            
            if (nelayanFull != null && nelayanFull.getDaftarKapal() != null) {
                for (Kapal kapal : nelayanFull.getDaftarKapal()) {
                    String fotoUrl = kapal.getFotoKapal();
                    if (fotoUrl != null && !fotoUrl.isEmpty()) {
                        String fileId = GoogleDriveService.extractFileIdFromUrl(fotoUrl);
                        GoogleDriveService.deleteFile(fileId);
                    }
                }
            }
            
            nelayanDAO.demoteNelayan(nelayan);
        } else {
            throw new Exception("Hanya akun Nonaktif yang bisa ditolak/dihapus.");
        }
    }
    
    public void verifikasiKapal(Kapal kapal) throws Exception {
        if (kapal.getStatusKapal() == StatusKapal.Nonaktif) {
            kapal.setStatusKapal(StatusKapal.Aktif);
            kapalDAO.update(kapal);
        } else {
            throw new Exception("Kapal ini sudah Aktif.");
        }
    }
    
    public void tolakKapal(Kapal kapal) throws Exception {
        if (kapal.getStatusKapal() == StatusKapal.Nonaktif) {
            
            String fotoUrl = kapal.getFotoKapal();
            if (fotoUrl != null && !fotoUrl.isEmpty()) {
                String fileId = GoogleDriveService.extractFileIdFromUrl(fotoUrl);
                GoogleDriveService.deleteFile(fileId);
            }
            
            kapalDAO.delete(kapal);
        } else {
            throw new Exception("Hanya kapal Nonaktif yang bisa ditolak/dihapus.");
        }
    }
}