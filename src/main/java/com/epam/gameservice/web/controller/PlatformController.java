package com.epam.gameservice.web.controller;

import com.epam.gameservice.business.domain.GameDto;
import com.epam.gameservice.business.domain.PlatformDto;
import com.epam.gameservice.business.service.GameService;
import com.epam.gameservice.business.service.PlatformService;
import com.epam.gameservice.web.dto.Response;
import com.epam.gameservice.web.dto.builder.GenericResponseBuilder;
import com.epam.gameservice.web.dto.platforms.PlatformDtoRequest;
import com.epam.methodlog.annotation.InputMethodLog;
import com.epam.methodlog.annotation.OutputMethodLog;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/platforms")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformService platformService;
    private final GameService gameService;
    private final GenericResponseBuilder genericResponseBuilder;

    @OutputMethodLog
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<PlatformDto>>> getAllPlatforms() {
        List<PlatformDto> platformDtos = platformService.findAllPlatformDtos();
        Response<List<PlatformDto>> response = genericResponseBuilder.buildResponse(platformDtos);
        return ResponseEntity.ok(response);
    }

    @InputMethodLog
    @OutputMethodLog
    @GetMapping(value = "/{code}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<PlatformDto>> getPlatformByPlatformCode(@PathVariable String code) {
        PlatformDto platformDto = platformService.findPlatformDtoByCode(code);
        Response<PlatformDto> response = genericResponseBuilder.buildResponse(platformDto);
        return ResponseEntity.ok(response);
    }

    @InputMethodLog
    @OutputMethodLog
    @GetMapping(value = "/{code}/games", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<GameDto>>> getGamesByPlatformCode(@PathVariable String code) {
        List<GameDto> gameDtos = gameService.findGamesByPlatformCode(code);
        Response<List<GameDto>> response = genericResponseBuilder.buildResponse(gameDtos);
        return ResponseEntity.ok(response);
    }

    @InputMethodLog
    @OutputMethodLog
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> createPlatform(@Valid @RequestBody PlatformDtoRequest request, UriComponentsBuilder cb) {
        platformService.save(request);
        UriComponents uriComponents = cb.path("/v1/platforms/{code}").buildAndExpand(request.code());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @InputMethodLog
    @OutputMethodLog
    @GetMapping("/{code}/games/random")
    public ResponseEntity<URI> getRandomGameByPlatformCode(@PathVariable String code, UriComponentsBuilder cb) {
        String gameName = gameService.findRandomGameNameByPlatformCode(code);
        UriComponents uriComponents = cb.path("/v1/games/{gameName}").buildAndExpand(gameName);
        return ResponseEntity.status(HttpStatus.FOUND).location(uriComponents.toUri()).build();
    }
}