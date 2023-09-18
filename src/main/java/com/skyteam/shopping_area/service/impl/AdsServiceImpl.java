package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.AdDto;
import com.skyteam.shopping_area.dto.CreateOrUpdateAdDto;
import com.skyteam.shopping_area.dto.ExtendedAdDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import com.skyteam.shopping_area.exception.AdNotFoundException;
import com.skyteam.shopping_area.exception.ImageNotFoundException;
import com.skyteam.shopping_area.exception.UserNotFoundException;
import com.skyteam.shopping_area.model.Ad;
import com.skyteam.shopping_area.model.Image;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.AdsRepository;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.AdsService;
import com.skyteam.shopping_area.service.CheckRoleUserService;
import com.skyteam.shopping_area.service.ImageService;
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
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CheckRoleUserService checkRoleUserService;
    private final ImageService imageService;

    @Override
    public ResponseWrapperAdsDto getAllAds() {
        log.info("Current method is - getAllAds");
        return modelMapper.map(adsRepository.findAll(), ResponseWrapperAdsDto.class);
    }

    @Override
    public AdDto addAds(CreateOrUpdateAdDto properties, MultipartFile imageFile, String email) {
        log.info("Current method is - addAds");
        Ad saveAd = modelMapper.map(properties, Ad.class);
        saveAd.setAuthor(userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new));
        Image image;
        if (imageFile != null) {
            try {
                image = imageService.saveImage(imageFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            saveAd.setImage(image);
            adsRepository.save(saveAd);
            return modelMapper.map(saveAd, AdDto.class);
        } else {
            throw new ImageNotFoundException("Картинка не найдена");
        }
    }

    @Override
    public ExtendedAdDto getFullAds(int id) {
        Ad ad = adsRepository.findById(id).orElseThrow(AdNotFoundException::new);
        log.info(String.valueOf(ad));
        return modelMapper.map(ad, ExtendedAdDto.class);
    }

    @Override
    public void removeAdDto(String email, int id) {
        log.info("Current method is - removeAdsDto");
        Optional<Ad> optionalAd = adsRepository.findById(id);

        Ad ad = adsRepository.findById(id).orElseThrow(AdNotFoundException::new);
        User adOwner = ad.getAuthor();
        if (checkRoleUserService.isUserOrAdmin(email, adOwner ) && optionalAd.isPresent()) {
            adsRepository.deleteById(id);
        } else {
            throw new AdNotFoundException("Объявление не найдено");
        }
    }

    @Override
    public AdDto updateAdDto(int id, CreateOrUpdateAdDto properties, String email) {
        log.info("Current method is - updateAdsDto");
        Optional<Ad> optionalAd = adsRepository.findById(id);
        if (optionalAd.isPresent()) {
            Ad existingAd = optionalAd.get();
            User adOwner = existingAd.getAuthor();
            if (checkRoleUserService.isUserOrAdmin(email, adOwner)) {
                existingAd.setTitle(properties.getTitle());
                existingAd.setPrice(properties.getPrice());
                return modelMapper.map(adsRepository.save(existingAd), AdDto.class);
            }
        }else {
            throw new AdNotFoundException("Объявление не найдено");
        }
        return null;
    }

    @Override
    public ResponseWrapperAdsDto getAllAdsMe(Authentication auth) {
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
