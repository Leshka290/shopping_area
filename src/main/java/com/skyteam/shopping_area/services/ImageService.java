package com.skyteam.shopping_area.services;

import com.skyteam.shopping_area.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image saveImage(MultipartFile image) throws IOException;

    byte[] getImage(Long id);

    Image updateImage(MultipartFile image, String oldImage) throws IOException;
}
