package com.group4.procurement.response.newones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderManagementResponse {

    private long id;
    private String invoiceUrl;

}
