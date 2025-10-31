///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package util;
//
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.FileContent;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.client.util.store.FileDataStoreFactory;
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.DriveScopes;
//import com.google.api.services.drive.model.File;
//import com.google.api.services.drive.model.Permission;
//
//import java.io.*;
//import java.security.GeneralSecurityException;
//import java.util.Collections;
//import java.util.List;
//
///**
// *
// * @author TUF
// */
//    public class GoogleDriveService {
//
//    private static final String APPLICATION_NAME = "Sistem Nelayan Upload";
//    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//    private static final java.io.File TOKENS_DIRECTORY_PATH = new java.io.File("tokens");
//
//    // Scope: full access ke Google Drive (upload, read, dll)
//    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);
//    private static final String CREDENTIALS_FILE_PATH = "/util/credentials.json"; // lokasi di resource folder (src/main/resources kalau pakai itu)
//
//    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
//        InputStream in = GoogleDriveService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//        if (in == null) {
//            throw new FileNotFoundException("File credentials.json tidak ditemukan: " + CREDENTIALS_FILE_PATH);
//        }
//
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                .setDataStoreFactory(new FileDataStoreFactory(TOKENS_DIRECTORY_PATH))
//                .setAccessType("offline")
//                .build();
//
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//        return new Authorizati      onCodeInstalledApp(flow, receiver).authorize("user");
//    }
//
//    /** Upload file ke Google Drive dan return link-nya */
//    public static String uploadFile(String filePath) throws IOException, GeneralSecurityException {
//        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//
//        java.io.File fileToUpload = new java.io.File(filePath);
//        File fileMetadata = new File();
//        fileMetadata.setName(fileToUpload.getName());
//
//        FileContent mediaContent = new FileContent("image/jpeg", fileToUpload);
//        File uploadedFile = service.files().create(fileMetadata, mediaContent)
//                .setFields("id")
//                .execute();
//
//        // Bikin file-nya public readable
//        Permission permission = new Permission()
//                .setType("anyone")
//                .setRole("reader");
//        service.permissions().create(uploadedFile.getId(), permission).execute();
//
//        // URL public Google Drive
//        return "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
//    }
//}
//    
