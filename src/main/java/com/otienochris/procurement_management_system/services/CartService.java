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

//    todo all
    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

//    todo get a cart
    public Optional<Cart> getCartById(Long id){
        return cartRepository.findById(id);
    }
//    todo add a list to a cart
    public void addAListToCart(Long id, List<Item> items){
        if (cartRepository.findById(id).isPresent())
            cartRepository.findById(id).get().setItems(items);

    }

    public void creatCart(List<Item> items){
//        cartRepository.save(new Cart(items));
    }
//    todo add an Item to a cart

    public void addItemToCart(Long id, Item item){
        if (cartRepository.findById(id).isPresent()){
            cartRepository.findById(id).get().getItems().add(item);
        }
    }
//    todo did not test if the found item is null
    public Item getItemFromCart(Long cartId, Long itemId){

        // check if the the cart exists and item exists
        if (cartRepository.findById(cartId).isPresent()){
            Cart cart = cartRepository.findById(cartId).get();
            if (cart.getItems().contains(itemRepository.getOne(itemId))) {
                itemRepository.getOne(itemId);
            }
        }
        return null;
    }
//    todo edit an item from a cart
public Item editItemInCart(Long cartId, Long itemId, Item newItem){
    return null;
}
//    todo delete an item from a cart

//    todo delete a cart

}
