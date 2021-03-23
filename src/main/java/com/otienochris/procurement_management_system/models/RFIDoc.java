package com.group4.procurement.model.newones.documents;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class RFIDoc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rfiDocId;
	
	private String rfiDocName;
	private String rfiDocType;
	
	@Lob
	private byte [] rfiDocData;

	public RFIDoc() {
		super();
	}

	public RFIDoc(String rfiDocName, String rfiDocType, byte[] rfiDocData) {
		super();
		this.rfiDocName = rfiDocName;
		this.rfiDocType = rfiDocType;
		this.rfiDocData = rfiDocData;
	}

}
