package com.solarshare.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NetUsageLedgerDTO {

    private Long id;

    private LocalDate ledgerDate;

    private BigDecimal consumedKwh;

    private BigDecimal allocatedSolarKwh;

    private BigDecimal netUsageKwh;

    private Long householdId;

    private Long solarGenerationId;

    public NetUsageLedgerDTO() {
    }

    public NetUsageLedgerDTO(
            Long id,
            LocalDate ledgerDate,
            BigDecimal consumedKwh,
            BigDecimal allocatedSolarKwh,
            BigDecimal netUsageKwh,
            Long householdId,
            Long solarGenerationId) {

        this.id = id;
        this.ledgerDate = ledgerDate;
        this.consumedKwh = consumedKwh;
        this.allocatedSolarKwh = allocatedSolarKwh;
        this.netUsageKwh = netUsageKwh;
        this.householdId = householdId;
        this.solarGenerationId = solarGenerationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public Long getSolarGenerationId() {
        return solarGenerationId;
    }

    public void setSolarGenerationId(Long solarGenerationId) {
        this.solarGenerationId = solarGenerationId;
    }
}