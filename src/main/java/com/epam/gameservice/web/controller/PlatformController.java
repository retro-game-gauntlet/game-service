package com.epam.gameservice.web.controller;

import com.epam.gameservice.business.annotation.InputMethodLog;
import com.epam.gameservice.business.annotation.OutputMethodLog;
import com.epam.gameservice.business.domain.GameDto;
import com.epam.gameservice.business.domain.PlatformDto;
import com.epam.gameservice.business.mapper.PlatformMapper;
import com.epam.gameservice.business.service.GameService;
import com.epam.gameservice.business.service.PlatformService;
import com.epam.gameservice.web.dto.games.GamesDtoData;
import com.epam.gameservice.web.dto.games.GamesResponse;
import com.epam.gameservice.web.dto.platforms.PlatformDtoRequest;
import com.epam.gameservice.web.dto.platforms.PlatformResponse;
import com.epam.gameservice.web.dto.platforms.PlatformsDtoData;
import com.epam.gameservice.web.dto.platforms.PlatformsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/platforms")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformService platformService;
    private final GameService gameService;

    @OutputMethodLog
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PlatformsResponse> getAllPlatforms() {
        List<PlatformDto> platformDtos = platformService.findAllPlatformDtos();
        PlatformsResponse response = buildPlatformsResponse(platformDtos);
        return ResponseEntity.ok(response);
    }

    @InputMethodLog
    @OutputMethodLog
    @GetMapping(value = "/{code}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PlatformResponse> getByPlatform(@PathVariable String code) {
        PlatformDto platformDto = platformService.findPlatformDtoByCode(code);
        PlatformResponse response = PlatformMapper.INSTANCE.map(platformDto);
        return ResponseEntity.ok(response);
    }

    @InputMethodLog
    @OutputMethodLog
    @GetMapping(value = "/{code}/games", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GamesResponse> getGamesByPlatform(@PathVariable String code) {
        List<GameDto> gameDtos = gameService.findGamesByPlatformCode(code);
        GamesResponse response = buildGamesResponse(gameDtos);
        return ResponseEntity.ok(response);
    }

    @InputMethodLog
    @OutputMethodLog
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> createPlatform(@Valid @RequestBody PlatformDtoRequest request, UriComponentsBuilder cb) {
        platformService.save(request);
        UriComponents uriComponents = cb.path("/platforms/{code}").buildAndExpand(request.code());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    private PlatformsResponse buildPlatformsResponse(List<PlatformDto> platformDtos) {
        return PlatformsResponse.builder()
                .data(PlatformsDtoData.builder().attributes(platformDtos).build())
                .build();
    }

    private GamesResponse buildGamesResponse(List<GameDto> gameDtos) {
        return GamesResponse.builder()
                .data(GamesDtoData.builder().attributes(gameDtos).build())
                .build();
    }
}