package com.epam.gameservice.dao.repository;

import com.epam.gameservice.business.domain.PlatformDto;
import com.epam.gameservice.dao.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    @Query("select distinct new com.epam.gameservice.business.domain.PlatformDto(p.code, p.name, count(g.id), p.releasedAt) " +
            "from Platform p left join p.games g group by p.id")
    List<PlatformDto> findAllPlatformDtos();

    @Query("select p from Platform p where upper(p.code) = upper(:code)")
    Optional<Platform> findByCode(String code);

    @Query("select distinct new com.epam.gameservice.business.domain.PlatformDto(p.code, p.name, count(g.id), p.releasedAt) " +
            "from Platform p left join p.games g where upper(p.code) = upper(:code) group by p.id")
    Optional<PlatformDto> findPlatformDtoByCode(String code);

    boolean existsByCodeIgnoreCase(String code);
}