package com.group4.procurement.model.newones.documents;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
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


}
