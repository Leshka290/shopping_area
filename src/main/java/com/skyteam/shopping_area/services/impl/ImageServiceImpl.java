package com.skyteam.shopping_area.services.impl;

import com.skyteam.shopping_area.exceptions.ImageNotFoundException;
import com.skyteam.shopping_area.models.Image;
import com.skyteam.shopping_area.repositories.ImageRepository;
import com.skyteam.shopping_area.services.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image saveImage(MultipartFile newImage) throws IOException {
        log.info("Current method is - saveImage");

        Image image = new Image();
        image.setImage(newImage.getBytes());

        return imageRepository.save(image);
    }

    @Override
    public Image updateImage(MultipartFile newImage, String idImage) throws IOException {
        log.info("Current method is - updateImage");

        Image image = imageRepository.findById(Long.valueOf(idImage)).orElseThrow(() -> new ImageNotFoundException("Image exception"));
        image.setId(idImage);
        image.setImage(newImage.getBytes());

        return imageRepository.save(image);
    }

    @Override
    public byte[] getImage(Long id) {
        log.info("Current method is - getImage");

        Image image = imageRepository.findById(id).orElseThrow(() ->
                new ImageNotFoundException("Image exception"));
        return image.getImage();
    }
}
