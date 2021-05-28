package com.otienochris.procurement_management_system.models;

import com.otienochris.procurement_management_system.models.enums.ContractStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Contract {
    @Id
    @GeneratedValue
    private Integer contractId;
    @CreationTimestamp
    private Timestamp dateAwarded;
    private Timestamp expiryDate;
    @ElementCollection
    private List<ContractStatusEnum> status = new ArrayList<>(3);
    @OneToOne(cascade = CascadeType.ALL)
    private Document contractDocument;
    private String supplierId;
}
