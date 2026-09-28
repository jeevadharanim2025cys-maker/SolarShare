package com.solarshare.repository;

import com.solarshare.entity.NetUsageLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NetUsageLedgerRepository
        extends JpaRepository<NetUsageLedger, Long> {

    List<NetUsageLedger> findByHouseholdId(Long householdId);

    Optional<NetUsageLedger> findByHouseholdIdAndLedgerDate(
            Long householdId,
            LocalDate ledgerDate);

    boolean existsByHouseholdIdAndLedgerDate(
            Long householdId,
            LocalDate ledgerDate);

    void deleteByHouseholdId(Long householdId);

    void deleteBySolarGenerationId(Long solarGenerationId);
}