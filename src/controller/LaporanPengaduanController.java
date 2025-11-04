package controller;

import dao.LaporanPengaduanDAO;
import model.LaporanPengaduan;
import model.enums.StatusPengaduan;
import util.GoogleDriveService;

import java.io.File;
import java.time.LocalDateTime;

/**
 * @author TUF
 */
public class LaporanPengaduanController {
    
    private final LaporanPengaduanDAO dao = new LaporanPengaduanDAO();

    
    public void buatLaporanBaru(LaporanPengaduan laporan, File fotoFile, String driveFolderId) throws Exception {
        
        String fotoUrl = GoogleDriveService.uploadFile(fotoFile.getAbsolutePath(), driveFolderId);
        
        laporan.setDokumentasi(fotoUrl);
        laporan.setTanggalPengaduan(LocalDateTime.now());
        laporan.setStatusPengaduan(StatusPengaduan.Menunggu); 
        laporan.setPegawai(null); 
        laporan.setCatatan(null); 
        dao.save(laporan);
    }
    public void hapusLaporanPengaduan(LaporanPengaduan laporan) throws Exception {
        if (laporan.getStatusPengaduan() != StatusPengaduan.Menunggu) {
            throw new Exception("Hanya laporan berstatus 'Menunggu' yang bisa dihapus.");
        }
        
        String fotoUrl = laporan.getDokumentasi();
        if (fotoUrl != null && !fotoUrl.isEmpty()) {
            String fileId = GoogleDriveService.extractFileIdFromUrl(fotoUrl);
            GoogleDriveService.deleteFile(fileId); 
        }
        
        dao.delete(laporan);
    }
    
}