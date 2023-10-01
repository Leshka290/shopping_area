package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.*;
import com.skyteam.shopping_area.exception.AdNotFoundException;
import com.skyteam.shopping_area.exception.UserNotFoundException;
import com.skyteam.shopping_area.mapper.AdsMapper;
import com.skyteam.shopping_area.model.Ad;
import com.skyteam.shopping_area.model.Image;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.AdsRepository;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.CheckRoleUserService;
import com.skyteam.shopping_area.service.ImageService;
import com.skyteam.shopping_area.service.impl.AdsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdsServiceTest {

    @Mock
    private AdsRepository adsRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdsMapper adsMapper;

    @Mock
    private CheckRoleUserService checkRoleUserService;

    @Mock
    private ImageService imageService;

    @Mock
    private Authentication auth;

    private AdsServiceImpl adsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adsService = new AdsServiceImpl(
                adsRepository,
                userRepository,
                adsMapper,
                checkRoleUserService,
                imageService
        );
    }

    @Test
    void testGetAllAds() {
        List<Ad> adsList = List.of(new Ad(), new Ad());
        when(adsRepository.findAll()).thenReturn(adsList);
        when(adsMapper.listAdsToAdsDto(eq(adsList.size()), anyList())).thenReturn(new ResponseWrapperAdsDto());

        ResponseWrapperAdsDto response = adsService.getAllAds();

        assertNotNull(response);
        assertEquals(0, response.getCount());
    }



    @Test
    void testGetFullAds() {
        int adId = 1;
        Ad ad = new Ad();
        when(adsRepository.findById(adId)).thenReturn(Optional.of(ad));
        when(adsMapper.toFullAdsDto(ad)).thenReturn(new ExtendedAdDto());

        ExtendedAdDto extendedAdDto = adsService.getFullAds(adId);

        assertNotNull(extendedAdDto);
    }





    @Test
    void testGetAllAdsMe() {
        String userEmail = "test@test.com";
        User user = new User();
        user.setEmail(userEmail);
        when(auth.getName()).thenReturn(userEmail);
        when(userRepository.findUserByEmail(userEmail)).thenReturn(Optional.of(user));
        List<Ad> adsList = List.of(new Ad(), new Ad());
        when(adsRepository.findAllByAuthor(user)).thenReturn(adsList);
        when(adsMapper.listAdsToAdsDto(eq(adsList.size()), anyList())).thenReturn(new ResponseWrapperAdsDto());

        ResponseWrapperAdsDto response = adsService.getAllAdsMe(auth);

        assertNotNull(response);
        assertEquals(0, response.getCount());
    }

}
