package com.otienochris.procurement_management_system.controllers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otienochris.procurement_management_system.Dtos.PurchaseOrderDto;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
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

    @Autowired
    WebApplicationContext webApplicationContext;

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
        MockMultipartFile file = new MockMultipartFile(
                "rfiTemplate",
                "hello.pdf",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, world".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile file2 = new MockMultipartFile(
                "rfpTemplate",
                "hello.pdf",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, world".getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(multipart("http://localhost:8080/api/v1/purchase-orders/")
                .file(file)
                .file(file2))
                .andExpect(status().isCreated());

    }

    @Test
    void updatePurchaseOrder() {
    }

    @Test
    void deletePurchaseOrder() {
    }
}