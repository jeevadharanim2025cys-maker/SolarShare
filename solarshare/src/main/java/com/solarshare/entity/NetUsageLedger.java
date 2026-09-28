package com.solarshare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "net_usage_ledger", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "household_id", "ledger_date" })
})
public class NetUsageLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ledger_date", nullable = false)
    private LocalDate ledgerDate;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "consumed_kwh", nullable = false, precision = 10, scale = 2)
    private BigDecimal consumedKwh;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "allocated_solar_kwh", nullable = false, precision = 10, scale = 2)
    private BigDecimal allocatedSolarKwh;

    @NotNull
    @Column(name = "net_usage_kwh", nullable = false, precision = 10, scale = 2)
    private BigDecimal netUsageKwh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id", nullable = false)
    private Household household;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solar_generation_id", nullable = false)
    private SolarGeneration solarGeneration;

    public NetUsageLedger() {
    }

    public NetUsageLedger(
            LocalDate ledgerDate,
            BigDecimal consumedKwh,
            BigDecimal allocatedSolarKwh,
            BigDecimal netUsageKwh,
            Household household,
            SolarGeneration solarGeneration) {

        this.ledgerDate = ledgerDate;
        this.consumedKwh = consumedKwh;
        this.allocatedSolarKwh = allocatedSolarKwh;
        this.netUsageKwh = netUsageKwh;
        this.household = household;
        this.solarGeneration = solarGeneration;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getLedgerDate() {
        return ledgerDate;
    }

    public void setLedgerDate(LocalDate ledgerDate) {
        this.ledgerDate = ledgerDate;
    }

    public BigDecimal getConsumedKwh() {
        return consumedKwh;
    }

    public void setConsumedKwh(BigDecimal consumedKwh) {
        this.consumedKwh = consumedKwh;
    }

    public BigDecimal getAllocatedSolarKwh() {
        return allocatedSolarKwh;
    }

    public void setAllocatedSolarKwh(BigDecimal allocatedSolarKwh) {
        this.allocatedSolarKwh = allocatedSolarKwh;
    }

    public BigDecimal getNetUsageKwh() {
        return netUsageKwh;
    }

    public void setNetUsageKwh(BigDecimal netUsageKwh) {
        this.netUsageKwh = netUsageKwh;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public SolarGeneration getSolarGeneration() {
        return solarGeneration;
    }

    public void setSolarGeneration(SolarGeneration solarGeneration) {
        this.solarGeneration = solarGeneration;
    }
}