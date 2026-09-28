package com.solarshare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "household_consumption", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "household_id", "consumption_date" })
})
public class HouseholdConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "consumption_date", nullable = false)
    private LocalDate consumptionDate;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "consumed_kwh", nullable = false, precision = 10, scale = 2)
    private BigDecimal consumedKwh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    public HouseholdConsumption() {
    }

    public HouseholdConsumption(
            LocalDate consumptionDate,
            BigDecimal consumedKwh,
            Household household) {

        this.consumptionDate = consumptionDate;
        this.consumedKwh = consumedKwh;
        this.household = household;
    }

    public Long getId() {
        return id;
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

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }
}