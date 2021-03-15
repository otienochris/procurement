package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.QuotationDto;
import com.otienochris.procurement_management_system.models.Quotation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;


@Mapper(uses = MultipartDocumentMapper.class)
public interface QuotationMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "version", target = "version")
    @Mapping(source = "quotationAttachment", target = "quotationAttachment")
    Quotation quotationDtoToQuotation(QuotationDto quotationDto) throws IOException;


//    QuotationDto quotationToQuotationDto(Quotation quotation);
}
