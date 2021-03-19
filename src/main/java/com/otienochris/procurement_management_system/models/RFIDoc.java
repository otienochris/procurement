package com.groupwork.Explorers.model.Docs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
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

	public int getRfiDocId() {
		return rfiDocId;
	}

	public void setRfiDocId(int rfiDocId) {
		this.rfiDocId = rfiDocId;
	}

	public String getRfiDocName() {
		return rfiDocName;
	}

	public void setRfiDocName(String rfiDocName) {
		this.rfiDocName = rfiDocName;
	}

	public String getRfiDocType() {
		return rfiDocType;
	}

	public void setRfiDocType(String rfiDocType) {
		this.rfiDocType = rfiDocType;
	}

	public byte[] getRfiDocData() {
		return rfiDocData;
	}

	public void setRfiDocData(byte[] rfiDocData) {
		this.rfiDocData = rfiDocData;
	}
	
	
	
}
