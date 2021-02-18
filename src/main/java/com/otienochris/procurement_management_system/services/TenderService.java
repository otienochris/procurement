package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.models.Tender;
import com.otienochris.procurement_management_system.repositories.TenderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenderService {

    @Autowired
    private TenderRepository tenderRepository;

//    all
    public List<Tender> getAllTenders(){
        return tenderRepository.findAll();
    }
//    add
    public List<Tender> addTender(Tender tender){
        if (tenderRepository.findById(tender.getTenderId()).isEmpty())
            tenderRepository.save(tender);
        return tenderRepository.findAll();
    }
//    get
    public Optional<Tender> getTenderById(Long id){
        return tenderRepository.findById(id);
    }
//    delete
    public List<Tender> deleteTender(Tender tender){
        if (tenderRepository.findById(tender.getTenderId()).isEmpty()){
            tenderRepository.delete(tender);
            return tenderRepository.findAll();
        }
        return tenderRepository.findAll();
    }
//    update
    /*
    *
    *
    * */
    public Tender updateTender(Tender tender){
        Long id = tender.getTenderId();
        if (tenderRepository.findById(tender.getTenderId()).isPresent()){
            Tender oldTender = tenderRepository.findById(id).get();
            oldTender.setDescription(tender.getDescription());
            oldTender.setDate(tender.getDate());
//            oldTender.setAttachment(tender.getAttachment());
            return tenderRepository.findById(id).get();
        }
        return tenderRepository.findById(id).get();
    }
}
