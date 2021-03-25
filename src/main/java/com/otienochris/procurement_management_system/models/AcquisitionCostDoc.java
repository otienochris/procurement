package com.groupwork.Explorers.model.Docs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class AcquisitionCostDoc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int acquisitionCostDocId;
	
	private String acquisitionCostDocName;
	private String acquisitionCostDocType;
	
	@Lob
	private byte[] acquisitionCostDocData;

	public AcquisitionCostDoc() {
		super();
	}

	public AcquisitionCostDoc(String acquisitionCostDocName, String acquisitionCostDocType,
			byte[] acquisitionCostDocData) {
		super();
		this.acquisitionCostDocName = acquisitionCostDocName;
		this.acquisitionCostDocType = acquisitionCostDocType;
		this.acquisitionCostDocData = acquisitionCostDocData;
	}

	public int getAcquisitionCostDocId() {
		return acquisitionCostDocId;
	}

	public void setAcquisitionCostDocId(int acquisitionCostDocId) {
		this.acquisitionCostDocId = acquisitionCostDocId;
	}

	public String getAcquisitionCostDocName() {
		return acquisitionCostDocName;
	}

	public void setAcquisitionCostDocName(String acquisitionCostDocName) {
		this.acquisitionCostDocName = acquisitionCostDocName;
	}

	public String getAcquisitionCostDocType() {
		return acquisitionCostDocType;
	}

	public void setAcquisitionCostDocType(String acquisitionCostDocType) {
		this.acquisitionCostDocType = acquisitionCostDocType;
	}

	public byte[] getAcquisitionCostDocData() {
		return acquisitionCostDocData;
	}

	public void setAcquisitionCostDocData(byte[] acquisitionCostDocData) {
		this.acquisitionCostDocData = acquisitionCostDocData;
	}
	
}
