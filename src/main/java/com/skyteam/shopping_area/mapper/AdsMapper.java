package com.skyteam.shopping_area.mapper;

import com.skyteam.shopping_area.dto.AdDto;
import com.skyteam.shopping_area.dto.CreateOrUpdateAdDto;
import com.skyteam.shopping_area.dto.ExtendedAdDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import com.skyteam.shopping_area.model.Ad;
import org.mapstruct.*;

import java.util.List;

/**
 * Интерфейс для преобразования сущности объявления в дто и обратно
 *
 * @author leshka290
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdsMapper {

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "image", expression = "java(ad.getImage() != null ? ad.getImage().getUrl() : \"\")")
    AdDto adsToAdsDto(Ad ad);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "author", target = "author.id")
    @Mapping(target = "image", expression = "java(new com.skyteam.shopping_area.model.Image())")
    Ad adsDtoToAds(AdDto adsDto);

    ResponseWrapperAdsDto listAdsToAdsDto(int count, List<Ad> results);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "image", expression = "java(ad.getImage() != null ? ad.getImage().getUrl() : \"\")")
    @Mapping(target = "pk", source = "id")
    ExtendedAdDto toFullAdsDto(Ad ad);

    void updateAdsFromCreateAdsDto(CreateOrUpdateAdDto createOrUpdateAdDto, @MappingTarget Ad ad);

    Ad adsDtoToAds(CreateOrUpdateAdDto createOrUpdateAdDto);
}
