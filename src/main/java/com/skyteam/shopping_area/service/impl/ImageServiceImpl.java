package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.exception.ImageNotFoundException;
import com.skyteam.shopping_area.model.Image;
import com.skyteam.shopping_area.repository.ImageRepository;
import com.skyteam.shopping_area.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Реализует CRUD операции класса Image
 *
 * @author leshka290
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Value("${shop.image.dir.path}")
    private String imageDir;

//
//    @Override
//    public byte[] getImage(Integer id) {
//        log.info("Current method is - getImage");
//
//        Image image = imageRepository.findById(id).orElseThrow(() ->
//                new ImageNotFoundException("Image exception"));
//        return image.getImage();
//    }

    @Override
    public void transferImageToResponse(Integer imageId, HttpServletResponse response) throws IOException {
        Image image = imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
        try (InputStream is = Files.newInputStream(image.getPath());
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(image.getMediaType());
            response.setContentLength((int)image.getSize());
            is.transferTo(os);
        }
    }

    @Override
    public Image saveImageFile(MultipartFile imageFile) throws IOException {
        Path filePath = createPathFromFile(imageFile);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = imageFile.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
        return saveImageDetails(imageFile, filePath);
    }

    private Path createPathFromFile(MultipartFile imageFile) {
        Path path = Path.of(String.format("%s/%s", imageDir, imageFile.getOriginalFilename()));
        if (Files.exists(path)) {
            path = Path.of(String.format("%s/%s.%s", imageDir, generateFileName(),
                    getExtension(Objects.requireNonNull(imageFile.getOriginalFilename(), ""))));
        }
        return path;
    }

    private String generateFileName() {
        int length = 8;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Image saveImageDetails(MultipartFile imageFile, Path filePath) {
        Image image = imageRepository.findByFilePath(filePath.toString()).orElse(new Image());
        image.setFilePath(filePath.toString());
        image.setFileExtension(getExtension(Objects.requireNonNull(imageFile.getOriginalFilename())));
        image.setSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        return imageRepository.saveAndFlush(image);
    }
}
