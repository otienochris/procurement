package com.otienochris.procurement_management_system.services;
import com.otienochris.procurement_management_system.*;

import com.otienochris.procurement_management_system.Dtos.RFIDto;
import com.otienochris.procurement_management_system.mappers.RFIMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.RFI;
import com.otienochris.procurement_management_system.repositories.RFIRepo;
import com.otienochris.procurement_management_system.responses.RFIResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RFIService {

    private final RFIRepo rfiRepo;
    private final RFIMapper rfiMapper;


    public List<RFIResponse> getAll(){
        List<RFIResponse> responses = new ArrayList<>();
        rfiRepo.findAll().forEach(rfi -> {
            responses.add(createResponse(rfi));
        });
        return responses;
    }

    public RFIResponse getById(UUID id){

        RFI rfi = rfiRepo.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The RFI with Id: " + id + " does not exist!");
        });
        return createResponse(rfi);
    }

    public RFIResponse saveRFI(RFIDto rfiDto) {
        RFI rfi = rfiMapper.rfiDtoToRfi(rfiDto);
        rfi.getRfi().setType("RFI Document");
        RFI savedRfi = rfiRepo.save(rfi);


        return createResponse(savedRfi);
    }

    public void updateRFI(UUID id, RFIDto rfiDto){
        RFI newrfi = rfiMapper.rfiDtoToRfi(rfiDto);

        rfiRepo.findById(id).ifPresentOrElse(
                rfi -> {
                    rfi.setRfi(newrfi.getRfi());
                    rfiRepo.save(rfi);

                },() -> {
                    throw new NoSuchElementException("Item with id: " + id + " not found");
                }
        );
    }

    public void delete(UUID id){
        rfiRepo.findById(id).ifPresentOrElse(
                rfiRepo::delete
                ,() -> { throw new NoSuchElementException("Item not found! "); });
    }


    public RFIResponse createResponse(RFI rfi) {
        Document rfiDocument = rfi.getRfi();

        String rfiName = StringUtils.cleanPath(rfiDocument.getFileName());

        String rfiDocumentPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(rfiName)
                .toUriString();


        return RFIResponse.builder()
                .id(rfi.getId())
                .rfiUrl(rfiDocumentPath)
                .build();
    }
}