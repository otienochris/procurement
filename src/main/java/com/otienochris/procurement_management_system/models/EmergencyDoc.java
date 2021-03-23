package com.group4.procurement.model.newones.documents;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class EmergencyDoc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int emergencyDocId;
	
	private String emergencyDocName;
	private String emergencyDocType;
	
	@Lob
	private byte [] emergencyDocData;

	public EmergencyDoc() {
		super();
	}

	public EmergencyDoc(String emergencyDocName, String emergencyDocType, byte[] emergencyDocData) {
		super();
		this.emergencyDocName = emergencyDocName;
		this.emergencyDocType = emergencyDocType;
		this.emergencyDocData = emergencyDocData;
	}

}
