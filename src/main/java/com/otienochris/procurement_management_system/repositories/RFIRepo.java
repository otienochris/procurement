package com.otienochris.procurement_management_system.repositories;
import com.otienochris.procurement_management_system.*;


import com.otienochris.procurement_management_system.models.RFI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RFIRepo extends JpaRepository<RFI, UUID> {
}
