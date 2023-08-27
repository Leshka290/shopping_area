package com.skyteam.shopping_area.controllers;

import com.skyteam.shopping_area.models.Image;
import com.skyteam.shopping_area.services.impl.ImageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

/**
 * Контроллер для работы с изображениями пользователей
 * @author Kovachev
 */
@RestController
@RequestMapping("image")
public class ImageController {
    private final ImageServiceImpl imageService;

    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    @Operation(summary = "Upload image")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@PathVariable Long id, MultipartFile image) throws IOException {
        imageService.uploadImage(id, image);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Download image")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping(value = "/{id}/image/preview")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
        Image image = imageService.findImage(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getMediaType()));
        headers.setContentLength(image.getImage().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(image.getImage());
    }

    @GetMapping(value = "/{id}/image")
    public void downloadImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Image image = imageService.findImage(id);

        Path path = Path.of(image.getFilePath());

        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream()
        ) {
            response.setStatus(200);
            response.setContentType(image.getMediaType());
            response.setContentLength((int) image.getFileSize());
            is.transferTo(os);
        }
    }

    @GetMapping("all")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @Operation(summary = "Find list all images")
    public Collection<Image> findAll(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        return imageService.findAll(pageNumber,pageSize);
    }
}
