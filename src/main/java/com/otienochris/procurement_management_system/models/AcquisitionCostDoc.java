package com.group4.procurement.model.newones.documents;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
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


}
