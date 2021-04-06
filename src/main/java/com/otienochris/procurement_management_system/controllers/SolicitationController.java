package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.SolicitationDto;
import com.otienochris.procurement_management_system.responses.SolicitationResponse;
import com.otienochris.procurement_management_system.services.SolicitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/solicitations")
@RequiredArgsConstructor
public class SolicitationController {

    private final SolicitationService solicitationService;

    @GetMapping("/all")
    public ResponseEntity<List<SolicitationResponse>> getAllSolicitations() {
        return new ResponseEntity<>(solicitationService.getAllSolicitations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitationResponse> getSolicitationById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(solicitationService.getSolicitationById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<SolicitationResponse> saveSolicitation(@RequestBody @Validated SolicitationDto solicitationDto) {
        return new ResponseEntity<>(solicitationService.saveSolication(solicitationDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSolicitation(UUID id) {
        solicitationService.deleteSolicitation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSolicitation(@PathVariable("id") UUID id, @RequestBody @Validated SolicitationDto solicitationDto) {
        solicitationService.updateSolicitation(id, solicitationDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
