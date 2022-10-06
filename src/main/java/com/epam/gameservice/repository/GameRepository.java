package com.epam.gameservice.repository;

import com.epam.gameservice.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select distinct count(g) from Game g left join g.platform p " +
            "where upper(p.code) = upper(:platformCode)")
    long countByPlatformCode(String platformCode);
}