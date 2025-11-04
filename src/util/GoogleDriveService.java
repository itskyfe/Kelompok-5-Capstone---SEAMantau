package util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;

import java.io.*;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author TUF
 */
public class GoogleDriveService {

    private static final String APPLICATION_NAME = "Sistem Nelayan Upload";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final java.io.File TOKENS_DIRECTORY_PATH = new java.io.File("tokens");

    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);
    private static final String CREDENTIALS_FILE_PATH = "/util/credentials.json";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = GoogleDriveService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("File credentials.json tidak ditemukan: " + CREDENTIALS_FILE_PATH);
        }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(TOKENS_DIRECTORY_PATH))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    private static Drive getDriveService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static String uploadFile(String filePath) throws IOException, GeneralSecurityException {
        return uploadFileToFolder(new java.io.File(filePath), null);
    }

    public static String uploadFile(String filePath, String folderId) throws IOException, GeneralSecurityException {
        Drive service = getDriveService();

        java.io.File f = new java.io.File(filePath);
        File fileMetadata = new File();
        fileMetadata.setName(f.getName());
        if (folderId != null && !folderId.isBlank()) {
            fileMetadata.setParents(Collections.singletonList(folderId));
        }

        FileContent mediaContent = new FileContent(Files.probeContentType(f.toPath()), f);
        File uploadedFile = service.files().create(fileMetadata, mediaContent)
                .setFields("id") 
                .execute();

        Permission permission = new Permission().setType("anyone").setRole("reader");
        service.permissions().create(uploadedFile.getId(), permission).execute();

        return "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
    }

    private static String uploadFileToFolder(java.io.File fileToUpload, String folderId) throws IOException, GeneralSecurityException {
        Drive service = getDriveService();
        File fileMetadata = new File();
        fileMetadata.setName(fileToUpload.getName());

        if (folderId != null && !folderId.isEmpty()) {
            fileMetadata.setParents(Collections.singletonList(folderId));
        }

        FileContent mediaContent = new FileContent("image/jpeg", fileToUpload);

        File uploadedFile = service.files().create(fileMetadata, mediaContent)
                .setFields("id, parents")
                .execute();

        Permission permission = new Permission()
                .setType("anyone")
                .setRole("reader");
        service.permissions().create(uploadedFile.getId(), permission).execute();

        return "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
    }
    
    public static String extractFileIdFromUrl(String anyGoogleDriveUrl) throws Exception {
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
        return fileId;
    }

    public static void deleteFile(String fileId) {
        if (fileId == null || fileId.isEmpty()) {
            System.err.println("File ID kosong, GDrive delete di-skip.");
            return;
        }
        
        try {
            Drive service = getDriveService(); 
            service.files().delete(fileId).execute();
//            System.out.println("File " + fileId + " berhasil dihapus dari GDrive.");
        } catch (Exception e) {

            System.err.println("PERINGATAN: Gagal menghapus file di GDrive (ID: " + fileId + "). "
                    + "Data DB akan tetap dihapus. Error GDrive: " + e.getMessage());
        }
    }
}