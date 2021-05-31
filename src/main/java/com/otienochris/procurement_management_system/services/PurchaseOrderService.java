package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.PurchaseOrderDto;
import com.otienochris.procurement_management_system.mappers.PurchaseOrderMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.enums.POStatusEnum;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import com.otienochris.procurement_management_system.repositories.DocumentRepository;
import com.otienochris.procurement_management_system.repositories.PurchaseOrderRepository;
import com.otienochris.procurement_management_system.responses.PurchaseOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final DocumentService documentService;
    private final DocumentRepository documentRepository;


    public PurchaseOrderResponse getPOById(Integer id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The Purchase Order with Id: " + id + " does not exist!");
        });
        return createResponse(purchaseOrder);
    }

    public List<PurchaseOrderResponse> getAllPO() {
        List<PurchaseOrderResponse> responses = new ArrayList<>();
        purchaseOrderRepository.findAll().forEach(purchaseOrder -> {
            responses.add(createResponse(purchaseOrder));
        });
        return responses;
    }

    public PurchaseOrderResponse savePO(PurchaseOrderDto purchaseOrderDto) {
        System.out.println(purchaseOrderDto);
        PurchaseOrder newPurchaseOrder = purchaseOrderMapper.purchaseOrderDtoToPurchaseOrder(purchaseOrderDto);

        newPurchaseOrder.setStatus(POStatusEnum.PENDING);
        newPurchaseOrder.getRfiTemplate().setType("Rfi Template");
        newPurchaseOrder.getRfpTemplate().setType("Rfp Template");
        newPurchaseOrder.getTermsAndConditions().setType("Terms and Conditions");
//
//        System.out.println(newPurchaseOrder.getTermsAndConditions());
//        Document TnC = documentRepository.saveAndFlush(newPurchaseOrder.getTermsAndConditions());
//        newPurchaseOrder.setTermsAndConditions(TnC);

        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(newPurchaseOrder);

        return createResponse(savedPurchaseOrder);
    }

    public void updatePO(Integer id, PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder newPurchaseOrder = purchaseOrderMapper.purchaseOrderDtoToPurchaseOrder(purchaseOrderDto);
        purchaseOrderRepository.findById(id).ifPresentOrElse(
                purchaseOrder -> {
                    Document oldRfp = purchaseOrder.getRfpTemplate();
                    Document oldRfi = purchaseOrder.getRfiTemplate();
                    Document oldTnC = purchaseOrder.getTermsAndConditions();

                    Document newRfp = newPurchaseOrder.getRfpTemplate();
                    Document newRfi = newPurchaseOrder.getRfiTemplate();
                    Document newTnC = newPurchaseOrder.getTermsAndConditions();

                    // if the passed rfi exist, just change the content, else overwrite the docs
                    if (oldRfi.getFileName().equals(newRfi.getFileName())) {
                        documentRepository.findByFileName(newRfi.getFileName()).ifPresent(document -> {
                            document.setContent(newRfi.getContent());
                            purchaseOrder.setRfiTemplate(documentRepository.save(document));
                        });
                    } else {
                        newRfi.setType("RFI template");
                        purchaseOrder.setRfiTemplate(documentRepository.save(newRfi));
                        documentService.deleteFile(oldRfi.getFileName());
                    }

                    // if the passed rfp exist, just change the content, else overwrite the docs
                    if (oldRfp.getFileName().equals(newRfp.getFileName())) {
                        documentRepository.findByFileName(newRfp.getFileName()).ifPresent(document -> {
                            document.setContent(newRfp.getContent());
                            purchaseOrder.setRfpTemplate(documentRepository.save(document));
                        });
                    } else {
                        newRfp.setType("RFP template");
                        purchaseOrder.setRfpTemplate(documentRepository.save(newRfp));
                        documentService.deleteFile(oldRfp.getFileName());
                    }

                    if (oldTnC.getFileName().equals(newTnC.getFileName())) {
                        documentRepository.findByFileName(newTnC.getFileName()).ifPresent(document -> {
                            document.setContent(newTnC.getContent());
                            purchaseOrder.setTermsAndConditions(documentRepository.save(document));
                        });
                    } else {
                        newTnC.setType("T&C");
                        purchaseOrder.setTermsAndConditions(documentRepository.save(newTnC));
                        documentService.deleteFile(oldTnC.getFileName());
                    }

                    // change the status
                    purchaseOrder.setStatus(newPurchaseOrder.getStatus());
                    purchaseOrder.setPurchaseRequisitionId(newPurchaseOrder.getPurchaseRequisitionId());
                    purchaseOrder.setDescription(newPurchaseOrder.getDescription());
                    //save the updated purchase order
                    purchaseOrderRepository.save(purchaseOrder);

                }, () -> {
                    throw new NoSuchElementException("Item with id: " + id + " not found");
                }
        );
    }

    //    delete
    public void deletePO(Integer id) {
        purchaseOrderRepository.findAll().forEach(purchaseOrder -> {
            System.out.println(id + "");
            System.out.println(purchaseOrder.getId());
        });
        purchaseOrderRepository.findById(id).ifPresentOrElse(
                purchaseOrderRepository::delete
                , () -> {
                    throw new NoSuchElementException("Item not found! ");
                });
    }

    public PurchaseOrderResponse createResponse(PurchaseOrder purchaseOrder) {
        Document rfiDoc = purchaseOrder.getRfiTemplate();
        Document rfpDoc = purchaseOrder.getRfpTemplate();
        Document termsAndConditions = purchaseOrder.getTermsAndConditions();

        String rfiTemplateName = StringUtils.cleanPath(rfiDoc.getFileName());
        String rfpTemplateName = StringUtils.cleanPath(rfpDoc.getFileName());
        String termsAndConditionsName = StringUtils.cleanPath(termsAndConditions.getFileName());

        String rfiTemplatePath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(rfiTemplateName)
                .toUriString();
        String rfpTemplatePath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(rfpTemplateName)
                .toUriString();
        String termsAndConditionsPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(termsAndConditionsName)
                .toUriString();
        return PurchaseOrderResponse.builder()
                .id(purchaseOrder.getId())
                .dataCreated(purchaseOrder.getDateCreated())
                .purchaseRequisitionId(purchaseOrder.getPurchaseRequisitionId())
                .rfiTemplateDownloadUrl(rfiTemplatePath)
                .rfpTemplateDownloadUrl(rfpTemplatePath)
                .termsAndConditionsDownloadUrl(termsAndConditionsPath)
                .status(purchaseOrder.getStatus().name())
                .description(purchaseOrder.getDescription())
                .build();
    }
}
