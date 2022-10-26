package com.epam.gameservice.service;

import com.epam.gameservice.domain.GameDto;
import com.epam.gameservice.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameService {

    private final GameRepository gameRepository;

    public List<GameDto> findGamesByPlatformCode(String platformCode) {
        return gameRepository.findGamesByPlatformCode(platformCode);
    }
}
