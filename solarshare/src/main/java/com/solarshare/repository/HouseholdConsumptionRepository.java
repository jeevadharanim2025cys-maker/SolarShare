package com.solarshare.repository;

import com.solarshare.entity.HouseholdConsumption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HouseholdConsumptionRepository
        extends JpaRepository<HouseholdConsumption, Long> {

    List<HouseholdConsumption> findByHouseholdId(Long householdId);

    Optional<HouseholdConsumption> findByHouseholdIdAndConsumptionDate(
            Long householdId,
            LocalDate consumptionDate);

    boolean existsByHouseholdIdAndConsumptionDate(
            Long householdId,
            LocalDate consumptionDate);

    void deleteByHouseholdId(Long householdId);
}