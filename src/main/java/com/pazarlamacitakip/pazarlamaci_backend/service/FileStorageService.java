package com.pazarlamacitakip.pazarlamaci_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    private Path uploadPath;

    @PostConstruct
    public void init() {
        this.uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Upload dizini oluşturulamadı!", e);
        }
    }

    /**
     * Dosyayı kaydet ve erişim URL'ini döndür
     */
    public String storeFile(MultipartFile file) {
        // Orijinal dosya adını al
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        
        // Dosya uzantısını al
        String fileExtension = "";
        if (originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        
        // Benzersiz dosya adı oluştur (UUID + uzantı)
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        try {
            // Güvenlik kontrolü
            if (newFileName.contains("..")) {
                throw new RuntimeException("Geçersiz dosya adı: " + newFileName);
            }

            // Dosyayı kaydet
            Path targetLocation = this.uploadPath.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Erişim URL'ini döndür
            return "/api/files/" + newFileName;

        } catch (IOException e) {
            throw new RuntimeException("Dosya kaydedilemedi: " + newFileName, e);
        }
    }

    /**
     * Dosyayı oku
     */
    public Path getFilePath(String fileName) {
        return this.uploadPath.resolve(fileName).normalize();
    }
}
