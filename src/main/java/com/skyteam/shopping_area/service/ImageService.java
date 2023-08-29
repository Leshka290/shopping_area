package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image saveImage(MultipartFile image) throws IOException;

    byte[] getImage(Long id);

    Image updateImage(MultipartFile image, long oldImage) throws IOException;
}
