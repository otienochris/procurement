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
@ToString

@Entity
@Table(name = "quotations")
public class Quotation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, updatable = false, columnDefinition = "varchar(36)", nullable = false)
    private UUID id;

    @Version
    private Integer version;

    @Column(name = "date_created")
    @CreationTimestamp
    private Timestamp dateCreated;

    @Column(name = "date_modified")
    @UpdateTimestamp
    private Timestamp dateModified;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_name")
    private Document quotationAttachment;

}
