package com.solarshare.controller;

import com.solarshare.dto.SolarGenerationDTO;
import com.solarshare.service.SolarGenerationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solar-generation")
public class SolarGenerationController {

    private final SolarGenerationService solarGenerationService;

    public SolarGenerationController(
            SolarGenerationService solarGenerationService) {

        this.solarGenerationService = solarGenerationService;
    }

    @PostMapping
    public ResponseEntity<SolarGenerationDTO> createGeneration(
            @Valid @RequestBody SolarGenerationDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solarGenerationService.createGeneration(dto));
    }

    @GetMapping
    public ResponseEntity<List<SolarGenerationDTO>> getAllGenerations() {
        return ResponseEntity.ok(
                solarGenerationService.getAllGenerations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolarGenerationDTO> getGenerationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                solarGenerationService.getGenerationById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGeneration(
            @PathVariable Long id) {

        solarGenerationService.deleteGeneration(id);
        return ResponseEntity.noContent().build();
    }
}