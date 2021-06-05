package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.ContractDto;
import com.otienochris.procurement_management_system.models.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    Contract contractDtoToContract(ContractDto contractDto);
}
