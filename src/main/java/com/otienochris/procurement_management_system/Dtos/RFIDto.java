package com.otienochris.procurement_management_system.Dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RFIDto {
    @NotNull
    private MultipartFile rfi;

}
