package com.solarshare.service;

import com.solarshare.dto.NetUsageLedgerDTO;
import com.solarshare.entity.Household;
import com.solarshare.entity.HouseholdConsumption;
import com.solarshare.entity.NetUsageLedger;
import com.solarshare.entity.SolarGeneration;
import com.solarshare.repository.HouseholdConsumptionRepository;
import com.solarshare.repository.HouseholdRepository;
import com.solarshare.repository.NetUsageLedgerRepository;
import com.solarshare.repository.SolarGenerationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class NetUsageLedgerService {

    private final NetUsageLedgerRepository ledgerRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdConsumptionRepository consumptionRepository;
    private final SolarGenerationRepository generationRepository;

    public NetUsageLedgerService(
            NetUsageLedgerRepository ledgerRepository,
            HouseholdRepository householdRepository,
            HouseholdConsumptionRepository consumptionRepository,
            SolarGenerationRepository generationRepository) {

        this.ledgerRepository = ledgerRepository;
        this.householdRepository = householdRepository;
        this.consumptionRepository = consumptionRepository;
        this.generationRepository = generationRepository;
    }

    public NetUsageLedgerDTO calculateLedger(
            Long householdId,
            LocalDate date) {

        Household household = householdRepository
                .findById(householdId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Household not found"));

        HouseholdConsumption consumption = consumptionRepository
                .findByHouseholdIdAndConsumptionDate(
                        householdId,
                        date)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Consumption record not found for this date"));

        SolarGeneration generation = generationRepository
                .findByGenerationDate(date)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Solar generation record not found for this date"));

        long householdCount = householdRepository.count();

        if (householdCount == 0) {
            throw new IllegalArgumentException(
                    "No households available");
        }

        BigDecimal allocatedSolar = generation.getGeneratedKwh()
                .divide(
                        BigDecimal.valueOf(householdCount),
                        2,
                        RoundingMode.HALF_UP);

        BigDecimal netUsage = consumption.getConsumedKwh()
                .subtract(allocatedSolar);

        NetUsageLedger ledger = ledgerRepository
                .findByHouseholdIdAndLedgerDate(
                        householdId,
                        date)
                .orElse(new NetUsageLedger());

        ledger.setLedgerDate(date);
        ledger.setConsumedKwh(
                consumption.getConsumedKwh());
        ledger.setAllocatedSolarKwh(
                allocatedSolar);
        ledger.setNetUsageKwh(
                netUsage);
        ledger.setHousehold(household);
        ledger.setSolarGeneration(generation);

        return toDTO(ledgerRepository.save(ledger));
    }

    @Transactional(readOnly = true)
    public List<NetUsageLedgerDTO> getAllLedgers() {

        return ledgerRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public NetUsageLedgerDTO getLedgerById(Long id) {

        NetUsageLedger ledger = ledgerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Ledger record not found"));

        return toDTO(ledger);
    }

    public void deleteLedger(Long id) {

        if (!ledgerRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Ledger record not found");
        }

        ledgerRepository.deleteById(id);
    }

    private NetUsageLedgerDTO toDTO(
            NetUsageLedger ledger) {

        return new NetUsageLedgerDTO(
                ledger.getId(),
                ledger.getLedgerDate(),
                ledger.getConsumedKwh(),
                ledger.getAllocatedSolarKwh(),
                ledger.getNetUsageKwh(),
                ledger.getHousehold().getId(),
                ledger.getSolarGeneration().getId());
    }
}