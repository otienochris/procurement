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
import java.util.UUID;

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

    public SolicitationResponse getSolicitationById(Integer id){
        Solicitation solicitation = solicitationRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The solicitation with id: " + id + " is not found!");
        });
        return createResponse(solicitation);
    }

    public SolicitationResponse saveSolicitation(SolicitationDto solicitationDto){
        Solicitation newSolicitation = solicitationMapper.solicitationDtoToSolicitation(solicitationDto);
        Solicitation savedSolicitation = solicitationRepository.save(newSolicitation);
        return createResponse(savedSolicitation);
    }

    public void updateSolicitation(Integer id, SolicitationDto solicitationDto){
        solicitationRepository.findById(id).ifPresentOrElse(solicitation -> {
            Solicitation newSolicitation = solicitationMapper.solicitationDtoToSolicitation(solicitationDto);
            solicitation.setDeadlineDate(newSolicitation.getDeadlineDate());
            solicitation.setPurchaseOrderId(newSolicitation.getPurchaseOrderId());
            solicitation.setMessage(newSolicitation.getMessage());
            solicitationRepository.save(solicitation);
        }
        ,() -> {
            throw new NoSuchElementException("The solicitation with id: " + id + "does not exist!");
                });
    }

    public void deleteSolicitation(Integer id){
        solicitationRepository.findById(id).ifPresentOrElse(solicitationRepository::delete,
                () -> {
                    throw new NoSuchElementException("The solicitation with id: " + id + "does not exist!"); });
    }

    private SolicitationResponse createResponse(Solicitation solicitation){
        return SolicitationResponse.builder()
                .id(solicitation.getId())
                .dateCreated(solicitation.getDateCreated())
                .deadline(solicitation.getDeadlineDate())
                .purchaseOrderId(solicitation.getPurchaseOrderId())
                .message(solicitation.getMessage())
                .build();
    }
}
