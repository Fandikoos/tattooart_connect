package com.almozara.tattooart_connect.service.image;

import com.almozara.tattooart_connect.domain.ImageEntity;
import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.ImageDto;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.mapper.ImageMapper;
import com.almozara.tattooart_connect.mapper.StudioMapper;
import com.almozara.tattooart_connect.repository.ImageRepository;
import com.almozara.tattooart_connect.service.storage.StorageService;
import com.almozara.tattooart_connect.service.studio.StudioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;
    private final StudioMapper studioMapper;
    private final ImageRepository imageRepository;
    private final StorageService storageService;
    private final StudioService studioService;

    @Override
    @Transactional
    public ImageDto uploadImage(Long idStudio, MultipartFile file) {
        StudioDto studio = studioService.findById(idStudio);
        StudioEntity studioEntity = studioMapper.transferToEntity(studio);
        validateFile(file);

        String storedImageName = storageService.save(file, idStudio);

        ImageEntity image = new ImageEntity();
        image.setOriginalName(file.getOriginalFilename());
        image.setStoredName(storedImageName);
        image.setUrl("/studios/" + idStudio + "/images/" + storedImageName);
        image.setUploadedAt(LocalDateTime.now());
        image.setTattooStudio(studioEntity);

        ImageEntity savedImageEntity = imageRepository.save(image):
        return imageMapper.transferToDto(savedImageEntity);
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
            throw new IllegalArgumentException("Only image formats are allowed");
        }

        long maxSize = 5_000_000;
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("File exceeds max size of 5MB");
        }
    }

    @Override
    @Transactional
    public void deleteImage(Long idImage) {
        ImageEntity imageEntity = imageRepository.findById(idImage)
                .orElseThrow(() -> new EntityNotFoundException("Image not found " + idImage));

        // Borrar del filesystem local
        Long idStudio = imageEntity.getTattooStudio().getIdStudio();
        storageService.delete(imageEntity.getStoredName(), idStudio);

        // Borrar en db
        imageRepository.delete(imageEntity);
    }

    @Override
    public List<ImageDto> getImagesByStudio(Long idStudio) {
        List<ImageEntity> imageEntities = imageRepository.findByTattooStudio_IdStudio(idStudio);
        return imageMapper.transferToDtoList(imageEntities);
    }
}
