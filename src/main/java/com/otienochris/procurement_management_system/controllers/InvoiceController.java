package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.models.Invoice;
import com.otienochris.procurement_management_system.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/all")
    public List<Invoice> getAllInvoice(){
        return invoiceService.getAllInvoices();
    }

    @PostMapping("/add")
    public Invoice addInvoice(@RequestBody Invoice invoice){
        return invoiceService.addInvoice(invoice);
    }

    @GetMapping("/{id}")
    public Optional<Invoice> getInvoiceByid( @PathVariable Long id){
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping("/delete")
    public List<Invoice> deleteInvoice(@RequestBody Invoice invoice){
        return invoiceService.deleteInvoice(invoice);
    }

    @PostMapping("/delete/{id}")
    public List<Invoice> deleteInvoiceById(@PathVariable Long id){
        return invoiceService.deleteInvoiceById(id);
    }

    @PostMapping("/update")
    public Optional<Invoice> updateInvoice(@RequestBody Invoice invoice){
        return invoiceService.updateInvoice(invoice);
    }
}
