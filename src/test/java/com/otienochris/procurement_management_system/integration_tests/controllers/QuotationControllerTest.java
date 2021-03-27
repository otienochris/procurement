package com.otienochris.procurement_management_system.controllers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otienochris.procurement_management_system.controllers.QuotationController;
import com.otienochris.procurement_management_system.models.Quotation;
import com.otienochris.procurement_management_system.services.QuotationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(QuotationController.class)
class QuotationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    QuotationService quotationService;

    @Test
    void getQuotationById() throws Exception{
        mockMvc.perform(get("http://localhost:8080/api/v1/quotations/" + 12 )).andExpect(status().isOk());
    }

    @Test
    void getAllQuotations () throws Exception{
        mockMvc.perform(get("http://localhost:8080/api/v1/quotations/all"))
                .andExpect(status().isOk());
    }

    @Test
    void saveQuotation() throws Exception{
        MockMultipartFile quotationAttachment =
                new MockMultipartFile("quotationAttachment", "quote.pdf", MediaType.MULTIPART_FORM_DATA_VALUE, "Hello world".getBytes(StandardCharsets.UTF_8));
        mockMvc.perform(
                multipart("http://localhost:8080/api/v1/quotations/")
                        .file(quotationAttachment)).andExpect(status().isCreated());
    }

    @Test
    void deleteQuotation() {
    }

    @Test
    void updateQuotation() {
    }
}