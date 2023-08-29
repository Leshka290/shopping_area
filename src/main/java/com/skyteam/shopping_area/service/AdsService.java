package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.AdsDto;
import com.skyteam.shopping_area.dto.CreateAdsDto;
import com.skyteam.shopping_area.dto.FullAdsDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import org.springframework.web.multipart.MultipartFile;

public interface AdsService {

    ResponseWrapperAdsDto getAllAds();

    AdsDto addAds(CreateAdsDto properties, MultipartFile image);

    FullAdsDto getFullAds(int adsId);

    void removeAdsDto(int adsId);

    AdsDto updateAdsDto(int adsId, CreateAdsDto createAdsDto);

    ResponseWrapperAdsDto getAllAdsMe();

}
