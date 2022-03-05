package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.OrderManagementDto;
import com.otienochris.procurement_management_system.mappers.OrderManagementMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.OrderManagement;
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
        orderManagement.getInvoice().setType("invoice");
        OrderManagement savedOrderManagement = orderManagementRepo.save(orderManagement);

        return createResponse(savedOrderManagement);
    }

    public void updateOrderManagement(Integer id, OrderManagementDto orderManagementDto) {
        OrderManagement newOrderManagement = orderManagementMapper.orderManagementDtoToOrderManagement(orderManagementDto);

        orderManagementRepo.findById(id).ifPresentOrElse(
                orderManagement -> {
                    orderManagement.setInvoice(newOrderManagement.getInvoice());
                    orderManagement.setStatus(newOrderManagement.getStatus());
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
        Document invoiceDoc = orderManagement.getInvoice();

        String invoiceDocName = StringUtils.cleanPath(invoiceDoc.getFileName());

        String invoicesDocPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/invoices/document/download/")
                .path(invoiceDocName)
                .toUriString();


        return OrderManagementResponse.builder()
                .id(orderManagement.getId())
                .invoiceUrl(invoicesDocPath)
                .build();
    }
}
