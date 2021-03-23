package com.group4.procurement.model.newones.documents;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
public class InvoicesDoc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invoicesDocId;
	
	private String invoicesDocName;
	private String invoicesDocType;
	
	@Lob
	private byte[] invoicesDocData;

	public InvoicesDoc() {
		super();
	}

	public InvoicesDoc(String invoicesDocName, String invoicesDocType, byte[] invoicesDocData) {
		super();
		this.invoicesDocName = invoicesDocName;
		this.invoicesDocType = invoicesDocType;
		this.invoicesDocData = invoicesDocData;
	}

}
