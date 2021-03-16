package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.PurchaseOrderDto;
import com.otienochris.procurement_management_system.mappers.PurchaseOrderMapper;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import com.otienochris.procurement_management_system.repositories.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;


    public PurchaseOrderDto getPOById(Long id){
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
        if (purchaseOrder.isEmpty())
            return null;
        return purchaseOrderMapper.purchaseOrderToPurchaseOrderDto(purchaseOrder.get());
    }

    public List<PurchaseOrderDto> getAllPO(){
//        todo return a list of purchase Dtos
        return purchaseOrderMapper.purchaseOrdersToPurchaseOrderDtos(purchaseOrderRepository.findAll());
    }

    public PurchaseOrderDto savePO(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder newPurchaseOrder = purchaseOrderMapper.purchaseOrderDtoToPurchaseOrder(purchaseOrderDto);
        newPurchaseOrder.getRfiTemplate().setType("Rfi Template");
        newPurchaseOrder.getRfpTemplate().setType("Rfp Template");
        return purchaseOrderMapper.purchaseOrderToPurchaseOrderDto(
                purchaseOrderRepository.save(newPurchaseOrder)
        );
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
