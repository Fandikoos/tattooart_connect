package com.almozara.tattooart_connect.service.storage;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService{

    @Value("${media.location}")
    private String mediaLocation;

    private Path rootLocation;
    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of("image/jpeg", "image/png", "image/webp");

    @Override
    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    // Metodo para almacenar un archivo
    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()){
                throw new RuntimeException("Failed to store empty file");
            }

            String contentType = file.getContentType();
            if (!ALLOWED_CONTENT_TYPES.contains(contentType)) {
                throw new RuntimeException("Only allow imágenes JPEG, PNG o WEBP.");
            }

            // Generar nombre único para el archivo
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = UUID.randomUUID() + extension;

            // Guardar en el sistema de archivos
            Path destination = rootLocation.resolve(newFilename).normalize();
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            return newFilename;
        } catch (IOException e){
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    // Recuperar archivo a partir del nombre
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()){
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e){
            throw new RuntimeException("Could not read file: " + filename);
        }
    }
}
