package com.otienochris.procurement_management_system.controllers;


import com.otienochris.procurement_management_system.models.Company;
import com.otienochris.procurement_management_system.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/all")
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PostMapping("/add")
    public List<Company> addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }
}
