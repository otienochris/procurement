package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
