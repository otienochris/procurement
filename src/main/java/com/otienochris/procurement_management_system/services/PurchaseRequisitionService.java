package com.otienochris.procurement_management_system.services;
import com.otienochris.procurement_management_system.*;

import com.otienochris.procurement_management_system.Dtos.PurchaseRequisitionDto;
import com.otienochris.procurement_management_system.mappers.PurchaseRequisitionMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.PurchaseRequisition;
import com.otienochris.procurement_management_system.repositories.PurchaseRequisitionRepo;
import com.otienochris.procurement_management_system.responses.PurchaseRequisitionResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PurchaseRequisitionService {

    PurchaseRequisitionRepo purchaseRequisitionRepo;

    PurchaseRequisitionMapper purchaseRequisitionMapper;

    public List<PurchaseRequisitionResponse> getAll(){
        List<PurchaseRequisitionResponse> responses = new ArrayList<>();
        purchaseRequisitionRepo.findAll().forEach(purchaseRequisition -> {
            responses.add(createResponse(purchaseRequisition));
        });
        return responses;
    }

    public PurchaseRequisitionResponse getById(Long id){
        PurchaseRequisition purchaseRequisition = purchaseRequisitionRepo.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The Purchase Requisition with Id: " + id + " does not exist!");
        });
        return createResponse(purchaseRequisition);
    }


    public PurchaseRequisitionResponse savePurchaseRequisition(PurchaseRequisitionDto purchaseRequisitionDto) {
        PurchaseRequisition purchaseRequisition = purchaseRequisitionMapper.purchaseRequisitionDtoToPurchaseRequisition(purchaseRequisitionDto);
        purchaseRequisition.getNeedDocument().setType("Need Document");
        purchaseRequisition.getAcquisitionDocument().setType("Acquisition Cost Document");
        purchaseRequisition.getAnalysisDocument().setType("Analysis Document");
        purchaseRequisition.getEmergencyDocument().setType("Emergency Document");
        PurchaseRequisition savedPurchaseRequisition = purchaseRequisitionRepo.save(purchaseRequisition);

        return createResponse(savedPurchaseRequisition);
    }

    public void updatePurchaseRequisition(Long id, PurchaseRequisitionDto purchaseRequisitionDto){
        PurchaseRequisition newPurchaseRequisition = purchaseRequisitionMapper.purchaseRequisitionDtoToPurchaseRequisition(purchaseRequisitionDto);

        purchaseRequisitionRepo.findById(id).ifPresentOrElse(
                purchaseRequisition -> {
                    purchaseRequisition.setNeedDocument(newPurchaseRequisition.getNeedDocument());
                    purchaseRequisition.setAcquisitionDocument(newPurchaseRequisition.getAcquisitionDocument());
                    purchaseRequisition.setAnalysisDocument(newPurchaseRequisition.getAnalysisDocument());
                    purchaseRequisition.setEmergencyDocument(newPurchaseRequisition.getEmergencyDocument());
                    purchaseRequisitionRepo.save(purchaseRequisition);

                },() -> {
                    throw new NoSuchElementException("Item with id: " + id + " not found");
                }
        );
    }


    public void delete(Long id){
        purchaseRequisitionRepo.findById(id).ifPresentOrElse(
                purchaseRequisitionRepo::delete
                ,() -> { throw new NoSuchElementException("Item not found! "); });
    }

    public PurchaseRequisitionResponse createResponse(PurchaseRequisition purchaseRequisition) {

        Document needDocument = purchaseRequisition.getNeedDocument();
        Document acquisitionDocument = purchaseRequisition.getAcquisitionDocument();
        Document analysisDocument = purchaseRequisition.getAnalysisDocument();
        Document emergencyDocument = purchaseRequisition.getEmergencyDocument();


        String needDocumentName = StringUtils.cleanPath(needDocument.getFileName());
        String acquisitionDocumentName = StringUtils.cleanPath(acquisitionDocument.getFileName());
        String analysisDocumentName = StringUtils.cleanPath(analysisDocument.getFileName());
        String emergencyDocumentName = StringUtils.cleanPath(emergencyDocument.getFileName());

        //please update path after creating controllers
        String needDocumentPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/document/download/}")
                .path(needDocumentName)
                .toUriString();
        String acquisitionDocumentPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/document/download/")
                .path(acquisitionDocumentName)
                .toUriString();
        String analysisDocumentPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/document/download/")
                .path(analysisDocumentName)
                .toUriString();
        String emergencyDocumentPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/document/download/")
                .path(emergencyDocumentName)
                .toUriString();



         return PurchaseRequisitionResponse.builder()
                .id(purchaseRequisition.getId())
                .needDocumentUrl(needDocumentPath)
                .acquisitionDocumentUrl(acquisitionDocumentPath)
                .analysisDocumentUrl(analysisDocumentPath)
                .emergencyDocumentUrl(emergencyDocumentPath)
                .build();

    }
}
