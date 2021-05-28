package com.otienochris.procurement_management_system.models;

import com.otienochris.procurement_management_system.models.enums.POStatusEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

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

    @OneToOne(cascade = CascadeType.ALL)
    private Document rfiTemplate;

    @OneToOne(cascade = CascadeType.ALL)
    private Document rfpTemplate;

    @Enumerated(value = EnumType.STRING)
    private POStatusEnum status;

    private Integer purchaseRequisitionId;

    private String description;
}
