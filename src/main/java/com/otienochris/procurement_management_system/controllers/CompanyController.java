package com.otienochris.procurement_management_system.controllers;


import com.otienochris.procurement_management_system.models.Company;
import com.otienochris.procurement_management_system.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/update")
    public Optional<Company> updateCompany(@RequestBody Company company){
        companyService.updateCompany(company);
        return companyService.getByid(company.getCompanyId());
    }
}
