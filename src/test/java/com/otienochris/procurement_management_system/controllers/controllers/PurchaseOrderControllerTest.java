package com.otienochris.procurement_management_system.controllers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otienochris.procurement_management_system.controllers.PurchaseOrderController;
import com.otienochris.procurement_management_system.models.POStatus;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import com.otienochris.procurement_management_system.services.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchaseOrderController.class)
class PurchaseOrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private  PurchaseOrderService purchaseOrderService;

    @Test
    void getPurchaseOrder() {

    }

    @Test
    void getAllPOs() throws Exception {
        mockMvc.perform(
                    get("http://localhost:8080/api/v1/purchase-orders/all"))
                .andExpect(status().isOk());
    }

    @Test
    void savePurchaseOrder() throws Exception{
        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .status(POStatus.PENDING)
                .build();

        String purchaseOrderJson = objectMapper.writeValueAsString(purchaseOrder);

        mockMvc.perform(
                post("http://localhost:8080/api/v1/purchaseorders/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(purchaseOrderJson))
                .andExpect(status().isCreated());

    }

    @Test
    void updatePurchaseOrder() {
    }

    @Test
    void deletePurchaseOrder() {
    }
}