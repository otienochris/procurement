package com.otienochris.procurement_management_system.mappers;

import com.otienochris.procurement_management_system.Dtos.SolicitationDto;
import com.otienochris.procurement_management_system.models.Solicitation;
import org.mapstruct.Mapper;


@Mapper(uses = MultipartDocumentMapper.class)
public interface SolicitationMapper {
    SolicitationDto solicitationToSolicitationDto(Solicitation solicitation);
    Solicitation solicitationDtoToSolicitation(SolicitationDto solicitationDto);
}
