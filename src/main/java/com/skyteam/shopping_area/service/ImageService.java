package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.model.Image;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image saveImage(MultipartFile image) throws IOException;

    byte[] getImage(int id);

    Image updateImage(String email, MultipartFile image, int oldImage) throws IOException;
}
