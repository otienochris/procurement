package com.otienochris.procurement_management_system.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private int version;
    @CreationTimestamp
    private Timestamp dateCreated;
    @UpdateTimestamp
    private Timestamp dateModified;
    @NotNull
    private OMStatus status;

    @NotNull
    @Column(columnDefinition = "varchar(100) not null")
    private String goodsReceivedNote;
    @NotNull
    @Column(columnDefinition = "varchar(100) not null")
    private String goodsReturnShipment;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Document invoice;
}
