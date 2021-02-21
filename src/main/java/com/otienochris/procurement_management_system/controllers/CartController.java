package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.models.Cart;
import com.otienochris.procurement_management_system.models.Item;
import com.otienochris.procurement_management_system.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

//    get all
    @GetMapping("/all")
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

//    get an item from cart
    @GetMapping("/{cartId}/{itemId}")
    public Item getAnItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId){
        return cartService.getItemFromCart(cartId, itemId);
    }

//   search
    @GetMapping("/{cartId}")
    public Optional<Cart> getCartById(@PathVariable Long cartId){
        return cartService.getCartById(cartId);
    }

//    create
    @PostMapping
    public List<Cart> createCart(@RequestBody Cart cart){
        return cartService.addCart(cart);
    }

    @PostMapping
    public List<Cart> deleteCart(@RequestBody Cart cart){
        return cartService.deleteCart(cart);
    }

    @PostMapping("/add/{cartId}/{itemId}")
    public Optional<Cart> addItemToCart(@PathVariable(required = false) Long cartId, Long itemId){
        return cartService.addItemToCart(cartId, itemId);
    }

//    add a list of items to cart
    @PostMapping("/add/{cartId}")
    public Optional<Cart> addAListOfItemsToCart(@PathVariable Long cartId, @RequestBody List<Item> items){
        return cartService.addAItemsListToCart(cartId, items);
    }

    //    delete
    @PostMapping("/delete/{cartId}")
    public List<Cart> deleteCartById(@PathVariable Long cartId){
        return cartService.deleteCartById(cartId);
    }

    //    delete item from cart
    @PostMapping("/delete/{cartId}/{itemId}")
    public Optional<Cart> deleteItemFromCart(@PathVariable Long cartId,@PathVariable Long itemId){
        return cartService.deleteItemFromCart(cartId, itemId);
    }

// todo update method
}
