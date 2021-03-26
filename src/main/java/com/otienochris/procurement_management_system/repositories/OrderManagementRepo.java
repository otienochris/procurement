package com.otienochris.procurement_management_system.repositories;
import com.otienochris.procurement_management_system.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderManagementRepo extends JpaRepository<OrderManagement, Long> {
}
