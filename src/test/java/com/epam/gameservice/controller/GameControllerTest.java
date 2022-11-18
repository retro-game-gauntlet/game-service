package com.epam.gameservice.controller;

import com.epam.gameservice.exception.GameNotFoundException;
import com.epam.gameservice.service.GameService;
import com.epam.gameservice.tags.Spring;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static com.epam.gameservice.factories.GameDtoFactory.marioDto;
import static java.lang.String.format;
import static java.nio.file.Files.readAllBytes;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.ResourceUtils.getFile;

@Spring
@Import(GameController.class)
@WebMvcTest(GameControllerTest.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    void shouldReturnGameResponse() throws Exception {
        when(gameService.findGameByName("Super Mario Bros.")).thenReturn(marioDto());

        mockMvc.perform(get("/games/Super Mario Bros."))
                .andExpect(status().isOk())
                .andExpect(content().json(readJsonFile("gameByName.json")));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenGameNotFound() throws Exception {
        when(gameService.findGameByName("qwe")).thenThrow(new GameNotFoundException("qwe"));

        mockMvc.perform(get("/games/qwe"))
                .andExpect(status().isNotFound())
                .andExpect(content().json(readJsonFile("gameByNameNotFound.json")));
    }

    @Test
    void shouldCreateGame() throws Exception {
        mockMvc.perform(post("/games")
                        .contentType(APPLICATION_JSON)
                        .content(readJsonFile("gameDtoRequest.json")))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/games/Super%20Mario%20Bros.")));
    }

    private String readJsonFile(String resourceLocation) throws IOException {
        return new String(readAllBytes(getFile(format("classpath:json/%s", resourceLocation)).toPath()));
    }
}