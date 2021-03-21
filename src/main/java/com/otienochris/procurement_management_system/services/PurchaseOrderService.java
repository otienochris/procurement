package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.PurchaseOrderDto;
import com.otienochris.procurement_management_system.mappers.PurchaseOrderMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import com.otienochris.procurement_management_system.repositories.PurchaseOrderRepository;
import com.otienochris.procurement_management_system.responses.PurchaseOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


    public PurchaseOrderResponse getPOById(Long id){
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
        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(newPurchaseOrder);

        return createResponse(savedPurchaseOrder);
    }

    public void updatePO(Long id, PurchaseOrderDto purchaseOrderDto){
        PurchaseOrder newPurchaseOrder = purchaseOrderMapper.purchaseOrderDtoToPurchaseOrder(purchaseOrderDto);

        purchaseOrderRepository.findById(id).ifPresentOrElse(
                purchaseOrder -> {
                    purchaseOrder.setRfiTemplate(newPurchaseOrder.getRfiTemplate());
                    purchaseOrder.setRfpTemplate(newPurchaseOrder.getRfpTemplate());
                    purchaseOrder.setStatus(newPurchaseOrder.getStatus());
                    purchaseOrderRepository.save(purchaseOrder);

        },() -> {
                    throw new NoSuchElementException("Item with id: " + id + " not found");
                }
        );
    }

//    delete
    public void deletePO(Long id){
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
                .rfiTemplateUrl(rfiTemplatePath)
                .rfpTemplateUrl(rfiTemplatePath).build();
    }
}
