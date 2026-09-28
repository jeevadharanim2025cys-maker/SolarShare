package com.solarshare.service;

import com.solarshare.dto.SolarGenerationDTO;
import com.solarshare.entity.SolarGeneration;
import com.solarshare.repository.NetUsageLedgerRepository;
import com.solarshare.repository.SolarGenerationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SolarGenerationService {

    private final SolarGenerationRepository solarGenerationRepository;
    private final NetUsageLedgerRepository ledgerRepository;

    public SolarGenerationService(
            SolarGenerationRepository solarGenerationRepository,
            NetUsageLedgerRepository ledgerRepository) {

        this.solarGenerationRepository = solarGenerationRepository;
        this.ledgerRepository = ledgerRepository;
    }

    public SolarGenerationDTO createGeneration(
            SolarGenerationDTO dto) {

        if (solarGenerationRepository
                .existsByGenerationDate(dto.getGenerationDate())) {

            throw new IllegalArgumentException(
                    "Solar generation already exists for this date");
        }

        SolarGeneration generation = new SolarGeneration();

        generation.setGenerationDate(
                dto.getGenerationDate());

        generation.setGeneratedKwh(
                dto.getGeneratedKwh());

        return toDTO(
                solarGenerationRepository.save(generation));
    }

    @Transactional(readOnly = true)
    public List<SolarGenerationDTO> getAllGenerations() {

        return solarGenerationRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public SolarGenerationDTO getGenerationById(Long id) {

        SolarGeneration generation = solarGenerationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Solar generation record not found"));

        return toDTO(generation);
    }

    public void deleteGeneration(Long id) {

        if (!solarGenerationRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "Solar generation record not found");
        }

        // Delete ledger records that use this generation
        ledgerRepository.deleteBySolarGenerationId(id);

        // Delete generation
        solarGenerationRepository.deleteById(id);
    }

    private SolarGenerationDTO toDTO(
            SolarGeneration generation) {

        return new SolarGenerationDTO(
                generation.getId(),
                generation.getGenerationDate(),
                generation.getGeneratedKwh());
    }
}