package com.otienochris.procurement_management_system.models;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Component
@Entity
public class Contract {
    @Id
    private int contractId;
    private int contractYear;
    private LocalDate dateAwarded;
    private Long contractDurationDays;


    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getContractYear() {
        return contractYear;
    }

    public void setContractYear(int contractYear) {
        this.contractYear = contractYear;
    }

    public LocalDate getDateAwarded() {
        return dateAwarded;
    }

    public void setDateAwarded(LocalDate dateAwarded) {
        this.dateAwarded = dateAwarded;
    }

    public Long getContractDurationDays() {
        return contractDurationDays;
    }

    public void setContractDurationDays(Long contractDurationDays) {
        this.contractDurationDays = contractDurationDays;
    }

    @Override
    public String toString() {
        return "Contract [contractId=" + contractId + ", contractYear=" + contractYear + ", dateAwarded=" + dateAwarded
                + ", contractDurationDays=" + contractDurationDays + "]";
    }


}
