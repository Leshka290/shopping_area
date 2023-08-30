package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.AdDto;
import com.skyteam.shopping_area.dto.CreateOrUpdateAdDto;
import com.skyteam.shopping_area.dto.ExtendedAdDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import org.springframework.web.multipart.MultipartFile;

public interface AdsService {

    ResponseWrapperAdsDto getAllAds();

    AdDto addAds(CreateOrUpdateAdDto properties, MultipartFile image);

    ExtendedAdDto getFullAds(int adsId);

    void removeAdsDto(int adsId);

    AdDto updateAdsDto(int adsId, CreateOrUpdateAdDto createAdsDto);

    ResponseWrapperAdsDto getAllAdsMe();

}
