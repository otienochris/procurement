package com.otienochris.procurement_management_system.Dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RFIDto {
    @NotNull(message = "Please include the request for information file")
    private MultipartFile rfi;

    @NotNull(message = "Please add an Id for the Request for Information")
    private Integer purchaseOrderId;

    @NotNull(message = "Description cannot be empty")
    @NotEmpty(message = "Description cannot be empty")
    private String description;

}
