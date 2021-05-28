package com.otienochris.procurement_management_system.models;

import com.otienochris.procurement_management_system.models.enums.OMStatusEnum;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Transactional
public class OrderManagement {

    @Id
    @GeneratedValue
    private Integer id;

    @Version
    private int version;
    @CreationTimestamp
    private Timestamp dateCreated;
    @UpdateTimestamp
    private Timestamp dateModified;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private OMStatusEnum status;

    @NotNull
    @Column(columnDefinition = "varchar(100) not null")
    private String goodsReceivedNote;

    @NotNull
    @Column(columnDefinition = "varchar(100) not null")
    private String goodsReturnShipment;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_name")
    private Document invoice;
}
