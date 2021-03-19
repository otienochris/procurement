package com.groupwork.Explorers.model.Docs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
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

	public int getEmergencyDocId() {
		return emergencyDocId;
	}

	public void setEmergencyDocId(int emergencyDocId) {
		this.emergencyDocId = emergencyDocId;
	}

	public String getEmergencyDocName() {
		return emergencyDocName;
	}

	public void setEmergencyDocName(String emergencyDocName) {
		this.emergencyDocName = emergencyDocName;
	}

	public String getEmergencyDocType() {
		return emergencyDocType;
	}

	public void setEmergencyDocType(String emergencyDocType) {
		this.emergencyDocType = emergencyDocType;
	}

	public byte[] getEmergencyDocData() {
		return emergencyDocData;
	}

	public void setEmergencyDocData(byte[] emergencyDocData) {
		this.emergencyDocData = emergencyDocData;
	}
	
		
}
