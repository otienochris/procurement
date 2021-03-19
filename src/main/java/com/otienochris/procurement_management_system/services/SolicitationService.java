package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.SolicitationDto;
import com.otienochris.procurement_management_system.mappers.SolicitationMapper;
import com.otienochris.procurement_management_system.models.Solicitation;
import com.otienochris.procurement_management_system.repositories.SolicitationRepository;
import com.otienochris.procurement_management_system.responses.SolicitationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SolicitationService {

    private final SolicitationRepository solicitationRepository;
    private final SolicitationMapper solicitationMapper;

    public List<SolicitationResponse> getAllSolicitations(){
        List<SolicitationResponse>  responses = new ArrayList<>();
        solicitationRepository.findAll().forEach(solicitation -> {
            responses.add(createResponse(solicitation));
        });
        return responses;
    }

    public SolicitationResponse getSolicitationById(Long id){
        Solicitation solicitation = solicitationRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The solicitation with id: " + id + " is not found!");
        });
        return createResponse(solicitation);
    }

    public SolicitationResponse saveSolication(SolicitationDto solicitationDto){
        Solicitation newSolicitation = solicitationMapper.solicitationDtoToSolicitation(solicitationDto);
        Solicitation savedSolicitation = solicitationRepository.save(newSolicitation);
        return createResponse(savedSolicitation);
    }

    public void updateSolicitation(Long id, SolicitationDto solicitationDto){
        solicitationRepository.findById(id).ifPresentOrElse(solicitation -> {
            Solicitation newSolicitation = solicitationMapper.solicitationDtoToSolicitation(solicitationDto);
            solicitation.setDeadlineDate(newSolicitation.getDeadlineDate());
            solicitation.setPurchaseOrderId(newSolicitation.getPurchaseOrderId());
            solicitationRepository.save(solicitation);
        }
        ,() -> {
            throw new NoSuchElementException("The solicitation with id: " + id + "does not exist!");
                });
    }

    public void deleteSolicitation(Long id){
        solicitationRepository.findById(id).ifPresentOrElse(solicitationRepository::delete,
                () -> {
                    throw new NoSuchElementException("The solicitation with id: " + id + "does not exist!"); });
    }

    private SolicitationResponse createResponse(Solicitation solicitation){
        return SolicitationResponse.builder()
                .dateCreated(solicitation.getDateCreated())
                .dateModified(solicitation.getDateModified())
                .deadline(solicitation.getDeadlineDate())
                .purchaseOrderId(solicitation.getPurchaseOrderId())
                .build();
    }
}
