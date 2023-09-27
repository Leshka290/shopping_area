package com.skyteam.shopping_area.controller;

import com.skyteam.shopping_area.dto.AdDto;
import com.skyteam.shopping_area.dto.CreateOrUpdateAdDto;
import com.skyteam.shopping_area.dto.ExtendedAdDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import com.skyteam.shopping_area.service.AdsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;

    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseWrapperAdsDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping()
    public ResponseEntity<?> getAds() {
        log.info("Request GET ads");
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @Operation(summary = "Добавление объявления")
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdDto.class))
    )
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAds(@RequestPart("properties") @Valid CreateOrUpdateAdDto properties,
                                        @RequestBody @Valid MultipartFile image,
                                        @NotNull Authentication auth) {
        log.info("New ads added {}", properties.getTitle());
        return ResponseEntity.ok(adsService.addAds(properties, image, auth.getName()));
    }

    @Operation(summary = "Получение информации об объявлении")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExtendedAdDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping( "/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable int id) {
        log.info("Get info ads id: {}", id);
        return ResponseEntity.ok(adsService.getFullAds(id));
    }

    @Operation(summary = "Удаление объявления")
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAds(@NotNull Authentication auth, @PathVariable int id) {
        log.info("Remove ads id: {}", id);
        if (adsService.removeAdDto(auth.getName(), id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Operation(summary = "Обновление информации об объявлениях")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAds(@PathVariable int id, @RequestBody CreateOrUpdateAdDto createAdsDto, @NotNull Authentication auth) {
        log.info("Update ads id: {}", id);
        AdDto adsDto = adsService.updateAdDto(id, createAdsDto, auth.getName());
        if (adsDto != null) {
            return ResponseEntity.ok(adsDto);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Operation(summary = "Получение объявлений авторизированного пользователя")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseWrapperAdsDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe(Authentication auth) {
        log.info("Get an ads from an authorized user");
        ResponseWrapperAdsDto ads = adsService.getAllAdsMe(auth);
        log.info(String.valueOf(ads));
        if (ads != null) {
            return ResponseEntity.ok(ads);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Обновление картинки объявления",
            responses = {@ApiResponse(responseCode = "200", description = "OK"
                    , content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE
                    , schema = @Schema(type = "array", format = "byte"))),
                    @ApiResponse(responseCode = "404",
                            description = "Not Found")}, tags = "Image")
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAdsImage(@NotNull Authentication auth,
                                            @PathVariable Integer id,
                                            @RequestParam MultipartFile imageFile) {
        log.info("Update image ags id: {}", id);
        if (adsService.updateImage(id, imageFile, auth.getName())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
