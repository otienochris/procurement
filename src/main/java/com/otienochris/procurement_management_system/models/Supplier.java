package com.otienochris.procurement_management_system.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    private String kRA;
    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;

}
