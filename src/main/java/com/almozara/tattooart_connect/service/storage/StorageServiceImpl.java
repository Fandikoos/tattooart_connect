package com.almozara.tattooart_connect.service.storage;

import com.almozara.tattooart_connect.global.exceptions.StorageException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final Path root = Paths.get("storage/studios");

    @Override
    public String save(MultipartFile file, Long idStudio) {
        String originalFileName = file.getOriginalFilename();
        // Extraer extension
        String extension = getFileExtension(originalFileName);

        String storedImageName = UUID.randomUUID() + extension;
        try {
            Path studioImageFolder = root.resolve(String.valueOf(idStudio)).resolve("images");
            Files.createDirectories(studioImageFolder);
            //Ruta final donde guardamos el archivo
            Path destinationFile = studioImageFolder.resolve(storedImageName).normalize();
            Files.copy(file.getInputStream(), destinationFile);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + storedImageName, e);
        }

    }

    private String getFileExtension(String originalFileName) {
        if (originalFileName == null || !originalFileName.contains(".")) {
            throw new StorageException("Invalid file: no extension found");
        }
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    @Override
    public void delete(String storedImageName, Long idStudio) {
        try {
            Path filePath = root.resolve(String.valueOf(idStudio)).resolve("images").resolve(storedImageName);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new StorageException("Could not delete file " + storedImageName, e);
        }
    }

    @Override
    public Resource load(String storedImageName, Long idStudo) {
        try {
            Path filePath = root.resolve(String.valueOf(idStudo)).resolve("images").resolve(storedImageName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException("Could not read file: " + storedImageName);
            }

        } catch (MalformedURLException e) {
            throw new StorageException("Could not load file: " + storedImageName, e);
        }
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage directory", e);
        }
    }
}
