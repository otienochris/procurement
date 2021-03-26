package com.otienochris.procurement_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@ComponentScan({"com.otienochris.procurement_management_system" , "com.otienochris.procurement_management_system.controllers"})
public class ProcurementManagementSystemApplication {

    public static void main(String[] args) {
//        new File(FileUploaderController.uploadDirectory).mkdir();
        SpringApplication.run(ProcurementManagementSystemApplication.class, args);
    }

}
