package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.POStatus;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderDto {
    @Null
    private Long id;

    @NotNull
    private MultipartFile rfiTemplate;

    @NotNull
    private MultipartFile rfpTemplate;

    private POStatus status;
}
