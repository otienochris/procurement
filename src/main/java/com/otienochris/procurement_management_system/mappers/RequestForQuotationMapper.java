package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.RequestForQuotationDto;
import com.otienochris.procurement_management_system.models.RequestForQuotation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;


@Mapper(uses = MultipartDocumentMapper.class)
public interface RequestForQuotationMapper {
//    RequestForQuotationDto requestForQuotationToRequestForQuotationDto(RequestForQuotation requestForQuotation);

    @Mapping(source = "message", target = "message")
    @Mapping(source = "purchaseOrderId", target = "purchaseOrderId")
    @Mapping(source = "quotationDocument", target = "quotationDocument")
    @Mapping(source = "termsAndConditions", target = "termsAndConditions")
    RequestForQuotation requestForQuotationDtoToRequestForQuotation(RequestForQuotationDto requestForQuotationDto) throws IOException;
}
