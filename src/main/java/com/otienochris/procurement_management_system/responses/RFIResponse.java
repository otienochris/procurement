package com.otienochris.procurement_management_system.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RFIResponse {
    private Long id;
    private String rfiUrl;
}
