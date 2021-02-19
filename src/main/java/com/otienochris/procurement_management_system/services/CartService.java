package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.models.Cart;
import com.otienochris.procurement_management_system.repositories.CartRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

//    todo all
    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

//    todo get a cart
    public Optional<Cart> getCartById(Long id){
        return cartRepository.findById(id);
    }
//    todo add a list to a cart

//    todo add an Item to a cart

//    todo find an item from a cart

//    todo edit an item from a cart

//    todo delete an item from a cart

//    todo delete a cart

}
