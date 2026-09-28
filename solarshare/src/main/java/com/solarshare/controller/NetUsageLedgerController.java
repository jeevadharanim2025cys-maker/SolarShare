package com.solarshare.controller;

import com.solarshare.dto.NetUsageLedgerDTO;
import com.solarshare.service.NetUsageLedgerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/net-usage")
public class NetUsageLedgerController {

    private final NetUsageLedgerService ledgerService;

    public NetUsageLedgerController(
            NetUsageLedgerService ledgerService) {

        this.ledgerService = ledgerService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<NetUsageLedgerDTO> calculateLedger(
            @RequestParam Long householdId,
            @RequestParam LocalDate date) {

        return ResponseEntity.ok(
                ledgerService.calculateLedger(
                        householdId,
                        date));
    }

    @GetMapping
    public ResponseEntity<List<NetUsageLedgerDTO>> getAllLedgers() {

        return ResponseEntity.ok(
                ledgerService.getAllLedgers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NetUsageLedgerDTO> getLedgerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ledgerService.getLedgerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLedger(
            @PathVariable Long id) {

        ledgerService.deleteLedger(id);
        return ResponseEntity.noContent().build();
    }
}