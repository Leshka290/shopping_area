package com.skyteam.shopping_area.services.impl;

import com.skyteam.shopping_area.exceptions.ImageNotFoundException;
import com.skyteam.shopping_area.models.Image;
import com.skyteam.shopping_area.repositories.ImageRepository;
import com.skyteam.shopping_area.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image saveImage(MultipartFile image) {
        //дописать метод
        return null;
    }

    @Override
    public Image updateImage(MultipartFile image, String oldImage) {
        //дописать метод
        return null;
    }

    @Override
    public byte[] getImage(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() ->
                new ImageNotFoundException("Image exception"));
        return image.getImage();
    }

}
