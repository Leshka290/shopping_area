package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.AdsDto;
import com.skyteam.shopping_area.dto.CreateAdsDto;
import com.skyteam.shopping_area.dto.FullAdsDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import com.skyteam.shopping_area.repository.AdsRepository;
import com.skyteam.shopping_area.service.AdsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public AdsDto addAds(CreateAdsDto properties, MultipartFile image) {
        //реализовать метод
        return null;
    }

    @Override
    public FullAdsDto getFullAds(int id) {
        //реализовать метод
        return null;
    }

    @Override
    public void removeAdsDto(int id) {
        //реализовать метод
    }

    @Override
    public AdsDto updateAdsDto(int id, CreateAdsDto createAdsDto) {
        //реализовать метод
        return null;
    }

    @Override
    public ResponseWrapperAdsDto getAllAdsMe() {
        //реализовать метод
        return null;
    }
}
