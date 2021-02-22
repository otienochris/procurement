package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.models.Cart;
import com.otienochris.procurement_management_system.models.Item;
import com.otienochris.procurement_management_system.repositories.CartRepository;
import com.otienochris.procurement_management_system.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Cart> addAItemsListToCart(Long cartId, List<Item> items){
        cartRepository.findById(cartId).ifPresent(cart ->
            cart.setItems(items));
        return cartRepository.findById(cartId); // if null, the cart does not exist
    }

//    todo add an Item to a cart, if a cart does not exist, create a new one
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
        searchedItem.add(0, null);
        itemRepository.findById(itemId).ifPresent(item ->
            cartRepository.findById(cartId).ifPresent(cart -> {
                    if(cart.getItems().contains(item)) {
                        searchedItem.set(0, item);
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

    public List<Cart> deleteCartById(Long cartId){
        cartRepository.findById(cartId).ifPresent( cart ->
                cartRepository.delete(cart));
        return cartRepository.findAll();
    }

    public List<Cart> addCart(Cart cart) {
        cartRepository.save(cart);
        return cartRepository.findAll();
    }

    public List<Cart> deleteCart(Cart cart) {
        cartRepository.findById(cart.getCartId()).ifPresent(oldCart ->
                cartRepository.delete(oldCart)
        );
        return cartRepository.findAll();
    }
}
