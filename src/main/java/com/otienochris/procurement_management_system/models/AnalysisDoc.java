package com.groupwork.Explorers.model.Docs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class AnalysisDoc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int analysisDocId;
	
	private String analysisDocName;
	private String analysisDocType;
	
	@Lob
	private byte [] analysisDocData;

	public AnalysisDoc() {
		super();
	}

	public AnalysisDoc(String analysisDocName, String analysisDocType, byte[] analysisDocData) {
		super();
		this.analysisDocName = analysisDocName;
		this.analysisDocType = analysisDocType;
		this.analysisDocData = analysisDocData;
	}

	public int getAnalysisDocId() {
		return analysisDocId;
	}

	public void setAnalysisDocId(int analysisDocId) {
		this.analysisDocId = analysisDocId;
	}

	public String getAnalysisDocName() {
		return analysisDocName;
	}

	public void setAnalysisDocName(String analysisDocName) {
		this.analysisDocName = analysisDocName;
	}

	public String getAnalysisDocType() {
		return analysisDocType;
	}

	public void setAnalysisDocType(String analysisDocType) {
		this.analysisDocType = analysisDocType;
	}

	public byte[] getAnalysisDocData() {
		return analysisDocData;
	}

	public void setAnalysisDocData(byte[] analysisDocData) {
		this.analysisDocData = analysisDocData;
	}
	
	
}
