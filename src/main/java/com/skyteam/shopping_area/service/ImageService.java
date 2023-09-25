package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.model.Image;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ImageService {

    void transferImageToResponse(Integer imageId, HttpServletResponse response) throws IOException;

    Image saveImageFile(MultipartFile imageFile) throws IOException;
}
