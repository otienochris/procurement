package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.PurchaseOrderDto;
import com.otienochris.procurement_management_system.exception_handlers.ResourceNotFoundException;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import com.otienochris.procurement_management_system.repositories.PurchaseOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public PurchaseOrder getPOById(Long id){
        return purchaseOrderRepository.findById(id)
                .orElseThrow(
                        () -> {
                            log.error("Item with id: " + id + " not found");
                            throw new IllegalArgumentException("Item with id: " + id + " not found");
                        }
                );
    }

    public List<PurchaseOrder> getAllPO(){
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder savePO(PurchaseOrderDto purchaseOrderDto) throws IOException {
        Document rfiTemplate = Document.builder()
                .title("RFI Template")
                .fileName(purchaseOrderDto.getRFITemplate().getOriginalFilename())
                .content(purchaseOrderDto.getRFITemplate().getBytes())
                .build();
        Document rfpTemplate = Document.builder()
                .title("RFP Template")
                .content(purchaseOrderDto.getRFPTemplate().getBytes())
                .fileName(purchaseOrderDto.getRFPTemplate().getOriginalFilename())
                .build();

        log.info("saving purchase order with files:  "
                + rfiTemplate.getFileName() +
                " and "
                + rfpTemplate.getFileName()
                + " ...");

        PurchaseOrder toSavePurchaseOrder = PurchaseOrder.builder()
                .status(purchaseOrderDto.getStatus())
                .rFITemplate(Document.builder()
                        .title(rfiTemplate.getTitle())
                        .fileName(rfiTemplate.getFileName())
                        .content(rfiTemplate.getContent())
                        .build())
                .rFPTemplate(Document.builder()
                        .title(rfpTemplate.getTitle())
                        .content(rfpTemplate.getContent())
                        .fileName(rfpTemplate.getFileName())
                        .build())
                .build();
        log.info("Purchase order saved!");
        return purchaseOrderRepository.save(toSavePurchaseOrder);
    }

    public void updatePO(Long id, PurchaseOrderDto purchaseOrderDto) throws IOException {
        Document rfiTemplate = Document.builder()
                .title("RFI Template")
                .fileName(purchaseOrderDto.getRFITemplate().getOriginalFilename())
                .content(purchaseOrderDto.getRFITemplate().getBytes())
                .build();
        Document rfpTemplate = Document.builder()
                .title("RFP Template")
                .fileName(purchaseOrderDto.getRFPTemplate().getOriginalFilename())
                .content(purchaseOrderDto.getRFPTemplate().getBytes())
                .build();

        purchaseOrderRepository.findById(id).ifPresentOrElse(
                purchaseOrder -> {
                    purchaseOrder.setRFITemplate(rfiTemplate);
                    purchaseOrder.setRFPTemplate(rfpTemplate);
                    purchaseOrder.setStatus(purchaseOrderDto.getStatus());
                    purchaseOrderRepository.save(purchaseOrder);

        },() -> {
                    log.error("Item with id: " + id + " not found");
                    throw new IllegalArgumentException("Item with id: " + id + " not found");
                }
        );
    }

//    delete
    public void deletePO(Long id){
        purchaseOrderRepository.findById(id).ifPresentOrElse(
                purchaseOrderRepository::delete
                ,() -> {
                    log.error("Item with id: " + id + " not found");
                    throw new IllegalArgumentException("Item not found! ");
                });
    }
}
