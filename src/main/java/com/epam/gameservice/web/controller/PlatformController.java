package com.epam.gameservice.web.controller;

import com.epam.gameservice.business.annotation.InputMethodLog;
import com.epam.gameservice.business.annotation.OutputMethodLog;
import com.epam.gameservice.business.domain.GameDto;
import com.epam.gameservice.business.domain.PlatformDto;
import com.epam.gameservice.business.mapper.PlatformMapper;
import com.epam.gameservice.business.service.GameService;
import com.epam.gameservice.business.service.PlatformService;
import com.epam.gameservice.web.dto.Data;
import com.epam.gameservice.web.dto.Response;
import com.epam.gameservice.web.dto.platforms.PlatformDtoRequest;
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
    public ResponseEntity<Response<List<PlatformDto>>> getAllPlatforms() {
        List<PlatformDto> platformDtos = platformService.findAllPlatformDtos();
        Response<List<PlatformDto>> response = buildPlatformsResponse(platformDtos);
        return ResponseEntity.ok(response);
    }

    @InputMethodLog
    @OutputMethodLog
    @GetMapping(value = "/{code}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<PlatformDto>> getByPlatform(@PathVariable String code) {
        PlatformDto platformDto = platformService.findPlatformDtoByCode(code);
        Response<PlatformDto> response = PlatformMapper.INSTANCE.map(platformDto);
        return ResponseEntity.ok(response);
    }

    @InputMethodLog
    @OutputMethodLog
    @GetMapping(value = "/{code}/games", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<GameDto>>> getGamesByPlatform(@PathVariable String code) {
        List<GameDto> gameDtos = gameService.findGamesByPlatformCode(code);
        Response<List<GameDto>> response = buildGamesResponse(gameDtos);
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

    private Response<List<PlatformDto>> buildPlatformsResponse(List<PlatformDto> platformDtos) {
        return Response.<List<PlatformDto>>builder()
                .data(Data.<List<PlatformDto>>builder().attributes(platformDtos).build())
                .build();
    }

    private Response<List<GameDto>> buildGamesResponse(List<GameDto> gameDtos) {
        return Response.<List<GameDto>>builder()
                .data(Data.<List<GameDto>>builder().attributes(gameDtos).build())
                .build();
    }
}