package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.RequestForQuotationDto;
import com.otienochris.procurement_management_system.mappers.RequestForQuotationMapper;
import com.otienochris.procurement_management_system.models.RequestForQuotation;
import com.otienochris.procurement_management_system.repositories.RequestForQuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RequestForQuotationService {

    @Autowired
    private RequestForQuotationRepository requestForQuotationRepository;

    @Autowired
    RequestForQuotationMapper requestForQuotationMapper;

    public List<RequestForQuotation> allFRQs(){
//        todo get list of rfq dtos
        return requestForQuotationRepository.findAll();
    }

    public RequestForQuotationDto getRFQById(Long id){
        Optional<RequestForQuotation> requestForQuotation = requestForQuotationRepository.findById(id);
        if (requestForQuotation.isEmpty())
            return null;
        return requestForQuotationMapper.requestForQuotationToRequestForQuotationDto(requestForQuotation.get());
    }

    public RequestForQuotationDto saveRFQ(RequestForQuotationDto requestForQuotationDto) throws IOException {
        RequestForQuotation newRequestForQuotation = requestForQuotationMapper.
                requestForQuotationDtoToRequestForQuotation(requestForQuotationDto);
        return requestForQuotationMapper.requestForQuotationToRequestForQuotationDto(
                requestForQuotationRepository.save(newRequestForQuotation)
        );
    }

    public void updateRFQ(Long id, RequestForQuotationDto requestForQuotationDto) {

        RequestForQuotation newRequestForQuotation =
                requestForQuotationMapper.requestForQuotationDtoToRequestForQuotation(requestForQuotationDto);

        requestForQuotationRepository.findById(id).ifPresentOrElse(requestForQuotation -> {
                    requestForQuotation.setQuotationDocument(newRequestForQuotation.getQuotationDocument());
                    requestForQuotation.setMessage(newRequestForQuotation.getMessage());
                    requestForQuotation.setPurchaseOrderId(newRequestForQuotation.getPurchaseOrderId());
                    requestForQuotation.setTermsAndConditions(newRequestForQuotation.getTermsAndConditions());
                    requestForQuotationRepository.save(requestForQuotation);
                },
                () -> { throw new IllegalArgumentException(" ");});
    }

    public void deleteById(Long id) {
        requestForQuotationRepository.findById(id).ifPresentOrElse(requestForQuotationRepository::delete, () -> {
            throw new IllegalArgumentException("RFQ with id: " + id + " not found!");
        });
    }
}
