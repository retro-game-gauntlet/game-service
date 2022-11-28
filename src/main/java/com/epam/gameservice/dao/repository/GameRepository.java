package com.epam.gameservice.dao.repository;

import com.epam.gameservice.business.domain.GameDto;
import com.epam.gameservice.dao.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select distinct count(g) from Game g left join g.platform p " +
            "where upper(p.code) = upper(:platformCode)")
    long countByPlatformCode(String platformCode);

    @Query("select distinct new com.epam.gameservice.business.domain.GameDto(g.name, g.releasedAt, p.code) " +
            "from Game g left join g.platform p where upper(p.code) = upper(:platformCode)")
    List<GameDto> findGamesByPlatformCode(String platformCode);

    @Query("select new com.epam.gameservice.business.domain.GameDto(g.name, g.releasedAt, p.code) " +
            "from Game g left join g.platform p where upper(g.name) = upper(:name)")
    Optional<GameDto> findGameByName(String name);
}