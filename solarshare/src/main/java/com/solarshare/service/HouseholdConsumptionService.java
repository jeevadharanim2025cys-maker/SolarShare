package com.solarshare.service;

import com.solarshare.dto.HouseholdConsumptionDTO;
import com.solarshare.entity.Household;
import com.solarshare.entity.HouseholdConsumption;
import com.solarshare.repository.HouseholdConsumptionRepository;
import com.solarshare.repository.HouseholdRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HouseholdConsumptionService {

    private final HouseholdConsumptionRepository consumptionRepository;
    private final HouseholdRepository householdRepository;

    public HouseholdConsumptionService(
            HouseholdConsumptionRepository consumptionRepository,
            HouseholdRepository householdRepository) {

        this.consumptionRepository = consumptionRepository;
        this.householdRepository = householdRepository;
    }

    public HouseholdConsumptionDTO createConsumption(
            HouseholdConsumptionDTO dto) {

        if (consumptionRepository
                .existsByHouseholdIdAndConsumptionDate(
                        dto.getHouseholdId(),
                        dto.getConsumptionDate())) {

            throw new IllegalArgumentException(
                    "Consumption already exists for this household and date");
        }

        Household household = householdRepository
                .findById(dto.getHouseholdId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Household not found"));

        HouseholdConsumption consumption =
                new HouseholdConsumption();

        consumption.setConsumptionDate(
                dto.getConsumptionDate());

        consumption.setConsumedKwh(
                dto.getConsumedKwh());

        consumption.setHousehold(household);

        return toDTO(
                consumptionRepository.save(consumption));
    }

    @Transactional(readOnly = true)
    public List<HouseholdConsumptionDTO> getAllConsumptions() {

        return consumptionRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public HouseholdConsumptionDTO getConsumptionById(
            Long id) {

        HouseholdConsumption consumption =
                consumptionRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Consumption record not found"));

        return toDTO(consumption);
    }

    public void deleteConsumption(Long id) {

        if (!consumptionRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Consumption record not found");
        }

        consumptionRepository.deleteById(id);
    }

    private HouseholdConsumptionDTO toDTO(
            HouseholdConsumption consumption) {

        return new HouseholdConsumptionDTO(
                consumption.getId(),
                consumption.getConsumptionDate(),
                consumption.getConsumedKwh(),
                consumption.getHousehold().getId()
        );
    }
}