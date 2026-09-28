package com.solarshare.repository;

import com.solarshare.entity.SolarGeneration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SolarGenerationRepository extends JpaRepository<SolarGeneration, Long> {

    Optional<SolarGeneration> findByGenerationDate(LocalDate generationDate);

    boolean existsByGenerationDate(LocalDate generationDate);
}