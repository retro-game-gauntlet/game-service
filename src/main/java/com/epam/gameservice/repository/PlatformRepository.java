package com.epam.gameservice.repository;

import com.epam.gameservice.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    @Query("select p from Platform p where upper(p.code) = upper(:code)")
    Platform findByCode(String code);
}