/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.LaporanDAO;
import model.Laporan;
import model.enums.StatusLaporan;
import util.GoogleDriveService;

import java.io.File;
import java.time.LocalDateTime;
/**
 *
 * @author TUF
 */
public class LaporanPenangkapanController {
    
    private LaporanDAO dao = new LaporanDAO();
    

    public void buatLaporanBerangkat(Laporan laporan, File fotoFile, String driveFolderId) throws Exception {
        
        String fotoUrl = GoogleDriveService.uploadFile(fotoFile.getAbsolutePath(), driveFolderId);
        
        laporan.setFotoAlatTangkap(fotoUrl);
        laporan.setWaktuBerangkat(LocalDateTime.now());
        laporan.setStatusLaporan(StatusLaporan.Menunggu);
        
        laporan.setWaktuBerlabuh(null);
        laporan.setPegawai(null); 
        laporan.setCatatan(null);
        
        dao.save(laporan);
    }
    public void selesaikanPelayaran(Laporan laporan) throws Exception {
        
        
        if (laporan.getStatusLaporan() == StatusLaporan.Berlayar && laporan.getWaktuBerlabuh() == null) 
        {
            laporan.setWaktuBerlabuh(LocalDateTime.now());
            laporan.setStatusLaporan(StatusLaporan.Berlayar);
            dao.update(laporan); 
        } 
        else if (laporan.getWaktuBerlabuh() != null) 
        {
            throw new Exception("Laporan ini sudah diselesaikan sebelumnya.");
        } 
        else 
        {
            throw new Exception("Hanya laporan berstatus 'Berlayar' yang bisa diselesaikan.");
        }
    }
    public void hapusLaporan(Laporan laporan) throws Exception {
        if (laporan.getStatusLaporan() == StatusLaporan.Menunggu) {
            
            String fotoUrl = laporan.getFotoAlatTangkap();
            if (fotoUrl != null && !fotoUrl.isEmpty()) {
                String fileId = GoogleDriveService.extractFileIdFromUrl(fotoUrl);
                GoogleDriveService.deleteFile(fileId); 
            }
            
            dao.delete(laporan); 
        } else {
            throw new Exception("Hanya laporan berstatus 'Menunggu' yang bisa dihapus.");
        }
    }
}