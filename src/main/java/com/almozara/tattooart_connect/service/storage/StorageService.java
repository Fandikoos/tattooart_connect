package com.almozara.tattooart_connect.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String save(MultipartFile file, Long idStudio);

    void delete(String storedName, Long idStudio);

    Resource load(String storedName, Long idStudo);
}
