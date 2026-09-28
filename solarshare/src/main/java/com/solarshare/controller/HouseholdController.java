package com.solarshare.controller;

import com.solarshare.dto.HouseholdDTO;
import com.solarshare.service.HouseholdService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    public ResponseEntity<HouseholdDTO> createHousehold(
            @Valid @RequestBody HouseholdDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(householdService.createHousehold(dto));
    }

    @GetMapping
    public ResponseEntity<List<HouseholdDTO>> getAllHouseholds() {
        return ResponseEntity.ok(
                householdService.getAllHouseholds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseholdDTO> getHouseholdById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                householdService.getHouseholdById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHousehold(
            @PathVariable Long id) {

        householdService.deleteHousehold(id);
        return ResponseEntity.noContent().build();
    }
}