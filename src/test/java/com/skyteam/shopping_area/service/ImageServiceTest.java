package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.model.Image;
import com.skyteam.shopping_area.repository.ImageRepository;
import com.skyteam.shopping_area.service.impl.ImageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ImageServiceTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSaveImageFile() throws IOException {
        MultipartFile imageFile = new MockMultipartFile("test.jpg", "test.jpg", "image/jpeg", "test data".getBytes());

        when(imageRepository.findByFilePath(anyString())).thenReturn(Optional.empty());

        imageService.saveImageFile(imageFile);

        verify(imageRepository, times(1)).saveAndFlush(any());
    }



    @Test
    public void testGenerateFileName() {
        String fileName = imageService.generateFileName();

        assertEquals(8, fileName.length());
    }

    @Test
    public void testGetExtension() {
        String fileName = "image.jpg";
        String extension = imageService.getExtension(fileName);

        assertEquals("jpg", extension);
    }


}
