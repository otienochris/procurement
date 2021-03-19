package com.groupwork.Explorers.model.Docs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;



@Entity
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

	public int getInvoicesDocId() {
		return invoicesDocId;
	}

	public void setInvoicesDocId(int invoicesDocId) {
		this.invoicesDocId = invoicesDocId;
	}

	public String getInvoicesDocName() {
		return invoicesDocName;
	}

	public void setInvoicesDocName(String invoicesDocName) {
		this.invoicesDocName = invoicesDocName;
	}

	public String getInvoicesDocType() {
		return invoicesDocType;
	}

	public void setInvoicesDocType(String invoicesDocType) {
		this.invoicesDocType = invoicesDocType;
	}

	public byte[] getInvoicesDocData() {
		return invoicesDocData;
	}

	public void setInvoicesDocData(byte[] invoicesDocData) {
		this.invoicesDocData = invoicesDocData;
	}
	
	
}
