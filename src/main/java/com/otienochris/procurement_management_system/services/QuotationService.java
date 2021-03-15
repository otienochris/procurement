package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.QuotationDto;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.Quotation;
import com.otienochris.procurement_management_system.repositories.QuotationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuotationService {

    private final QuotationRepository quotationRepository;

    public Quotation getQuotationById(Long id){
         return quotationRepository.findById(id)
                 .orElseThrow(
                         () -> {throw new IllegalArgumentException("Item not found");}
                 );
    }

    public List<Quotation> gelAllQuotations(){
        return quotationRepository.findAll();
    }

    public Quotation saveQuotation(QuotationDto quotationDto) throws IOException {
        log.info("saving a quotation ... ");

        MultipartFile quotationAttachment = quotationDto.getQuotationAttachment();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(quotationAttachment.getOriginalFilename()));
        Document document = Document.builder()
                .id(quotationDto.getId())
                .version(quotationDto.getVersion())
                .content(quotationAttachment.getBytes())
                .fileName(fileName)
                .title("Quotation")
                .build();
        log.info("Quotation with file:  "+ fileName + " saved successfully.");

        return quotationRepository.save(
                Quotation.builder()
                        .quotationAttachment(document)
                        .build()
        );
    }

//    delete
    public void deleteQuotation(Long id){
        log.info("In the deleteQuotation method of the quotation service class");
        quotationRepository.findById(id).ifPresentOrElse( quotationRepository::delete,
                () -> {
                    log.error("item with id " + id + " not found");
                    throw new IllegalArgumentException("Item not found! ");});
    }

//    update
    public void updateQuotation(Long id, QuotationDto newQuotationDto) throws IOException {
        log.info("In update Quotation method of the quotation service");

        Document newAttachment = Document.builder()
                .title("Quotation")
                .fileName(newQuotationDto.getQuotationAttachment().getOriginalFilename())
                .content(newQuotationDto.getQuotationAttachment().getBytes())
                .build();

        quotationRepository.findById(id)
                .ifPresentOrElse(
                    quotation -> {
                        quotation.setQuotationAttachment(newAttachment);
                        quotationRepository.save(quotation);
                        log.info("Quotation with id " + id + " saved successfully");
                    }, () -> {
                        log.error("Quotation with id " + id + " not found!");
                        throw new IllegalArgumentException("Quotation with id " + id + " not found!");
                    });
    }
}
