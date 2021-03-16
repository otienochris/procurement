package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.SolicitationDto;
import com.otienochris.procurement_management_system.models.Solicitation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(uses = MultipartDocumentMapper.class, componentModel = "spring")
public interface SolicitationMapper {

    SolicitationMapper INSTANCE = Mappers.getMapper(SolicitationMapper.class);

    SolicitationDto solicitationToSolicitationDto(Solicitation solicitation);
    Solicitation solicitationDtoToSolicitation(SolicitationDto solicitationDto);
}
