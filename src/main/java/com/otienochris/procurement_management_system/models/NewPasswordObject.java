package com.otienochris.procurement_management_system.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordObject {
    private String token;
    private String newPassword;
}
