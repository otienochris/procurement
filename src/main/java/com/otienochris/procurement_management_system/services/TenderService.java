package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.models.Tender;
import com.otienochris.procurement_management_system.repositories.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenderService {

    @Autowired
    private TenderRepository tenderRepository;

    public List<Tender> getAllTenders(){
        return tenderRepository.findAll();
    }

    public List<Tender> addTender(Tender tender){
        if (tenderRepository.findById(tender.getTenderId()).isEmpty())
            tenderRepository.save(tender);
        return tenderRepository.findAll();
    }

    public Optional<Tender> getTenderById(Long id){
        return tenderRepository.findById(id);
    }

    public List<Tender> deleteTender(Tender tender){
        tenderRepository.findById(tender.getTenderId()).ifPresent( tender1 ->
            tenderRepository.delete(tender1)
        );
        return tenderRepository.findAll();
    }

//      todo work on the tender attachment file
    public Optional<Tender> updateTender(Tender newTender){
        tenderRepository.findById(newTender.getTenderId()).ifPresent( oldTender -> {
            oldTender.setDescription(newTender.getDescription());
            oldTender.setDate(newTender.getDate());
//            oldTender.setAttachment(tender.getAttachment());
            tenderRepository.save(oldTender);

        });
        return tenderRepository.findById(newTender.getTenderId());
    }

    public List<Tender> deleteTenderById(Long tenderId) {
        tenderRepository.findById(tenderId).ifPresent(tender -> {
            tenderRepository.deleteById(tenderId);
        });
        return tenderRepository.findAll();
    }
}
