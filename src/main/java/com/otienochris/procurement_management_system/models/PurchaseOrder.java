package com.otienochris.procurement_management_system.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, updatable = false, columnDefinition = "varchar", nullable = false)
    private UUID id;

    @Version
    private Integer version;

    @CreationTimestamp
    @Column(name = "date_created")
    private Timestamp dateCreated;

    @UpdateTimestamp
    @Column(name = "date_modified")
    private Timestamp dateModified;

    @NotNull(message = "Please upload the ")
    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "file_name")
    private Document rfiTemplate;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "file_name")
    private Document rfpTemplate;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private POStatus status;
}
