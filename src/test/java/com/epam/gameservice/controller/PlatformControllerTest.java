package com.epam.gameservice.controller;

import com.epam.gameservice.exception.PlatformNotFoundException;
import com.epam.gameservice.service.GameService;
import com.epam.gameservice.service.PlatformService;
import com.epam.gameservice.tags.Spring;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static com.epam.gameservice.factories.GameDtoFactory.marioDto;
import static com.epam.gameservice.factories.PlatformDtoFactory.nesDto;
import static java.nio.file.Files.readAllBytes;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.ResourceUtils.getFile;

@Spring
@Import(PlatformController.class)
@WebMvcTest(PlatformControllerTest.class)
class PlatformControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlatformService platformService;
    @MockBean
    private GameService gameService;

    @Test
    void shouldReturnPlatformResponce() throws Exception {
        when(platformService.findByCode("nes")).thenReturn(nesDto());

        mockMvc.perform(get("/platforms/nes"))
                .andExpect(status().isOk())
                .andExpect(content().json(readJsonFile("classpath:json/platformByCode.json")));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenPlatformNotFound() throws Exception {
        when(platformService.findByCode("qwe")).thenThrow(new PlatformNotFoundException("qwe"));

        mockMvc.perform(get("/platforms/qwe"))
                .andExpect(status().isNotFound())
                .andExpect(content().json(readJsonFile("classpath:json/platformByCodeNotFound.json")));
    }

    @Test
    void shouldReturnGamesByPlatformCode() throws Exception {
        when(gameService.findGamesByPlatformCode("nes")).thenReturn(singletonList(marioDto()));

        mockMvc.perform(get("/platforms/nes/games"))
                .andExpect(status().isOk())
                .andExpect(content().json(readJsonFile("classpath:json/gamesByPlatform.json")));
    }

    @Test
    void shouldCreatePlatform() throws Exception {
        mockMvc.perform(post("/platforms")
                        .contentType(APPLICATION_JSON)
                        .content(readJsonFile("classpath:json/platformDtoRequest.json")))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/platforms/NES")));
    }

    private String readJsonFile(String resourceLocation) throws IOException {
        return new String(readAllBytes(getFile(resourceLocation).toPath()));
    }
}