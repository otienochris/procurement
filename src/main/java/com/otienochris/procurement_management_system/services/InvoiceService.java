package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.models.Invoice;
import com.otienochris.procurement_management_system.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id){
        return invoiceRepository.findById(id);
    }

    public Invoice addInvoice(Invoice invoice){
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return invoiceRepository.findById(savedInvoice.getInvoiceId()).get();
    }

    public Invoice updateInvoice(Invoice invoice){
        if (invoiceRepository.findById(invoice.getInvoiceId()).isPresent()) {
            Invoice oldInvoice = invoiceRepository.findById(invoice.getInvoiceId()).get();
            oldInvoice.setDate(invoice.getDate());
            oldInvoice.setItems(invoice.getItems());
        }
        return invoiceRepository.findById(invoice.getInvoiceId()).get();
    }
    public void deleteInvoice(Invoice invoice){
        if (invoiceRepository.findById(invoice.getInvoiceId()).isPresent()){
            invoiceRepository.delete(invoice);
        }
    }
}
