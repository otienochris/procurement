package com.otienochris.procurement_management_system.Dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

    @Null
    private Long id;

    @NotBlank
    private String title;

    @Null
    private Date dateCreated;

    @Null
    private Date dateModified;

    @Null
    private String fileName;

}
