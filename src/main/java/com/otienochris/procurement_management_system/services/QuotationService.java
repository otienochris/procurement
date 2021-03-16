package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.QuotationDto;
import com.otienochris.procurement_management_system.mappers.QuotationMapper;
import com.otienochris.procurement_management_system.models.Quotation;
import com.otienochris.procurement_management_system.repositories.QuotationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuotationService {

    private final QuotationRepository quotationRepository;
    private final QuotationMapper quotationMapper;

    public QuotationDto getQuotationById(Long id){
        Optional<Quotation> quotation = quotationRepository.findById(id);
        if (quotation.isEmpty())
            throw new NoSuchElementException("The quotation with id: " + id + " is not found!");
        return quotationMapper.quotationToQuotationDto(quotation.get());
    }

    public List<QuotationDto> gelAllQuotations(){
        return quotationMapper.quotationsToQuotationDtos(quotationRepository.findAll());
    }

    public QuotationDto saveQuotation(QuotationDto quotationDto){
        log.info("saving a quotation ... ");
        Quotation newQuotation = quotationMapper.quotationDtoToQuotation(quotationDto);
        newQuotation.getQuotationAttachment().setType("Quotation");
        return quotationMapper.quotationToQuotationDto(
                quotationRepository.save(newQuotation)
        );
    }

//    delete
    public void deleteQuotation(Long id){
        log.info("In the deleteQuotation method of the quotation service class");
        quotationRepository.findById(id).ifPresentOrElse( quotationRepository::delete,
                () -> {
                    log.error("item with id " + id + " not found");
                    throw new NoSuchElementException("Item not found! ");});
    }

//    update
    public void updateQuotation(Long id, QuotationDto newQuotationDto) throws IOException {
        log.info("In update Quotation method of the quotation service");

        Quotation newQuotation = quotationMapper.quotationDtoToQuotation(newQuotationDto);

        quotationRepository.findById(id)
                .ifPresentOrElse(
                    quotation -> {
                        quotation.getQuotationAttachment().setContent(
                                newQuotation.getQuotationAttachment().getContent());
                        quotation.getQuotationAttachment().setFileName(
                                newQuotation.getQuotationAttachment().getFileName()
                        );
                        quotationRepository.save(quotation);
                        log.info("Quotation with id " + id + " saved successfully");
                    }, () -> {
                        log.error("Quotation with id " + id + " not found!");
                        throw new NoSuchElementException("Quotation with id " + id + " not found!");
                    });
    }
}
