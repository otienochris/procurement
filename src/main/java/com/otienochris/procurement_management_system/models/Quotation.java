package com.otienochris.procurement_management_system.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.File;
import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "quotations")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Null
    private Long id;

    @Version
//    @Null
    private Integer version;

    @Null
    @Column(name = "date_created")
    @CreationTimestamp
    private Timestamp dateCreated;

    @Null
    @Column(name = "date_modified")
    @UpdateTimestamp
    private Timestamp dateModified;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private Document quotationAttachment;

}
