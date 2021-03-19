package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.QuotationDto;
import com.otienochris.procurement_management_system.mappers.QuotationMapper;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.Quotation;
import com.otienochris.procurement_management_system.repositories.QuotationRepository;
import com.otienochris.procurement_management_system.responses.QuotationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuotationService {

    private final QuotationRepository quotationRepository;
    private final QuotationMapper quotationMapper;

    public QuotationResponse getQuotationById(Long id){
        Quotation quotation = quotationRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The quotation with id: " + id + " is not found!");
        });
        return createResponse(quotation);
    }

    public List<QuotationResponse> gelAllQuotations(){
        List<QuotationResponse> responses = new ArrayList<>();
        quotationRepository.findAll().forEach(quotation -> {
            responses.add(createResponse(quotation));
        });
        return responses;
    }

    public QuotationResponse saveQuotation(QuotationDto quotationDto){
        Quotation newQuotation = quotationMapper.quotationDtoToQuotation(quotationDto);
        newQuotation.getQuotationAttachment().setType("Quotation");
        return createResponse(quotationRepository.save(newQuotation));
    }

    public void deleteQuotation(Long id){
        quotationRepository.findById(id).ifPresentOrElse( quotationRepository::delete,
                () -> {
                    log.error("item with id " + id + " not found");
                    throw new NoSuchElementException("Item not found! ");});
    }

    public void updateQuotation(Long id, QuotationDto newQuotationDto) throws IOException {
        Quotation newQuotation = quotationMapper.quotationDtoToQuotation(newQuotationDto);

        quotationRepository.findById(id).ifPresentOrElse(
                    quotation -> {
                        quotation.getQuotationAttachment()
                                .setContent(newQuotation.getQuotationAttachment().getContent());
                        quotation.getQuotationAttachment()
                                .setFileName(newQuotation.getQuotationAttachment().getFileName());
                        quotationRepository.save(quotation);
                    }, () -> {
                        throw new NoSuchElementException("Quotation with id " + id + " not found!");
                    });
    }

    private QuotationResponse createResponse(Quotation document){
        String name = StringUtils.cleanPath(
                Objects.requireNonNull(document.getQuotationAttachment().getFileName()));

        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(name)
                .toUriString();

        return QuotationResponse.builder()
                .downloadUrl(url)
                .build();
    }
}
