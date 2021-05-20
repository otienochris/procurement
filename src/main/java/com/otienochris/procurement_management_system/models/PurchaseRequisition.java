package com.otienochris.procurement_management_system.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Transactional
@Table(name = "purchase_requisitions")
public class PurchaseRequisition {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Version
    private Integer version;

    @CreationTimestamp
    private Timestamp dateCreated;

    @UpdateTimestamp
    private Timestamp dateModified;

    @OneToOne(cascade = CascadeType.ALL)
    private Document needDocument;

    @OneToOne(cascade = CascadeType.ALL)
    private Document emergencyDocument;

    @OneToOne(cascade = CascadeType.ALL)
    private Document acquisitionDocument;

    @OneToOne(cascade = CascadeType.ALL)
    private Document analysisDocument;
}
