package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.models.Cart;
import com.otienochris.procurement_management_system.models.Item;
import com.otienochris.procurement_management_system.repositories.CartRepository;
import com.otienochris.procurement_management_system.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(Long id){
        return cartRepository.findById(id);
    }

//    todo this overwrites the existing list yet we need to add to the existing
    public Optional<Cart> addAItemsListToCart(Long id, List<Item> items){
        cartRepository.findById(id).ifPresent(cart ->
            cart.setItems(items));
        return cartRepository.findById(id); // if null, the cart does not exist
    }

//    todo add an Item to a cart
    public Optional<Cart> addItemToCart(Long cartId, Long itemId){
        itemRepository.findById(itemId).ifPresent( item ->
            cartRepository.findById(cartId).ifPresent( cart -> {
                cart.getItems().add(item);
                cartRepository.save(cart);
            })
        );
        return cartRepository.findById(cartId);
    }

//    todo did not test if the found item is null
    public Item getItemFromCart(Long cartId, Long itemId){
        // check if the the cart exists and item exists
        List<Item> searchedItem = null;
        itemRepository.findById(itemId).ifPresent(item ->
            cartRepository.findById(cartId).ifPresent(cart -> {
                    if(cart.getItems().contains(item)) {
                        searchedItem.add(item);
                    }
                }
            )
        );
        return searchedItem.get(0);
    }

//    todo delete an item from a cart
    public Optional<Cart> deleteItemFromCart(Long cartId, Long itemId){
        // check that item and cart exists
        itemRepository.findById(itemId).ifPresent( item ->
            cartRepository.findById(cartId).ifPresent(cart -> {
                if (cart.getItems().contains(item)) {
                    cart.getItems().remove(item);
                }
            })
        );

        return cartRepository.findById(cartId);
    }

//    todo delete a cart
    public List<Cart> deleteCart(Long cartId){
        cartRepository.findById(cartId).ifPresent( cart ->
                cartRepository.deleteById(cartId));
        return cartRepository.findAll();
    }

}
