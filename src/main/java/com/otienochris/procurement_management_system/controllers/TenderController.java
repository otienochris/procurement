package com.otienochris.procurement_management_system.controllers;


import com.otienochris.procurement_management_system.models.Tender;
import com.otienochris.procurement_management_system.services.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tender")
public class TenderController {

    @Autowired
    private TenderService tenderService;

    //    get by id
    @GetMapping("/{id}")
    public Optional<Tender> getTenderById(@PathVariable Long id){
        return tenderService.getTenderById(id);
    }

    //    get all
    @GetMapping("/all")
    public List<Tender> getAllTenders(){
        return tenderService.getAllTenders();
    }

    //    add / create
    @PostMapping("/create")
    public List<Tender> addTender(@RequestBody Tender tender){
        return tenderService.addTender(tender);
    }

    //    delete
    @PostMapping("/delete/{tenderId}")
    public List<Tender> deleteTenderById(@PathVariable Long tenderId){
        return tenderService.deleteTenderById(tenderId);
    }

    @PostMapping("/delete")
    public List<Tender> deleteTender(@RequestBody Tender tender){
        return tenderService.deleteTender(tender);
    }

    //    update
    @PostMapping("/update")
    public Optional<Tender> updateTender(@RequestBody Tender tender){
        return tenderService.updateTender(tender);
    }
}
