package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.PurchaseRequisitionDto;
import com.otienochris.procurement_management_system.mappers.PurchaseRequisitionMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.PurchaseRequisition;
import com.otienochris.procurement_management_system.repositories.DocumentRepository;
import com.otienochris.procurement_management_system.repositories.PurchaseRequisitionRepo;
import com.otienochris.procurement_management_system.responses.PurchaseRequisitionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;


@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseRequisitionService {

    private final PurchaseRequisitionRepo purchaseRequisitionRepo;

    private final PurchaseRequisitionMapper purchaseRequisitionMapper;

    private final DocumentRepository documentRepository;

    public List<PurchaseRequisitionResponse> getAll() {
        List<PurchaseRequisitionResponse> responses = new ArrayList<>();
        purchaseRequisitionRepo.findAll().forEach(purchaseRequisition -> {
            responses.add(createResponse(purchaseRequisition));
        });
        return responses;
    }

    public PurchaseRequisitionResponse getById(Integer id) {
        PurchaseRequisition purchaseRequisition = purchaseRequisitionRepo.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The Purchase Requisition with Id: " + id + " does not exist!");
        });
        return createResponse(purchaseRequisition);
    }


    public void savePurchaseRequisition(PurchaseRequisitionDto purchaseRequisitionDto) {
        PurchaseRequisition purchaseRequisition =
                purchaseRequisitionMapper.purchaseRequisitionDtoToPurchaseRequisition(purchaseRequisitionDto);

        purchaseRequisition.getNeedDocument().setType("Need Document");
        purchaseRequisition.getAcquisitionDocument().setType("Acquisition Document");
        purchaseRequisition.getAnalysisDocument().setType("Analysis Document");
        purchaseRequisition.getEmergencyDocument().setType("Emergency Document");
        System.out.println(purchaseRequisition);

        documentRepository.save(purchaseRequisition.getNeedDocument());
        documentRepository.save(purchaseRequisition.getAcquisitionDocument());
        documentRepository.save(purchaseRequisition.getAnalysisDocument());
        documentRepository.save(purchaseRequisition.getEmergencyDocument());
        purchaseRequisitionRepo.save(purchaseRequisition);
    }

    public void updatePurchaseRequisition(Integer id, PurchaseRequisitionDto purchaseRequisitionDto) {
        PurchaseRequisition newPurchaseRequisition = purchaseRequisitionMapper.purchaseRequisitionDtoToPurchaseRequisition(purchaseRequisitionDto);

        purchaseRequisitionRepo.findById(id).ifPresentOrElse(
                purchaseRequisition -> {
                    Document oldNeedDocument = purchaseRequisition.getNeedDocument();
                    Document oldAcquisitionDocument = purchaseRequisition.getAcquisitionDocument();
                    Document oldAnalysisDocument = purchaseRequisition.getAnalysisDocument();
                    Document oldEmergencyDocument = purchaseRequisition.getEmergencyDocument();

                    Document newNeedDocument = newPurchaseRequisition.getNeedDocument();
                    Document newAcquisitionDocument = newPurchaseRequisition.getAcquisitionDocument();
                    Document newAnalysisDocument = newPurchaseRequisition.getAnalysisDocument();
                    Document newEmergencyDocument = newPurchaseRequisition.getEmergencyDocument();


                    if (newNeedDocument != null) {
                        if (oldNeedDocument.getFileName().equals(newNeedDocument.getFileName()))
                            documentRepository.findByFileName(newNeedDocument.getFileName()).ifPresent(document -> {
                                document.setContent(newNeedDocument.getContent());
                                documentRepository.save(document);
                            });
                        else {
                            newNeedDocument.setType("Need Doc");
                            purchaseRequisition.setNeedDocument(newNeedDocument);
                            documentRepository.delete(oldNeedDocument);
                        }
                    }
                    if (newAcquisitionDocument != null) {
                        if (oldAcquisitionDocument.getFileName().equals(newAcquisitionDocument.getFileName()))
                            documentRepository.findByFileName(newAcquisitionDocument.getFileName()).ifPresent(document -> {
                                document.setContent(newAcquisitionDocument.getContent());
                                documentRepository.save(document);
                            });
                        else {
                            newAcquisitionDocument.setType("Acquisition Doc");
                            purchaseRequisition.setAcquisitionDocument(newAcquisitionDocument);
                            documentRepository.delete(oldAcquisitionDocument);
                        }
                    }
                    if (newAnalysisDocument != null) {
                        if (oldAnalysisDocument.getFileName().equals(newAnalysisDocument.getFileName()))
                            documentRepository.findByFileName(newAnalysisDocument.getFileName()).ifPresent(document -> {
                                document.setContent(newAcquisitionDocument.getContent());
                                documentRepository.save(document);
                            });
                        else {
                            newAnalysisDocument.setType("Analysis Doc");
                            purchaseRequisition.setAnalysisDocument(newAnalysisDocument);
                            documentRepository.delete(oldAnalysisDocument);
                        }
                    }
                    if (newEmergencyDocument != null) {
                        if (oldEmergencyDocument.getFileName().equals(newEmergencyDocument.getFileName()))
                            documentRepository.findByFileName(newEmergencyDocument.getFileName()).ifPresent(document -> {
                                document.setContent(newEmergencyDocument.getContent());
                                documentRepository.save(document);
                            });
                        else {
                            newEmergencyDocument.setType("Emergency Doc");
                            purchaseRequisition.setEmergencyDocument(newEmergencyDocument);
                            documentRepository.delete(oldEmergencyDocument);
                        }
                    }
                    purchaseRequisition.setDescription(purchaseRequisitionDto.getDescription());
                    purchaseRequisitionRepo.save(purchaseRequisition);

                }, () -> {
                    throw new NoSuchElementException("Item with id: " + id + " not found");
                }
        );
    }


    public void delete(Integer id) {
        purchaseRequisitionRepo.findById(id).ifPresentOrElse(
                purchaseRequisitionRepo::delete
                , () -> {
                    throw new NoSuchElementException("Item not found! ");
                });
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
                .path("/api/v1/documents/download/")
                .path(needDocumentName)
                .toUriString();
        String acquisitionDocumentPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(acquisitionDocumentName)
                .toUriString();
        String analysisDocumentPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(analysisDocumentName)
                .toUriString();
        String emergencyDocumentPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(emergencyDocumentName)
                .toUriString();


        return PurchaseRequisitionResponse.builder()
                .id(purchaseRequisition.getId())
                .needDocumentUrl(needDocumentPath)
                .acquisitionDocumentUrl(acquisitionDocumentPath)
                .analysisDocumentUrl(analysisDocumentPath)
                .emergencyDocumentUrl(emergencyDocumentPath)
                .description(purchaseRequisition.getDescription())
                .dateCreated(purchaseRequisition.getDateCreated())
                .build();

    }
}
