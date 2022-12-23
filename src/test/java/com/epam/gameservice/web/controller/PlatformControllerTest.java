package com.epam.gameservice.web.controller;

import com.epam.gameservice.business.exception.PlatformNotFoundException;
import com.epam.gameservice.business.service.GameService;
import com.epam.gameservice.business.service.PlatformService;
import com.epam.gameservice.tags.Spring;
import com.epam.gameservice.web.dto.builder.GenericResponseBuilder;
import com.epam.gameservice.web.jsonreader.GameJsonReader;
import com.epam.gameservice.web.jsonreader.PlatformJsonReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static com.epam.gameservice.factories.GameDtoFactory.marioDto;
import static com.epam.gameservice.factories.PlatformDtoFactory.nesDto;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Spring
@Import(GenericResponseBuilder.class)
@WebMvcTest(PlatformController.class)
class PlatformControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlatformService platformService;
    @MockBean
    private GameService gameService;

    @Test
    void shouldReturnPlatformResponce() throws Exception {
        when(platformService.findPlatformDtoByCode("nes")).thenReturn(nesDto());

        mockMvc.perform(get("/v1/platforms/nes"))
                .andExpect(status().isOk())
                .andExpect(content().json(new PlatformJsonReader("platformByCode.json").read()));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenPlatformNotFound() throws Exception {
        when(platformService.findPlatformDtoByCode("qwe")).thenThrow(new PlatformNotFoundException("qwe"));

        mockMvc.perform(get("/v1/platforms/qwe"))
                .andExpect(status().isNotFound())
                .andExpect(content().json(new PlatformJsonReader("platformByCodeNotFound.json").read()));
    }

    @Test
    void shouldReturnGamesByPlatformCode() throws Exception {
        when(gameService.findGamesByPlatformCode("nes")).thenReturn(singletonList(marioDto()));

        mockMvc.perform(get("/v1/platforms/nes/games"))
                .andExpect(status().isOk())
                .andExpect(content().json(new GameJsonReader("gamesByPlatform.json").read()));
    }

    @Test
    void shouldCreatePlatform() throws Exception {
        mockMvc.perform(post("/v1/platforms")
                        .contentType(APPLICATION_JSON)
                        .content(new PlatformJsonReader("platformDtoRequest.json").read()))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/platforms/NES")));
    }

    @Test
    void shouldThrowExceptionWhenPlatformDtoRequestIsIncorrect() throws Exception {
        mockMvc.perform(post("/v1/platforms")
                        .contentType(APPLICATION_JSON)
                        .content(new PlatformJsonReader("platformDtoIncorrectRequest.json").read()))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(new PlatformJsonReader("platformDtoIncorrectRequestResponse.json").read()));
    }

    @Test
    void shouldReturnAllPlatforms() throws Exception {
        when(platformService.findAllPlatformDtos()).thenReturn(singletonList(nesDto()));

        mockMvc.perform(get("/v1/platforms"))
                .andExpect(status().isOk())
                .andExpect(content().json(new PlatformJsonReader("allPlatforms.json").read()));
    }

    @Test
    void shouldReturnRedirectForRandomGame() throws Exception {
        when(gameService.findRandomGameNameByPlatformCode("NES")).thenReturn("Super Mario Bros.");

        mockMvc.perform(get("/v1/platforms/NES/games/random"))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", containsString("/v1/games/Super%20Mario%20Bros.")));
    }
}