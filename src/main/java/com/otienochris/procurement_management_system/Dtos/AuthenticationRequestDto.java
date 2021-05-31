package com.otienochris.procurement_management_system.Dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
