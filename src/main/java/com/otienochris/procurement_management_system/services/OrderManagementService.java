package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.OrderManagementDto;
import com.otienochris.procurement_management_system.mappers.OrderManagementMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.OrderManagement;
import com.otienochris.procurement_management_system.models.enums.OMStatusEnum;
import com.otienochris.procurement_management_system.repositories.OrderManagementRepo;
import com.otienochris.procurement_management_system.responses.OrderManagementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderManagementService {


    private final OrderManagementRepo orderManagementRepo;


    private final OrderManagementMapper orderManagementMapper;

    public List<OrderManagementResponse> getAll() {
        List<OrderManagementResponse> responses = new ArrayList<>();
        orderManagementRepo.findAll().forEach(orderManagement -> {
            responses.add(createResponse(orderManagement));
        });
        return responses;
    }

    public OrderManagementResponse getById(Integer id) {
        OrderManagement orderManagement = orderManagementRepo.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The Purchase Order with Id: " + id + " does not exist!");
        });
        return createResponse(orderManagement);
    }

    public OrderManagementResponse saveOrderManagement(OrderManagementDto orderManagementDto) {
        OrderManagement orderManagement = orderManagementMapper.orderManagementDtoToOrderManagement(orderManagementDto);

        Document invoice = orderManagement.getInvoice();
        Document goodsReceivedNote = orderManagement.getGoodsReceivedNote();
        Document goodsReturnShipment = orderManagement.getGoodsReturnShipment();


        if (invoice != null)
            invoice.setType("invoice");
        if (goodsReceivedNote != null)
            goodsReceivedNote.setType("Goods Received note");
        if (goodsReturnShipment != null)
            goodsReturnShipment.setType("Goods returned note");

        orderManagement.setDepartmentHeadApproval(OMStatusEnum.PENDING);
        orderManagement.setProcurementOfficerApproval(OMStatusEnum.PENDING);
        orderManagement.setSupplierApproval(OMStatusEnum.PENDING);
        orderManagement.setStoreManagerApproval(OMStatusEnum.PENDING);

        OrderManagement savedOrderManagement = orderManagementRepo.save(orderManagement);

        return createResponse(savedOrderManagement);
    }

    public void updateOrderManagement(Integer id, OrderManagementDto orderManagementDto) {
        OrderManagement newOrderManagement = orderManagementMapper.orderManagementDtoToOrderManagement(orderManagementDto);

        orderManagementRepo.findById(id).ifPresentOrElse(
                orderManagement -> {
                    orderManagement.setStoreManagerApproval(newOrderManagement.getStoreManagerApproval());
                    orderManagement.setSupplierApproval(newOrderManagement.getSupplierApproval());
                    orderManagement.setProcurementOfficerApproval(newOrderManagement.getProcurementOfficerApproval());
                    orderManagement.setDepartmentHeadApproval(newOrderManagement.getDepartmentHeadApproval());
                    orderManagement.setPurchaseOrderId(newOrderManagement.getPurchaseOrderId());

                    newOrderManagement.getInvoice().setType("invoice");
                    newOrderManagement.getGoodsReceivedNote().setType("Goods Received note");
                    newOrderManagement.getGoodsReturnShipment().setType("Goods returned note");

                    orderManagement.setInvoice(newOrderManagement.getInvoice());
                    orderManagement.setGoodsReceivedNote(newOrderManagement.getGoodsReceivedNote());
                    orderManagement.setGoodsReturnShipment(newOrderManagement.getGoodsReturnShipment());


                    orderManagementRepo.save(orderManagement);

                }, () -> {
                    throw new NoSuchElementException("Item with id: " + id + " not found");
                }
        );
    }

    public void delete(Integer id) {
        orderManagementRepo.findById(id).ifPresentOrElse(
                orderManagementRepo::delete
                , () -> {
                    throw new NoSuchElementException("Item not found! ");
                });
    }


    public OrderManagementResponse createResponse(OrderManagement orderManagement) {

        String invoicesDocPath = "", grnPath = "", grsPath = "";

        Document invoiceDoc = orderManagement.getInvoice();
        Document grn = orderManagement.getGoodsReceivedNote();
        Document grs = orderManagement.getGoodsReturnShipment();

        if (invoiceDoc != null){
            String invoiceDocName = StringUtils.cleanPath(invoiceDoc.getFileName());
            invoicesDocPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/documents/download/")
                    .path(invoiceDocName)
                    .toUriString();

        }
        if (grn != null){
            String grnName = StringUtils.cleanPath(grn.getFileName());
            grnPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/documents/download/")
                    .path(grnName)
                    .toUriString();
        }
        if (grs != null){
            String grsName = StringUtils.cleanPath(grs.getFileName());
            grsPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/documents/download/")
                    .path(grsName)
                    .toUriString();
        }




        return OrderManagementResponse.builder()
                .id(orderManagement.getId())
                .invoiceUrl(invoicesDocPath)
                .goodsReturnedShipmentUrl(grsPath)
                .goodsReceivedNoteUrl(grnPath)
                .build();
    }
}
