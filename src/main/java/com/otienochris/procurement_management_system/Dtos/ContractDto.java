package com.otienochris.procurement_management_system.Dtos;

import com.otienochris.procurement_management_system.models.enums.ContractStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private String supplierId;
    private Date expiryDate;
    private List<ContractStatusEnum> status;
    private MultipartFile contractDocument;
}
