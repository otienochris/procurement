package com.otienochris.procurement_management_system.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

//    @Null
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Null
    @Version
    private Integer version;

    @CreationTimestamp
//    @Null
    @Column(name = "date_created")
    private Timestamp dateCreated;

    @UpdateTimestamp
//    @Null
    @Column(name = "date_modified")
    private Timestamp dateModified;

    @NotNull(message = "Please upload the ")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Document rfiTemplate;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private Document rfpTemplate;

    @NotNull
    private POStatus status;
}
