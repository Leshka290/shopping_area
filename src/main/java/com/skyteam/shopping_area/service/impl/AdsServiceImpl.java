package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.AdDto;
import com.skyteam.shopping_area.dto.CreateOrUpdateAdDto;
import com.skyteam.shopping_area.dto.ExtendedAdDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import com.skyteam.shopping_area.repository.AdsRepository;
import com.skyteam.shopping_area.service.AdsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Реализует CRUD операции класса Ads
 *
 * @author leshka290
 */

@Service
@AllArgsConstructor
@Slf4j
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseWrapperAdsDto getAllAds() {
        log.info("Current method is - getAllAds");
        return modelMapper.map(adsRepository.findAll(), ResponseWrapperAdsDto.class);
    }

    @Override
    public AdDto addAds(CreateOrUpdateAdDto properties, MultipartFile image) {
        log.info("Current method is - addAds");
        //реализовать метод
        return null;
    }

    @Override
    public ExtendedAdDto getFullAds(int id) {
        log.info("Current method is - getFullAds");
        //реализовать метод
        return null;
    }

    @Override
    public void removeAdsDto(int id) {
        log.info("Current method is - removeAdsDto");
        //реализовать метод
    }

    @Override
    public AdDto updateAdsDto(int id, CreateOrUpdateAdDto createAdsDto) {
        log.info("Current method is - updateAdsDto");
        //реализовать метод
        return null;
    }

    @Override
    public ResponseWrapperAdsDto getAllAdsMe() {
        log.info("Current method is - getAllAdsMe");
        //реализовать метод
        return null;
    }
}
