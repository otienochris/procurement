package com.groupwork.Explorers.model.Docs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.groupwork.Explorers.model.FileCompoundedAllInfoNecessary;


@Entity
public class FileUploadResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileId;
	@Column
	private String fileName;
	@Column
	private String fileType;
	
	@Column
	@Lob
	private byte [] fileData;
	
	/*
	 * @OneToOne(mappedBy = "fileuploadresponse") private
	 * FileCompoundedAllInfoNecessary filecompoundedalinfonecessary;
	 */

	public FileUploadResponse() {
		super();
	}

	public FileUploadResponse(String fileName, String fileType, byte[] fileData) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileData = fileData;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	
	
}
