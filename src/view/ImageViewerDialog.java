package view;

import java.awt.Frame;
import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

public class ImageViewerDialog extends JDialog {

    private final JLabel imageLabel;
    private final String googleDriveUrl;

    public ImageViewerDialog(Frame parent, String googleDriveUrl) {
        super(parent, "Lihat Foto Bukti", true); 
        this.googleDriveUrl = googleDriveUrl;
        imageLabel = new JLabel("Sedang memuat gambar...");
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(new JScrollPane(imageLabel)); 
        setSize(400, 300);
        setLocationRelativeTo(parent);
        new ImageDownloader().execute();
    }
    
    public ImageViewerDialog(JDialog parent, String googleDriveUrl) {
        super(parent, "Lihat Foto Bukti", true); 
        this.googleDriveUrl = googleDriveUrl;
        imageLabel = new JLabel("Sedang memuat gambar...");
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(new JScrollPane(imageLabel)); 
        setSize(400, 300);
        setLocationRelativeTo(parent);
        new ImageDownloader().execute();
    }

    private String getDirectDownloadUrl(String anyGoogleDriveUrl) throws Exception {
        String fileId = null;
        if (anyGoogleDriveUrl == null || anyGoogleDriveUrl.isEmpty()) {
            throw new Exception("URL kosong.");
        }
        try {
            if (anyGoogleDriveUrl.contains("/file/d/")) {
                fileId = anyGoogleDriveUrl.split("/d/")[1].split("/")[0];
            } else if (anyGoogleDriveUrl.contains("id=")) {
                fileId = anyGoogleDriveUrl.split("id=")[1].split("&")[0];
            } else {
                throw new Exception("Format URL Google Drive tidak dikenali.");
            }
        } catch (Exception e) {
            throw new Exception("Gagal parse File ID dari URL. Format tidak valid.");
        }
        if (fileId == null || fileId.trim().isEmpty()) {
             throw new Exception("Gagal mengekstrak File ID dari URL.");
        }
        return "https://drive.google.com/uc?export=download&id=" + fileId;
    }

    class ImageDownloader extends SwingWorker<ImageIcon, Void> {
        @Override
        protected ImageIcon doInBackground() throws Exception {
            String downloadUrl = getDirectDownloadUrl(googleDriveUrl);
            URL url = new URL(downloadUrl);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0"); 
            InputStream in = conn.getInputStream();
            Image image = ImageIO.read(in); 
            in.close();
            if (image == null) {
                throw new Exception("Gagal membaca gambar. Pastikan file 'Anyone with the link can view'.");
            }
            return new ImageIcon(image);
        }
        
        @Override
        protected void done() {
            try {
                ImageIcon icon = get(); 
                Image image = icon.getImage();
                int width = image.getWidth(null);
                int height = image.getHeight(null);
                double aspectRatio = (double) width / height;
                int maxWidth = 800; 
                int maxHeight = 600; 
                if (width > maxWidth) {
                    width = maxWidth;
                    height = (int) (width / aspectRatio);
                }
                if (height > maxHeight) {
                    height = maxHeight;
                    width = (int) (height * aspectRatio);
                }
                Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.setText(null); 
                setSize(width + 20, height + 40); 
                setLocationRelativeTo(getParent());
            } catch (Exception e) {
                imageLabel.setText("Gagal memuat gambar: " + e.getMessage());
                setSize(500, 300); 
//                e.printStackTrace();
            }
        }
    }
}