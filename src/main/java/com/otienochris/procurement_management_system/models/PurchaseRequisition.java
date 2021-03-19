package com.groupwork.Explorers.model;



import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PurchaseRequisition {

	@Id
	private int prId;
	private String prDescription;

	
	
	public int getPrId() {
		return prId;
	}
	public void setPrId(int prId) {
		this.prId = prId;
	}
	public String getPrDescription() {
		return prDescription;
	}
	public void setPrDescription(String prDescription) {
		this.prDescription = prDescription;
	}

	
	
}
