package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.QuotationDto;
import com.otienochris.procurement_management_system.models.Quotation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.List;


@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface QuotationMapper {

    QuotationMapper INSTANCE = Mappers.getMapper(QuotationMapper.class);

    Quotation quotationDtoToQuotation(QuotationDto quotationDto);
}
