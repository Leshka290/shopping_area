package com.skyteam.shopping_area.controller;

import com.skyteam.shopping_area.dto.*;
import com.skyteam.shopping_area.service.AdsService;
import com.skyteam.shopping_area.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final CommentService commentService;

    @Operation(summary = "getAllAds")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseWrapperAdsDto[].class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping(value = "/")
    public ResponseEntity<?> getAds() {
        log.info("Request GET ads");
        return ResponseEntity.ok().body(adsService.getAllAds());
    }

    @Operation(summary = "addAds")
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsDto.class))
    )
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAds(@RequestPart CreateAdsDto properties, @RequestPart MultipartFile image) {
        log.info("New ads added {}", properties.getTitle());
        return ResponseEntity.ok(adsService.addAds(properties, image));
    }

    @Operation(summary = "getFullAd")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = FullAdsDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAds(@PathVariable int id) {
        return ResponseEntity.ok(adsService.getFullAds(id));
    }

    @Operation(summary = "removeAds")
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAds(@PathVariable int id) {
        log.info("Remove ads id: {}", id);
        adsService.removeAdsDto(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "updateAds")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable int id, @RequestBody CreateAdsDto createAdsDto) {
        log.info("Update ads id: {}", id);
        return ResponseEntity.ok(adsService.updateAdsDto(id, createAdsDto));
    }

    @Operation(summary = "GetAdsMe")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseWrapperAdsDto[].class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe() {
        log.info("Get an ads from an authorized user");
        return ResponseEntity.ok(adsService.getAllAdsMe());
    }

    @Operation(summary = "getComments")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseWrapperCommentDto[].class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/{adPk}/comments")
    public ResponseEntity<ResponseWrapperCommentDto> getComments(@PathVariable String adPk) {
        log.info("Get comments ads id: {}", adPk);
        return ResponseEntity.ok().body(commentService.getComments(adPk));
    }

    @Operation(summary = "addComments")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CommentDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping("/{adPk}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable String adPk, @RequestBody CommentDto commentDto) {
        log.info("Add comments ads id: {}", adPk);
        return ResponseEntity.ok(commentService.addComment(adPk, commentDto));
    }

    @Operation(summary = "deleteComment")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @DeleteMapping("/{adPk}/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String adPk, @PathVariable int id) {
        log.info("Delete comment: {}", adPk);
        if (commentService.deleteComments(adPk, id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "updateComment")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CommentDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PatchMapping("/{adPk}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable String adPk, @PathVariable int id,
                                                    @RequestBody CommentDto commentDto) {
        log.info("Update comment: {}", adPk);
        return ResponseEntity.ok(commentService.updateComments(adPk, id, commentDto));
    }
}
