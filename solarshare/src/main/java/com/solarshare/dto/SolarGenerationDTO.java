package com.solarshare.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SolarGenerationDTO {

    private Long id;

    @NotNull(message = "Generation date is required")
    private LocalDate generationDate;

    @NotNull(message = "Generated energy is required")
    @DecimalMin(value = "0.0", message = "Generated energy cannot be negative")
    private BigDecimal generatedKwh;

    public SolarGenerationDTO() {
    }

    public SolarGenerationDTO(
            Long id,
            LocalDate generationDate,
            BigDecimal generatedKwh) {

        this.id = id;
        this.generationDate = generationDate;
        this.generatedKwh = generatedKwh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(LocalDate generationDate) {
        this.generationDate = generationDate;
    }

    public BigDecimal getGeneratedKwh() {
        return generatedKwh;
    }

    public void setGeneratedKwh(BigDecimal generatedKwh) {
        this.generatedKwh = generatedKwh;
    }
}