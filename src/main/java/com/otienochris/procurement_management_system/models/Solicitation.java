package com.otienochris.procurement_management_system.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Solicitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Null
    private Long id;

    @Version
    @Null
    private Integer version;

    @CreationTimestamp
    @Null
    @Column(name = "date_created")
    private Timestamp dateCreated;

    @UpdateTimestamp
    @Null
    @Column(name = "date_modified")
    private Timestamp dateModified;

    @NotNull
    @Column(name = "deadline_date")
    private LocalDate deadlineDate;

    @OneToOne
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id")
    private PurchaseOrder purchaseOrder;
}
