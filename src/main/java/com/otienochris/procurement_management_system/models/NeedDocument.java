package com.groupwork.Explorers.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NeedDocument {

	@Id
	private int needDocID;
	private String needDescription;
	
	
	public int getNeedDocID() {
		return needDocID;
	}
	public void setNeedDocID(int needDocID) {
		this.needDocID = needDocID;
	}
	public String getNeedDescription() {
		return needDescription;
	}
	public void setNeedDescription(String needDescription) {
		this.needDescription = needDescription;
	}

}
