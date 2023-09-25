package com.skyteam.shopping_area.controller;

import com.skyteam.shopping_area.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@CrossOrigin(value = "http://localhost:3000")
@RestController
@Transactional
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/image/{id}")
    public void transferImageToResponse(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        imageService.transferImageToResponse(id, response);
    }
}
