package com.otienochris.procurement_management_system.models;

import com.otienochris.procurement_management_system.models.enums.POStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "applications")
public class ApplicationObject {
    @Id
    @GeneratedValue
    private Integer id;

    @Version
    private Integer version;

    @CreationTimestamp
    @Column(name = "date_created")
    private Timestamp dateCreated;

    @UpdateTimestamp
    @Column(name = "date_modified")
    private Timestamp dateModified;

    @Column(columnDefinition = "varchar(500)")
    private String message;

    @OneToOne(cascade = CascadeType.ALL)
    private Document informationDocument;

    @OneToOne(cascade = CascadeType.ALL)
    private Document quotationDocument;

    private Integer purchaseOrderId;
    private String supplierId;
    private POStatusEnum status;
}
