package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.RequestForQuotationDto;
import com.otienochris.procurement_management_system.models.RequestForQuotation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.List;


@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface RequestForQuotationMapper {


    RequestForQuotationMapper INSTANCE = Mappers.getMapper(RequestForQuotationMapper.class);

    RequestForQuotation requestForQuotationDtoToRequestForQuotation(RequestForQuotationDto requestForQuotationDto);
}
