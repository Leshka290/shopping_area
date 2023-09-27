package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.AdDto;
import com.skyteam.shopping_area.dto.CreateOrUpdateAdDto;
import com.skyteam.shopping_area.dto.ExtendedAdDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import com.skyteam.shopping_area.exception.AdNotFoundException;
import com.skyteam.shopping_area.exception.UserNotFoundException;
import com.skyteam.shopping_area.mapper.AdsMapper;
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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private final AdsMapper adsMapper;
    private final CheckRoleUserService checkRoleUserService;
    private final ImageService imageService;

    @Override
    public ResponseWrapperAdsDto getAllAds() {
        log.info("Current method is - getAllAds");

        List<Ad> adsList = adsRepository.findAll();
        log.info(adsList.toString());
        return adsMapper.listAdsToAdsDto(adsList.size(), adsList);
    }

    @Override
    public AdDto addAds(CreateOrUpdateAdDto properties, MultipartFile imageFile, String email) {
        log.info("Current method is - addAds");
        Ad saveAd = adsMapper.adsDtoToAds(properties);
        saveAd.setAuthor(userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new));
        Image image;
        try {
            image = imageService.saveImageFile(imageFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        saveAd.setImage(image);
        adsRepository.saveAndFlush(saveAd);
        return adsMapper.adsToAdsDto(saveAd);
    }

    @Override
    public ExtendedAdDto getFullAds(int id) {
        Ad ad = adsRepository.findById(id).orElseThrow(AdNotFoundException::new);
        log.info(String.valueOf(ad));

        return adsMapper.toFullAdsDto(ad);
    }

    @Override
    public boolean removeAdDto(String email, int id) {
        log.info("Current method is - removeAdsDto");
        Optional<Ad> optionalAd = adsRepository.findById(id);

        Ad ad = adsRepository.findById(id).orElseThrow(AdNotFoundException::new);
        User adOwner = ad.getAuthor();
        if (checkRoleUserService.isUserOrAdmin(email, adOwner) && optionalAd.isPresent()) {
            try {
                Files.deleteIfExists(Path.of(ad.getImage().getFilePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            adsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public AdDto updateAdDto(int id, CreateOrUpdateAdDto properties, String email) {
        log.info("Current method is - updateAdsDto");
        Optional<Ad> optionalAd = adsRepository.findById(id);
        if (optionalAd.isPresent()) {
            Ad existingAd = optionalAd.get();
            User adOwner = existingAd.getAuthor();
            if (checkRoleUserService.isUserOrAdmin(email, adOwner)) {
                adsMapper.updateAdsFromCreateAdsDto(properties, existingAd);
                adsRepository.save(existingAd);
                return adsMapper.adsToAdsDto(existingAd);
            }
        } else {
            throw new AdNotFoundException("Объявление не найдено");
        }
        return null;
    }

    @Override
    public ResponseWrapperAdsDto getAllAdsMe(Authentication auth) {
        log.info("Current method is - getAllAdsMe");
        User user = userRepository
                .findUserByEmail(auth.getName())
                .orElseThrow(UserNotFoundException::new);
        log.info("Пользователь авторизован и запустился метод getAdsMe!");
        List<Ad> adsList = adsRepository.findAllByAuthor(user);
        return adsMapper.listAdsToAdsDto(adsList.size(), adsList);
    }

    @Override
    public boolean updateImage(int id, MultipartFile imageFile, String email) {
        log.info("запустился метод updateImage.");
        Ad ad = adsRepository.findById(id).orElseThrow(AdNotFoundException::new);
        User adOwner = ad.getAuthor();
        if (checkRoleUserService.isUserOrAdmin(email, adOwner)) {
            Image image;
            try {
                image = imageService.saveImageFile(imageFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ad.setImage(image);
            adsRepository.save(ad);
            return true;
        }
        return false;
    }
}
