package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.RequestForQuotationDto;
import com.otienochris.procurement_management_system.mappers.RequestForQuotationMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.RequestForQuotation;
import com.otienochris.procurement_management_system.repositories.RequestForQuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RequestForQuotationService {

    @Autowired
    private RequestForQuotationRepository requestForQuotationRepository;

    @Autowired
    RequestForQuotationMapper requestForQuotationMapper;

    public List<RequestForQuotationDto> allFRQs(){
        return requestForQuotationMapper.requestForQuotationsToRequestForQuotationDtos(requestForQuotationRepository.findAll());
    }

    public RequestForQuotationDto getRFQById(Long id){
        Optional<RequestForQuotation> requestForQuotation = requestForQuotationRepository.findById(id);
        if (requestForQuotation.isEmpty())
            throw new NoSuchElementException("The RFQ is not available!");
        return requestForQuotationMapper.requestForQuotationToRequestForQuotationDto(requestForQuotation.get());
    }

    public RequestForQuotationDto saveRFQ(RequestForQuotationDto requestForQuotationDto){
        RequestForQuotation newRequestForQuotation =
                requestForQuotationMapper.requestForQuotationDtoToRequestForQuotation(requestForQuotationDto);
        newRequestForQuotation.getQuotationDocument().setType("Quotation");
        newRequestForQuotation.getTermsAndConditions().setType("Terms and Conditions");

        return requestForQuotationMapper.requestForQuotationToRequestForQuotationDto(
                requestForQuotationRepository.save(newRequestForQuotation)
        );
    }

    public void updateRFQ(Long id, RequestForQuotationDto requestForQuotationDto) {

        RequestForQuotation newRfq =
                requestForQuotationMapper.requestForQuotationDtoToRequestForQuotation(requestForQuotationDto);

        requestForQuotationRepository.findById(id).ifPresentOrElse(requestForQuotation -> {

            Document newTermsAndConditions = newRfq.getTermsAndConditions();
            requestForQuotation.getTermsAndConditions().setContent(newTermsAndConditions.getContent());
            requestForQuotation.getTermsAndConditions().setFileName(newTermsAndConditions.getFileName());


            Document newQuotationDocument = newRfq.getQuotationDocument();
            requestForQuotation.getQuotationDocument().setContent(newQuotationDocument.getContent());
            requestForQuotation.getQuotationDocument().setFileName(newQuotationDocument.getFileName());

            requestForQuotation.setMessage(newRfq.getMessage());
            requestForQuotation.setPurchaseOrderId(newRfq.getPurchaseOrderId());

            requestForQuotationRepository.save(requestForQuotation);
        },
        () -> { throw new NoSuchElementException(" The RFQ with id: " + id + " is not found!");});
    }

    public void deleteById(Long id) {
        requestForQuotationRepository.findById(id).ifPresentOrElse(requestForQuotationRepository::delete, () -> {
            throw new NoSuchElementException("RFQ with id: " + id + " not found!");
        });
    }
}
