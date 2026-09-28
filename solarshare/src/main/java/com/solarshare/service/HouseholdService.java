package com.solarshare.service;

import com.solarshare.dto.HouseholdDTO;
import com.solarshare.entity.Household;
import com.solarshare.entity.User;
import com.solarshare.repository.HouseholdConsumptionRepository;
import com.solarshare.repository.HouseholdRepository;
import com.solarshare.repository.NetUsageLedgerRepository;
import com.solarshare.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HouseholdService {

    private final HouseholdRepository householdRepository;
    private final UserRepository userRepository;
    private final HouseholdConsumptionRepository consumptionRepository;
    private final NetUsageLedgerRepository ledgerRepository;

    public HouseholdService(
            HouseholdRepository householdRepository,
            UserRepository userRepository,
            HouseholdConsumptionRepository consumptionRepository,
            NetUsageLedgerRepository ledgerRepository) {

        this.householdRepository = householdRepository;
        this.userRepository = userRepository;
        this.consumptionRepository = consumptionRepository;
        this.ledgerRepository = ledgerRepository;
    }

    public HouseholdDTO createHousehold(HouseholdDTO dto) {

        if (householdRepository.existsByHouseholdName(
                dto.getHouseholdName())) {

            throw new IllegalArgumentException(
                    "Household name already exists");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        Household household = new Household();
        household.setHouseholdName(dto.getHouseholdName());
        household.setUser(user);

        return toDTO(
                householdRepository.save(household));
    }

    @Transactional(readOnly = true)
    public List<HouseholdDTO> getAllHouseholds() {

        return householdRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public HouseholdDTO getHouseholdById(Long id) {

        Household household = householdRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Household not found"));

        return toDTO(household);
    }

    public void deleteHousehold(Long id) {

        if (!householdRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Household not found");
        }

        // Delete dependent records first
        ledgerRepository.deleteByHouseholdId(id);
        consumptionRepository.deleteByHouseholdId(id);

        // Delete household
        householdRepository.deleteById(id);
    }

    private HouseholdDTO toDTO(Household household) {

        return new HouseholdDTO(
                household.getId(),
                household.getHouseholdName(),
                household.getUser().getId()
        );
    }
}