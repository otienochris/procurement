package com.otienochris.procurement_management_system.models;

import javax.persistence.*;
import java.util.List;

// persistence
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Item> items;

//    getters, setters and to string methods
    public Long getCartId() {
        return cartId;
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        if (!items.contains(item)){
            items.add(item);
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", items=" + items +
                '}';
    }
}
