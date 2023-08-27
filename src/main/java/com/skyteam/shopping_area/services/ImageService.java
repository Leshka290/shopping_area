package com.skyteam.shopping_area.services;

import com.skyteam.shopping_area.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public interface ImageService {

    Image uploadImage(Long userId, MultipartFile imageFile) throws IOException;

    Image findImage(Long userId);

    Collection<Image> findAll(Integer pageNumber, Integer pageSize);
}
