package com.otienochris.procurement_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({"com.otienochris.procurement_management_system" , "com.otienochris.procurement_management_system.controllers"})
public class ProcurementManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcurementManagementSystemApplication.class, args);
    }

}
