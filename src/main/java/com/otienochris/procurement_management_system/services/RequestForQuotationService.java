package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.RequestForQuotationDto;
import com.otienochris.procurement_management_system.exception_handlers.ResourceNotFoundException;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.RequestForQuotation;
import com.otienochris.procurement_management_system.repositories.RequestForQuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class RequestForQuotationService {

    @Autowired
    private RequestForQuotationRepository requestForQuotationRepository;

//    todo all RFQs
    public List<RequestForQuotation> allFRQs(){
        return requestForQuotationRepository.findAll();
    }

//    todo get a RFQ by id
    public RequestForQuotation getRFQById(Long id){
        return requestForQuotationRepository.findById(id).orElseThrow(
                () -> {throw new IllegalArgumentException("RFQ with id: " + id + " not found!");}
        );
    }

//    todo upload a RFQ
    public RequestForQuotation saveRFQ(RequestForQuotationDto requestForQuotationDto) throws IOException {
        MultipartFile quotationDocument = requestForQuotationDto.getQuotationDocument();
        MultipartFile termsAndConditions = requestForQuotationDto.getTermsAndConditions();

//        todo use map struct to create mappers
        RequestForQuotation requestForQuotation = RequestForQuotation.builder()
                .message(requestForQuotationDto.getMessage())
                .purchaseOrderId(requestForQuotationDto.getPurchaseOrderId())
                .quotationDocument(Document.builder()
                        .title("Quotation document")
                        .fileName(quotationDocument.getOriginalFilename())
                        .content(quotationDocument.getBytes())
                        .build())
                .termsAndConditions(Document.builder()
                        .title("Terms and conditions")
                        .fileName(termsAndConditions.getOriginalFilename())
                        .content(termsAndConditions.getBytes())
                        .build())
                .build();
        return requestForQuotationRepository.save(requestForQuotation);
    }

//    todo update a RFQ
public void updateRFQ(Long id, RequestForQuotationDto requestForQuotationDto) throws IOException {

        requestForQuotationRepository.findById(id).ifPresentOrElse(requestForQuotation -> {

            Document quotationDocument = null;
            try {
                quotationDocument = Document.builder()
                        .title("Quotation document")
                        .fileName(requestForQuotationDto.getQuotationDocument().getOriginalFilename())
                        .content(requestForQuotationDto.getQuotationDocument().getBytes())
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Document termsAndConditions = null;
            try {
                termsAndConditions = Document.builder()
                        .title("Terms and conditions")
                        .fileName(requestForQuotationDto.getTermsAndConditions().getOriginalFilename())
                        .content(requestForQuotationDto.getTermsAndConditions().getBytes())
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
            }

//        todo use map struct to create mappers
            if (quotationDocument != null && termsAndConditions!= null){
                    requestForQuotation = RequestForQuotation.builder()
                            .message(requestForQuotationDto.getMessage())
                            .purchaseOrderId(requestForQuotationDto.getPurchaseOrderId())
                            .quotationDocument(quotationDocument)
                            .termsAndConditions(termsAndConditions)
                            .build();
                    requestForQuotationRepository.save(requestForQuotation);
                }
            },
            () -> { throw new IllegalArgumentException(" ");});
}

    public void deleteById(Long id) {
        requestForQuotationRepository.findById(id).ifPresentOrElse(requestForQuotationRepository::delete, () -> {
            throw new IllegalArgumentException("RFQ with id: " + id + " not found!");
        });
    }
}
