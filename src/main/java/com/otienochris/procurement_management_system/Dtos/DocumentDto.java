package com.otienochris.procurement_management_system.Dtos;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

    private Long id;
    private String title;
    private Date dateCreated;
    private Date dateModified;
    private String fileName;

}
