package com.epam.gameservice.web.controller;

import com.epam.gameservice.business.annotation.InputMethodLog;
import com.epam.gameservice.business.annotation.OutputMethodLog;
import com.epam.gameservice.business.domain.GameDto;
import com.epam.gameservice.business.service.GameService;
import com.epam.gameservice.web.dto.games.GameDtoData;
import com.epam.gameservice.web.dto.games.GameDtoRequest;
import com.epam.gameservice.web.dto.games.GameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @InputMethodLog
    @OutputMethodLog
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> createGame(@Valid @RequestBody GameDtoRequest request, UriComponentsBuilder cb) {
        gameService.save(request);
        UriComponents uriComponents = cb.path("/games/{name}").buildAndExpand(request.name());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @InputMethodLog
    @OutputMethodLog
    @GetMapping(value = "/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GameResponse> getGame(@PathVariable String name) {
        GameDto gameDto = gameService.findGameByName(name);
        GameResponse response = buildGameResponse(gameDto);
        return ResponseEntity.ok(response);
    }

    private GameResponse buildGameResponse(GameDto gameDto) {
        return GameResponse.builder()
                .data(GameDtoData.builder().attributes(gameDto).build())
                .build();
    }
}
