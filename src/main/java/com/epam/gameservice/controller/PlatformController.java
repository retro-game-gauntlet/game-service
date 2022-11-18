package com.epam.gameservice.controller;

import com.epam.gameservice.controller.dto.games.GamesDtoData;
import com.epam.gameservice.controller.dto.games.GamesResponse;
import com.epam.gameservice.controller.dto.platforms.PlatformDtoRequest;
import com.epam.gameservice.controller.dto.platforms.PlatformResponse;
import com.epam.gameservice.controller.mapper.PlatformResponseMapper;
import com.epam.gameservice.domain.GameDto;
import com.epam.gameservice.domain.PlatformDto;
import com.epam.gameservice.service.GameService;
import com.epam.gameservice.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/platforms")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformService platformService;
    private final GameService gameService;

    @GetMapping(value = "/{code}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PlatformResponse> getByPlatform(@PathVariable String code) {
        PlatformDto platformDto = platformService.findPlatformDtoByCode(code);
        PlatformResponse response = PlatformResponseMapper.INSTANCE.map(platformDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{code}/games", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GamesResponse> getGamesByPlatform(@PathVariable String code) {
        List<GameDto> gameDtos = gameService.findGamesByPlatformCode(code);
        GamesResponse response = buildGamesResponse(gameDtos);
        return ResponseEntity.ok(response);
    }

    private GamesResponse buildGamesResponse(List<GameDto> gameDtos) {
        return GamesResponse.builder()
                .data(GamesDtoData.builder().attributes(gameDtos).build())
                .build();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> createPlatform(@RequestBody PlatformDtoRequest request, UriComponentsBuilder cb) {
        platformService.save(request);
        UriComponents uriComponents = cb.path("/platforms/{code}").buildAndExpand(request.code());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}