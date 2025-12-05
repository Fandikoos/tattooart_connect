package com.almozara.tattooart_connect.service.image;

import com.almozara.tattooart_connect.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    ImageDto uploadImage(Long idStudio, MultipartFile file);

    void deleteImage(Long idImage);

    List<ImageDto> getImagesByStudio(Long idStudio);
}
