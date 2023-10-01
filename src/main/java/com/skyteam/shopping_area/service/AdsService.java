package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.AdDto;
import com.skyteam.shopping_area.dto.CreateOrUpdateAdDto;
import com.skyteam.shopping_area.dto.ExtendedAdDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

/**
 * Интерфейс для работы с объявлениями
 *
 * @author leshka290
 */
public interface AdsService {

    /**
     * Получает все объявления
     *
     * @return объект {@link ResponseWrapperAdsDto}
     */
    ResponseWrapperAdsDto getAllAds();

    /**
     * Добавляет объявление
     *
     * @param properties объект {@link CreateOrUpdateAdDto}
     * @param image      объект {@link MultipartFile}
     * @param email      e-mail (логин) пользователя
     * @return объект {@link AdDto}
     */
    AdDto addAds(CreateOrUpdateAdDto properties, MultipartFile image, String email);

    /**
     * Получает информацию об объявлении
     *
     * @param adsId идентификатор объявления
     * @return объект {@link ExtendedAdDto}
     */
    ExtendedAdDto getFullAds(int adsId);

    /**
     * Удаляет объявление
     *
     * @param email e-mail (логин) пользователя
     * @param adsId идентификатор объявления
     * @return <code>true</code> если объявление удалено, <code>false</code> в случае неудачи
     */
    boolean removeAdDto(String email, int adsId);

    /**
     * Обновляет информацию об объявлении
     *
     * @param adsId        идентификатор объявления
     * @param createAdsDto новая информация об объявлении
     * @param email        e-mail (логин) пользователя
     * @return объект {@link AdDto}
     */
    AdDto updateAdDto(int adsId, CreateOrUpdateAdDto createAdsDto, String email);

    /**
     * Возвращает объявления только авторизованного пользователя
     *
     * @param auth {@link Authentication} данные о текущем пользователе
     * @return объект {@link ResponseWrapperAdsDto} список объявлений
     */
    ResponseWrapperAdsDto getAllAdsMe(Authentication auth);

    /**
     * Обновляет картинку объявления
     *
     * @param id        идентификатор объявления
     * @param imageFile {@link MultipartFile} новая картинка
     * @param email     e-mail (логин) пользователя
     * @return <code>true</code> картинка обновлена, <code>false</code> в случае неудачи
     */
    boolean updateImage(int id, MultipartFile imageFile, String email);

}
