package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.models.Item;
import com.otienochris.procurement_management_system.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/all")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @PostMapping("/add")
    public Item addItem(@RequestBody Item item){
        return itemService.addItem(item);
    }

    @PostMapping("/delete")
    public List<Item> deleteItem(@RequestBody Item item){
        return itemService.deleteItem(item);
    }

    @GetMapping("/{id}")
    public Optional<Item> findItemById(@PathVariable Long id){
        return itemService.getItemById(id);
    }

    @PostMapping("/update")
    public Item updateItem(@RequestBody Item item){
        return itemService.updateItem(item);
    }
}
