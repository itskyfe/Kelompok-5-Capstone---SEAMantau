/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.LaporanDAO;
import dao.LaporanPengaduanDAO;
import model.Laporan;
import model.LaporanPengaduan;
import model.Pegawai;
import model.enums.StatusLaporan;
import model.enums.StatusPengaduan;

/**
 *
 * @author TUF
 */
public class VerifikasiController {

    private final LaporanDAO laporanDAO = new LaporanDAO();
    private final LaporanPengaduanDAO pengaduanDAO = new LaporanPengaduanDAO();


    public void verifikasiLaporanPenangkapan(Laporan laporan, Pegawai pegawai, StatusLaporan statusBaru, String catatan) throws Exception {
        if (laporan.getStatusLaporan() == statusBaru && 
            (catatan != null && catatan.equals(laporan.getCatatan()))) {
             throw new Exception("Tidak ada perubahan data.");
        }
        laporan.setPegawai(pegawai);
        laporan.setStatusLaporan(statusBaru);
        laporan.setCatatan(catatan);
        laporanDAO.update(laporan);
    }


    public void verifikasiLaporanPengaduan(LaporanPengaduan pengaduan, Pegawai pegawai, StatusPengaduan statusBaru, String catatan) throws Exception {
        
        if (pengaduan.getStatusPengaduan() == statusBaru && 
            (catatan != null && catatan.equals(pengaduan.getCatatan()))) {
             throw new Exception("Tidak ada perubahan data.");
        }
        
        pengaduan.setPegawai(pegawai);
        pengaduan.setStatusPengaduan(statusBaru);
        pengaduan.setCatatan(catatan);
        
        pengaduanDAO.update(pengaduan);
    }
}