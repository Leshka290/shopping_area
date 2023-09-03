package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.AdDto;
import com.skyteam.shopping_area.dto.CreateOrUpdateAdDto;
import com.skyteam.shopping_area.dto.ExtendedAdDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import com.skyteam.shopping_area.exception.AdNotFoundException;
import com.skyteam.shopping_area.exception.ImageNotFoundException;
import com.skyteam.shopping_area.model.Ad;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.AdsRepository;
import com.skyteam.shopping_area.service.AdsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (image != null) {
            AdDto newAd = new AdDto();
            newAd.setAuthor(properties.getAuthorId());
            newAd.setImage(String.valueOf(image));
            newAd.setPrice(properties.getPrice());
            newAd.setTitle(properties.getTitle());
            Ad modelAd = modelMapper.map(newAd, Ad.class);
            adsRepository.save(modelAd);
            newAd.setPk(modelAd.getId());
            return newAd;
        } else {
            throw new ImageNotFoundException("Картинка не найдена");
        }
    }

    @Override
    public ExtendedAdDto getFullAds(long id) {
        log.info("Current method is - getFullAds");
        return null;
    }

    @Override
    public void removeAdsDto(long id) {
        log.info("Current method is - removeAdsDto");
        Optional<Ad> optionalAd = adsRepository.findById(id);
        if (optionalAd.isPresent()) {
            adsRepository.deleteById(id);
        } else {
            throw new AdNotFoundException("Объявление не найдено");
        }
    }

    @Override
    public AdDto updateAdsDto(long id, CreateOrUpdateAdDto properties) {
        log.info("Current method is - updateAdsDto");
        Optional<Ad> optionalAd = adsRepository.findById(id);
        if (optionalAd.isPresent()) {
            Ad existingAd = optionalAd.get();
            existingAd.setTitle(properties.getTitle());
            existingAd.setPrice(properties.getPrice());
            Ad ad = adsRepository.save(existingAd);
            AdDto adDto = new AdDto();
            adDto.setPk(ad.getId());
            adDto.setTitle(ad.getTitle());
            adDto.setPrice(ad.getPrice());
            return adDto;
        } else {
            throw new AdNotFoundException("Объявление не найдено");
        }
    }

    @Override
    public ResponseWrapperAdsDto getAllAdsMe() {
        log.info("Current method is - getAllAdsMe");
        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();
        List<Ad> ads = adsRepository.findByAuthorId(authenticatedUsername);
        List<AdDto> adDtos = new ArrayList<>();
        for (Ad ad : ads) {
            AdDto adDto = new AdDto();
            adDto.setTitle(ad.getTitle());
            adDto.setPrice(ad.getPrice());
            adDtos.add(adDto);
            responseWrapperAdsDto.setResults(adDtos);
        }
        return responseWrapperAdsDto;
    }
}
