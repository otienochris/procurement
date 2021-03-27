package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.RequestForQuotationDto;
import com.otienochris.procurement_management_system.models.RequestForQuotation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface RequestForQuotationMapper {


    RequestForQuotationMapper INSTANCE = Mappers.getMapper(RequestForQuotationMapper.class);

    RequestForQuotation requestForQuotationDtoToRequestForQuotation(RequestForQuotationDto requestForQuotationDto);
}
