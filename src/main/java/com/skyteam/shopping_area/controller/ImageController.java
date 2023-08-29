package com.skyteam.shopping_area.controller;

import com.skyteam.shopping_area.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "updateAdsImage", operationId = "updateAdsImage",
            responses = {@ApiResponse(responseCode = "200", description = "OK"
                    , content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE
                    , schema = @Schema(type = "array", format = "byte"))),
                    @ApiResponse(responseCode = "404",
                            description = "Not Found")}, tags = "Image")
    @PatchMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateAdsImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {
        log.info("Update image ags id: {}", id);
        imageService.updateImage(imageFile, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
