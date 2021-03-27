package com.otienochris.procurement_management_system.unit_tests.services;

import com.otienochris.procurement_management_system.repositories.SolicitationRepository;
import com.otienochris.procurement_management_system.services.SolicitationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SolicitationServiceTest {

    @Mock
    private SolicitationRepository solicitationRepository;

    @MockBean
    private SolicitationService solicitationService;

    @BeforeEach
    void setup(){
    }

    @Test
    void getAllSolicitations() {
    }

    @Test
    void getSolicitationById() {
    }

    @Test
    void saveSolication() {
    }

    @Test
    void updateSolicitation() {
    }

    @Test
    void deleteSolicitation() {
    }
}