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

    @OneToOne(cascade = CascadeType.ALL)
    private Document goodsReceivedNote;

    @OneToOne(cascade = CascadeType.ALL)
    private Document goodsReturnShipment;

    @OneToOne(cascade = CascadeType.ALL)
    private Document invoice;

    private Integer purchaseOrderId;

    private OMStatusEnum supplierApproval;
    private OMStatusEnum departmentHeadApproval;
    private OMStatusEnum storeManagerApproval;
    private OMStatusEnum procurementOfficerApproval;
}
