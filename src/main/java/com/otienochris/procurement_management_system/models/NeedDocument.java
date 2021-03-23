package com.group4.procurement.model.newones.documents;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
public class NeedDocument {

	/*
	 * create an id for the doc
	 * create a description for the doc
	 * create name for the doc
	 * create a string to hold the type
	 * create storage for the file(bytes)
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int needDocumentId;
	
	private String needDescription;
	private String needDocumentName;
	private String needDocumentType;
	
	@Lob
	private byte[] needDocumentData;
	
	
	/*
	 * constractor without arguments constractor with - docdesc, docName,docType,
	 * docData
	 */
	  
	  public NeedDocument() { super(); }
	  
	  
	  public NeedDocument(String needDescription, String needDocumentName, String
	  needDocumentType, byte[] needDocumentData) { super(); this.needDescription =
	  needDescription; this.needDocumentName = needDocumentName;
	  this.needDocumentType = needDocumentType; this.needDocumentData =
	  needDocumentData; }

}
