package com.solarshare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "solar_generation", uniqueConstraints = {
        @UniqueConstraint(columnNames = "generation_date")
})
public class SolarGeneration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "generation_date", nullable = false)
    private LocalDate generationDate;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "generated_kwh", nullable = false, precision = 10, scale = 2)
    private BigDecimal generatedKwh;

    public SolarGeneration() {
    }

    public SolarGeneration(
            LocalDate generationDate,
            BigDecimal generatedKwh) {

        this.generationDate = generationDate;
        this.generatedKwh = generatedKwh;
    }

    public Long getId() {
        return id;
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