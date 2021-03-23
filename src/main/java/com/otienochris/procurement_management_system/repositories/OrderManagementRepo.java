package com.group4.procurement.dao.newones;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.procurement.model.newones.OrderManagement;

public interface OrderManagementRepo extends JpaRepository<OrderManagement, Integer> {

}
