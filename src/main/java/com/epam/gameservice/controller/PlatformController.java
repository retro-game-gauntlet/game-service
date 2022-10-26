package com.epam.gameservice.controller;

import com.epam.gameservice.controller.dto.games.GameDtoData;
import com.epam.gameservice.controller.dto.games.GameResponse;
import com.epam.gameservice.controller.dto.platforms.PlatformResponse;
import com.epam.gameservice.controller.mapper.PlatformResponseMapper;
import com.epam.gameservice.domain.GameDto;
import com.epam.gameservice.domain.PlatformDto;
import com.epam.gameservice.service.GameService;
import com.epam.gameservice.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/platforms")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformService platformService;
    private final GameService gameService;

    @GetMapping("/{code}")
    public ResponseEntity<PlatformResponse> getByPlatform(@PathVariable String code) {
        PlatformDto platformDto = platformService.findByCode(code);
        PlatformResponse response = PlatformResponseMapper.INSTANCE.map(platformDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{code}/games")
    public ResponseEntity<GameResponse> getGamesByPlatform(@PathVariable String code) {
        List<GameDto> gameDtos = gameService.findGamesByPlatformCode(code);
        GameResponse response = buildGameResponse(gameDtos);
        return ResponseEntity.ok(response);
    }

    private GameResponse buildGameResponse(List<GameDto> gameDtos) {
        return GameResponse.builder()
                .data(GameDtoData.builder().attributes(gameDtos).build())
                .build();
    }
}