package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.AdDto;
import com.skyteam.shopping_area.dto.CreateOrUpdateAdDto;
import com.skyteam.shopping_area.dto.ExtendedAdDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface AdsService {

    ResponseWrapperAdsDto getAllAds();

    AdDto addAds(CreateOrUpdateAdDto properties, MultipartFile image, String email);

    ExtendedAdDto getFullAds(int adsId);

    void removeAdDto(String email, int adsId);

    AdDto updateAdDto(int adsId, CreateOrUpdateAdDto createAdsDto, String email);

    ResponseWrapperAdsDto getAllAdsMe(Authentication auth);

    boolean updateImage(int id, MultipartFile imageFile, String email);

}
