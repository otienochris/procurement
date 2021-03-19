package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.RequestForQuotationDto;
import com.otienochris.procurement_management_system.mappers.RequestForQuotationMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.RequestForQuotation;
import com.otienochris.procurement_management_system.repositories.RequestForQuotationRepository;
import com.otienochris.procurement_management_system.responses.RequestForQuotationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RequestForQuotationService {

    private final RequestForQuotationRepository requestForQuotationRepository;
    private final RequestForQuotationMapper requestForQuotationMapper;


    public List<RequestForQuotationResponse> allFRQs(){
        List<RequestForQuotationResponse> responses = new ArrayList<>();
        requestForQuotationRepository.findAll().forEach(requestForQuotation -> {
            responses.add(createResponse(requestForQuotation));
        });
        return responses;
    }

    public RequestForQuotationResponse getRFQById(Long id){
        RequestForQuotation requestForQuotation = requestForQuotationRepository.findById(id).orElseThrow(() -> {
                throw new NoSuchElementException("The RFQ with id: "+ id + " is not available!"); });
        return createResponse(requestForQuotation);
    }

    public RequestForQuotationResponse saveRFQ(RequestForQuotationDto requestForQuotationDto) {
        RequestForQuotation newRequestForQuotation =
                requestForQuotationMapper.requestForQuotationDtoToRequestForQuotation(requestForQuotationDto);
        newRequestForQuotation.getQuotationDocument().setType("Quotation");
        newRequestForQuotation.getTermsAndConditions().setType("Terms and Conditions");
        RequestForQuotation savedRFQ;
        savedRFQ = requestForQuotationRepository.save(newRequestForQuotation);

        return createResponse(savedRFQ);
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

    private RequestForQuotationResponse createResponse(RequestForQuotation request){
        Document quotationDoc = request.getQuotationDocument();
        Document termsAndConditionDoc = request.getTermsAndConditions();

        String quotationDocName = StringUtils.cleanPath(Objects.requireNonNull(quotationDoc.getFileName()));
        String termsAndConditionDocName = StringUtils.cleanPath(Objects.requireNonNull(termsAndConditionDoc.getFileName()));

        String quotationUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/RFQs/download/")
                .path(quotationDocName)
                .toUriString();
        String termsAndConditionUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/RFQs/download/")
                .path(termsAndConditionDocName)
                .toUriString();
        return RequestForQuotationResponse.builder()
                .dateCreated(request.getDateCreated())
                .dateModified(request.getDateModified())
                .description(request.getMessage())
                .purchaseOrderId(request.getPurchaseOrderId())
                .quotationDownloadUrl(quotationUrl)
                .termsAndConditionDownloadUrl(termsAndConditionUrl)
                .build();
    }
}
