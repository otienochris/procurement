package com.group4.procurement.model.newones;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



import com.group4.procurement.model.newones.documents.AcquisitionCostDoc;
import com.group4.procurement.model.newones.documents.AnalysisDoc;
import com.group4.procurement.model.newones.documents.EmergencyDoc;
import com.group4.procurement.model.newones.documents.NeedDocument;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.Value;

@Entity
@Data
public class PurchaseRequisition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prId;
		
	private String prDescription;
	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	private NeedDocument needDocument;
	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	private EmergencyDoc emergencyDocument;
	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	private AcquisitionCostDoc acquisitionCostDocument;
	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	private AnalysisDoc analysisDocument;
	
	public PurchaseRequisition() {
		super();
	}

	public PurchaseRequisition(String prDescription, NeedDocument needDocument, EmergencyDoc emergencyDocument,
			AcquisitionCostDoc acquisitionCostDocument, AnalysisDoc analysisDocument) {
		super();
		this.prDescription = prDescription;
		this.needDocument = needDocument;
		this.emergencyDocument = emergencyDocument;
		this.acquisitionCostDocument = acquisitionCostDocument;
		this.analysisDocument = analysisDocument;
	}

}
