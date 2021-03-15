package com.otienochris.procurement_management_system.Dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuotationDto {

    @Null
    private Long id;

    @Null
    private Integer version;

    @NotNull
    private MultipartFile quotationAttachment;
}
