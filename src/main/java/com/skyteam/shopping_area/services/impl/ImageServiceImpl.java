package com.skyteam.shopping_area.services.impl;

import com.skyteam.shopping_area.models.Image;
import com.skyteam.shopping_area.models.User;
import com.skyteam.shopping_area.repositories.ImageRepository;
import com.skyteam.shopping_area.repositories.UserRepository;
import com.skyteam.shopping_area.services.ImageService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Transactional
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public ImageServiceImpl(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    private String imagesDir = "/images";

    public Image uploadImage(Long userId, MultipartFile imageFile) throws IOException {
        logger.info("Was invoked method for upload avatar");
        User user = userRepository.findById(userId).get();
        Path filePath = Path.of(imagesDir, user + "." + getExtensions(imageFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = imageFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Image image = findImage(userId);
        image.setUser(user);
        image.setFilePath(filePath.toString());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setImage(generateDataForDB(filePath));
        imageRepository.save(image);
        return image;
    }

    private byte[] generateDataForDB(Path filePath) throws IOException {
        logger.info("Was invoked method for generate data for DB");
        try (
                InputStream is = Files.newInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100,height,image.getType());
            Graphics2D graphics2D = preview.createGraphics();
            graphics2D.drawImage(image,0,0,100, height, null);
            graphics2D.dispose();

            ImageIO.write(preview,getExtensions(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    public Image findImage(Long userId) {
        logger.info("Was invoked method for find avatar");
        return imageRepository.findByUserId(userId).orElse(new Image());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Collection<Image> findAll(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method for find all avatars");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return imageRepository.findAll(pageRequest).getContent();
    }

}
