package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.RequestForQuotationDto;
import com.otienochris.procurement_management_system.mappers.RequestForQuotationMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.RequestForQuotation;
import com.otienochris.procurement_management_system.repositories.DocumentRepository;
import com.otienochris.procurement_management_system.repositories.RequestForQuotationRepository;
import com.otienochris.procurement_management_system.responses.RequestForQuotationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.Doc;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RequestForQuotationService {

    private final RequestForQuotationRepository requestForQuotationRepository;
    private final RequestForQuotationMapper requestForQuotationMapper;
    private final DocumentService documentService;
    private final DocumentRepository documentRepository;

    public List<RequestForQuotationResponse> allFRQs(){
        List<RequestForQuotationResponse> responses = new ArrayList<>();
        requestForQuotationRepository.findAll().forEach(requestForQuotation -> {
            responses.add(createResponse(requestForQuotation));
        });
        return responses;
    }

    public RequestForQuotationResponse getRFQById(UUID id){
        RequestForQuotation requestForQuotation = requestForQuotationRepository.findById(id).orElseThrow(() -> {
                throw new NoSuchElementException("The RFQ with id: "+ id + " is not available!"); });
        return createResponse(requestForQuotation);
    }

    public RequestForQuotationResponse saveRFQ(RequestForQuotationDto requestForQuotationDto) {
        RequestForQuotation newRequestForQuotation =
                requestForQuotationMapper.requestForQuotationDtoToRequestForQuotation(requestForQuotationDto);
        newRequestForQuotation.setId(UUID.randomUUID());
        newRequestForQuotation.getQuotationDocument().setType("Quotation");
        newRequestForQuotation.getTermsAndConditions().setType("Terms and Conditions");
        RequestForQuotation savedRFQ;
        savedRFQ = requestForQuotationRepository.save(newRequestForQuotation);

        return createResponse(savedRFQ);
    }

    public void updateRFQ(UUID id, RequestForQuotationDto requestForQuotationDto) {

        RequestForQuotation newRfq =
                requestForQuotationMapper.requestForQuotationDtoToRequestForQuotation(requestForQuotationDto);

        requestForQuotationRepository.findById(id).ifPresentOrElse(requestForQuotation -> {

            Document oldTermsAndConditions = requestForQuotation.getTermsAndConditions();
            Document oldQuotation = requestForQuotation.getQuotationDocument();
            Document newTermsAndConditions = newRfq.getTermsAndConditions();
            Document newQuotation = newRfq.getQuotationDocument();

            // if the provided terms and condition exists, just update the content, else overwrite
            if (oldTermsAndConditions.getFileName().equals(newTermsAndConditions.getFileName())){
                documentRepository.findByFileName(newTermsAndConditions.getFileName()).ifPresent(document -> {
                    document.setContent(newTermsAndConditions.getContent());
                    requestForQuotation.setTermsAndConditions(documentRepository.save(document));
                });
            } else {
                newTermsAndConditions.setType("Terms and Conditions");
                requestForQuotation.setTermsAndConditions(documentRepository.save(newTermsAndConditions));
                documentService.deleteFile(oldTermsAndConditions.getFileName());
            }

            // if the provided quotation exist, change the content, else overwrite
            if (oldQuotation.getFileName().equals(newQuotation.getFileName())){
                documentRepository.findByFileName(newQuotation.getFileName()).ifPresent(document -> {
                    document.setContent(newQuotation.getContent());
                    requestForQuotation.setQuotationDocument(documentRepository.save(document));
                });
            } else {
                newQuotation.setType("Quotation attachment");
                requestForQuotation.setQuotationDocument(documentRepository.save(newQuotation));
                documentService.deleteFile(oldQuotation.getFileName());
            }
            // update the message
            requestForQuotation.setMessage(newRfq.getMessage());
            requestForQuotation.setPurchaseOrderId(newRfq.getPurchaseOrderId());
        },
        () -> { throw new NoSuchElementException(" The RFQ with id: " + id + " is not found!");});
    }

    public void deleteById(UUID id) {
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
                .path("/api/v1/documents/download/")
                .path(quotationDocName)
                .toUriString();
        String termsAndConditionUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(termsAndConditionDocName)
                .toUriString();
        return RequestForQuotationResponse.builder()
                .id(request.getId())
                .dateCreated(request.getDateCreated())
                .dateModified(request.getDateModified())
                .description(request.getMessage())
                .purchaseOrderId(request.getPurchaseOrderId())
                .quotationDownloadUrl(quotationUrl)
                .termsAndConditionDownloadUrl(termsAndConditionUrl)
                .build();
    }
}
