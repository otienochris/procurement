package com.groupwork.Explorers.model.Docs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class NeedDoc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int needDocId;
	
	private String needDocName;
	private String needDocType;
	
	@Lob
	private byte[] needDocdata;

	public NeedDoc() {
		super();
	}

	public NeedDoc(String needDocName, String needDocType, byte[] needDocdata) {
		super();
		this.needDocName = needDocName;
		this.needDocType = needDocType;
		this.needDocdata = needDocdata;
	}

	public int getNeedDocId() {
		return needDocId;
	}

	public void setNeedDocId(int needDocId) {
		this.needDocId = needDocId;
	}

	public String getNeedDocName() {
		return needDocName;
	}

	public void setNeedDocName(String needDocName) {
		this.needDocName = needDocName;
	}

	public String getNeedDocType() {
		return needDocType;
	}

	public void setNeedDocType(String needDocType) {
		this.needDocType = needDocType;
	}

	public byte[] getNeedDocdata() {
		return needDocdata;
	}

	public void setNeedDocdata(byte[] needDocdata) {
		this.needDocdata = needDocdata;
	}
	
	
}
