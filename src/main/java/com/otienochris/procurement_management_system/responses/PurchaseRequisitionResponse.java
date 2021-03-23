package com.group4.procurement.response.newones;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Builder;


@Builder
public class PurchaseRequisitionResponse {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String needDocumentUrl;
	private String acquisitionCostDocUrl;
	private String analysisDocUrl;
	private String emergencyDocUrl;
	
	public PurchaseRequisitionResponse() {
		super();
	}

	public PurchaseRequisitionResponse(String needDocumentUrl, String acquisitionCostDocUrl, String analysisDocUrl,
			String emergencyDocUrl) {
		super();
		this.needDocumentUrl = needDocumentUrl;
		this.acquisitionCostDocUrl = acquisitionCostDocUrl;
		this.analysisDocUrl = analysisDocUrl;
		this.emergencyDocUrl = emergencyDocUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNeedDocumentUrl() {
		return needDocumentUrl;
	}

	public void setNeedDocumentUrl(String needDocumentUrl) {
		this.needDocumentUrl = needDocumentUrl;
	}

	public String getAcquisitionCostDocUrl() {
		return acquisitionCostDocUrl;
	}

	public void setAcquisitionCostDocUrl(String acquisitionCostDocUrl) {
		this.acquisitionCostDocUrl = acquisitionCostDocUrl;
	}

	public String getAnalysisDocUrl() {
		return analysisDocUrl;
	}

	public void setAnalysisDocUrl(String analysisDocUrl) {
		this.analysisDocUrl = analysisDocUrl;
	}

	public String getEmergencyDocUrl() {
		return emergencyDocUrl;
	}

	public void setEmergencyDocUrl(String emergencyDocUrl) {
		this.emergencyDocUrl = emergencyDocUrl;
	}



}
