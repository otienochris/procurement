package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.PurchaseOrderDto;
import com.otienochris.procurement_management_system.mappers.PurchaseOrderMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import com.otienochris.procurement_management_system.repositories.DocumentRepository;
import com.otienochris.procurement_management_system.repositories.PurchaseOrderRepository;
import com.otienochris.procurement_management_system.responses.PurchaseOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.NoSuchFileException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final DocumentService documentService;
    private final DocumentRepository documentRepository;


    public PurchaseOrderResponse getPOById(UUID id){
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The Purchase Order with Id: " + id + " does not exist!");
        });
        return createResponse(purchaseOrder);
    }

    public List<PurchaseOrderResponse> getAllPO(){
        List<PurchaseOrderResponse> responses = new ArrayList<>();
        purchaseOrderRepository.findAll().forEach(purchaseOrder -> {
            responses.add(createResponse(purchaseOrder));
        });
        return responses;
    }

    public PurchaseOrderResponse savePO(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder newPurchaseOrder = purchaseOrderMapper.purchaseOrderDtoToPurchaseOrder(purchaseOrderDto);
        newPurchaseOrder.getRfiTemplate().setType("Rfi Template");
        newPurchaseOrder.getRfpTemplate().setType("Rfp Template");
        newPurchaseOrder.setId(UUID.randomUUID());
        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(newPurchaseOrder);

        return createResponse(savedPurchaseOrder);
    }

    public void updatePO(UUID id, PurchaseOrderDto purchaseOrderDto){
        PurchaseOrder newPurchaseOrder = purchaseOrderMapper.purchaseOrderDtoToPurchaseOrder(purchaseOrderDto);
        purchaseOrderRepository.findById(id).ifPresentOrElse(
                purchaseOrder -> {
                    Document oldRfp = purchaseOrder.getRfpTemplate();
                    Document oldRfi = purchaseOrder.getRfiTemplate();

                    Document newRfp = newPurchaseOrder.getRfpTemplate();
                    Document newRfi = newPurchaseOrder.getRfiTemplate();

                    // if the passed rfi exist, just change the content, else overwrite the docs
                    if (oldRfi.getFileName().equals(newRfi.getFileName())){
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
                    if (oldRfp.getFileName().equals(newRfp.getFileName())){
                        documentRepository.findByFileName(newRfp.getFileName()).ifPresent(document -> {
                            document.setContent(newRfp.getContent());
                            purchaseOrder.setRfpTemplate(documentRepository.save(document));
                        });
                    } else {
                        newRfp.setType("RFP template");
                        purchaseOrder.setRfpTemplate(documentRepository.save(newRfp));
                        documentService.deleteFile(oldRfp.getFileName());
                    }

                    // change the status
                    purchaseOrder.setStatus(newPurchaseOrder.getStatus());
                    //save the updated purchase order
                    purchaseOrderRepository.save(purchaseOrder);

        },() -> {
                    throw new NoSuchElementException("Item with id: " + id + " not found");
                }
        );
    }

//    delete
    public void deletePO(UUID id){
        purchaseOrderRepository.findById(id).ifPresentOrElse(
                purchaseOrderRepository::delete
                ,() -> { throw new NoSuchElementException("Item not found! "); });
    }

    public PurchaseOrderResponse createResponse(PurchaseOrder purchaseOrder){
        Document rfiDoc = purchaseOrder.getRfiTemplate();
        Document rfpDoc = purchaseOrder.getRfpTemplate();

        String rfiTemplateName = StringUtils.cleanPath(rfiDoc.getFileName());
        String rfpTemplateName = StringUtils.cleanPath(rfpDoc.getFileName());

        String rfiTemplatePath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(rfiTemplateName)
                .toUriString();
        String rfpTemplatePath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(rfpTemplateName)
                .toUriString();
        return PurchaseOrderResponse.builder()
                .id(purchaseOrder.getId())
                .dataCreated(purchaseOrder.getDateCreated())
                .dateModified(purchaseOrder.getDateModified())
                .rfiTemplateDownloadUrl(rfiTemplatePath)
                .rfpTemplateDownloadUrl(rfpTemplatePath)
                .status(purchaseOrder.getStatus().name())
                .build();
    }
}
