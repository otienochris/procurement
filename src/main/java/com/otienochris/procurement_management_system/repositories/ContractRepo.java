package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContractRepo extends JpaRepository<Contract, Integer> {

}
