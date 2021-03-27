package com.otienochris.procurement_management_system.configs.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

//import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@ComponentScan(basePackages = {"com.otienochris.procurement_management_system.controllers"})
@EnableSwagger2
public class SwaggerConfig {


    // this allows one to customize what is documented
    @Bean
    public Docket procurementApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select() // instance of API selector
                .apis(RequestHandlerSelectors.basePackage("com.otienochris.procurement_management_system"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Procurement Management System API",
                "An API that reduce paper works, automate notifications and link key stakeholders",
                "1.0.0",
                "Free To Use",
                new springfox.documentation.service.Contact("Otieno Christopher Ochieng", "http://otienochris.com", "ohtischris@gmail.com"),
                "API License",
                "http://otienochris.com",
                Collections.emptyList());
    }


}
