package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

/**
 * Dialog untuk memilih titik koordinat di peta OpenStreetMap.
 * Klik kiri untuk memilih lokasi.
 */
public class MapPicker extends JDialog {
    private JXMapViewer mapViewer;
    private JButton btnSelect;
    private GeoPosition selectedPosition;

    // Getter untuk mengambil koordinat terpilih
    public GeoPosition getSelectedPosition() {
        return selectedPosition;
    }

    public MapPicker(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Pilih Titik Koordinat");
        setPreferredSize(new Dimension(800, 600));
        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        // --- Setup Map Viewer ---
        mapViewer = new JXMapViewer();

        // 🔧 FIX HTTPS TileFactoryInfo
        TileFactoryInfo info = new TileFactoryInfo(
                0, 19, 19,
                256, true, true,
                "https://tile.openstreetmap.org",
                "x", "y", "z") {

            @Override
            public String getTileUrl(int x, int y, int zoom) {
                int z = 19 - zoom;
                return String.format("%s/%d/%d/%d.png", getBaseURL(), z, x, y);
            }
        };

        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        System.setProperty("http.agent", "Mozilla/5.0 (Java JXMapViewer)");
        mapViewer.setTileFactory(tileFactory);

        // Atur posisi awal (contoh: Jakarta)
        mapViewer.setAddressLocation(new GeoPosition(-6.2000, 106.8167));
        mapViewer.setZoom(5);

        // Tambahkan kontrol zoom & geser
        mapViewer.addMouseListener(new PanMouseInputListener(mapViewer));
        mapViewer.addMouseMotionListener(new PanMouseInputListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));

        // Tambahkan event klik untuk ambil koordinat
        mapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (SwingUtilities.isLeftMouseButton(evt)) {
                    GeoPosition gp = mapViewer.convertPointToGeoPosition(evt.getPoint());
                    selectedPosition = gp;
                    updateTitle();
                }
            }
        });

        // --- Panel tombol kontrol ---
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSelect = new JButton("Pilih Lokasi");
        JButton btnCancel = new JButton("Batal");

        btnSelect.addActionListener(e -> dispose());
        btnCancel.addActionListener(e -> {
            selectedPosition = null;
            dispose();
        });

        btnSelect.setEnabled(false);
        controlPanel.add(btnCancel);
        controlPanel.add(btnSelect);

        // Gabungkan semua komponen
        setLayout(new BorderLayout());
        add(mapViewer, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    // Update title saat user klik peta
    private void updateTitle() {
        if (selectedPosition != null) {
            String lat = String.format("%.6f", selectedPosition.getLatitude());
            String lon = String.format("%.6f", selectedPosition.getLongitude());
            setTitle("Koordinat dipilih: " + lat + ", " + lon);
            btnSelect.setEnabled(true);
        } else {
            setTitle("Pilih Titik Koordinat");
            btnSelect.setEnabled(false);
        }
    }
}
