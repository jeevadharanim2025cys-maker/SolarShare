package com.solarshare.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HouseholdConsumptionDTO {

    private Long id;

    @NotNull(message = "Consumption date is required")
    private LocalDate consumptionDate;

    @NotNull(message = "Consumed energy is required")
    @DecimalMin(value = "0.0", message = "Consumed energy cannot be negative")
    private BigDecimal consumedKwh;

    @NotNull(message = "Household ID is required")
    private Long householdId;

    public HouseholdConsumptionDTO() {
    }

    public HouseholdConsumptionDTO(
            Long id,
            LocalDate consumptionDate,
            BigDecimal consumedKwh,
            Long householdId) {

        this.id = id;
        this.consumptionDate = consumptionDate;
        this.consumedKwh = consumedKwh;
        this.householdId = householdId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(LocalDate consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public BigDecimal getConsumedKwh() {
        return consumedKwh;
    }

    public void setConsumedKwh(BigDecimal consumedKwh) {
        this.consumedKwh = consumedKwh;
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }
}