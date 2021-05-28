package com.otienochris.procurement_management_system.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Contract {
    @Id
    @GeneratedValue
    private Integer contractId;
    private Integer contractYear;
    @CreationTimestamp
    private Timestamp dateAwarded;
    private Timestamp completionDate;

    private Integer solicitationId;

}
