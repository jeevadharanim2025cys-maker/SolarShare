package com.solarshare.controller;

import com.solarshare.dto.HouseholdConsumptionDTO;
import com.solarshare.service.HouseholdConsumptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumption")
public class HouseholdConsumptionController {

    private final HouseholdConsumptionService consumptionService;

    public HouseholdConsumptionController(
            HouseholdConsumptionService consumptionService) {

        this.consumptionService = consumptionService;
    }

    @PostMapping
    public ResponseEntity<HouseholdConsumptionDTO> createConsumption(
            @Valid @RequestBody HouseholdConsumptionDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(consumptionService.createConsumption(dto));
    }

    @GetMapping
    public ResponseEntity<List<HouseholdConsumptionDTO>> getAllConsumptions() {

        return ResponseEntity.ok(
                consumptionService.getAllConsumptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseholdConsumptionDTO> getConsumptionById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                consumptionService.getConsumptionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsumption(
            @PathVariable Long id) {

        consumptionService.deleteConsumption(id);
        return ResponseEntity.noContent().build();
    }
}