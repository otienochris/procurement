package com.otienochris.procurement_management_system.services;


import java.io.IOException;
import java.util.List;

import javax.el.MethodNotFoundException;

import com.otienochris.procurement_management_system.models.NeedDocument;
import com.otienochris.procurement_management_system.repositories.NeedDocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;



@Service
public class NeedDocumentService {

	/*
	 * inject repository interface
	 * create save/upload method
	 * create get method for all files
	 * create get method for a single file using fileId
	 * delete method for all files
	 * delete method for a single file using fileId
	 */
	
	//repo interface injected
	@Autowired
	private NeedDocumentRepo needdocumentrepo;
	
	
	//save method
	public NeedDocument saveUpload(MultipartFile file, String description) throws IOException {
	/*
	 * Create a name to hold file name
	 * call the args constractor
	 * save the instance in repository
	 */
		
		String needdocumentname = StringUtils.cleanPath(file.getOriginalFilename());
		
		NeedDocument needDocument = new NeedDocument(description, needdocumentname, file.getContentType(), file.getBytes());
		
		return needdocumentrepo.save(needDocument);
	}
	
	//get all files method
	public List<NeedDocument> getAllNeedDocuments() {
		return needdocumentrepo.findAll();
	}
	

	//get single file method by fileId
	public NeedDocument getNeedDocument(int fileId) {
		return needdocumentrepo.findById(fileId).orElseThrow(() -> new MethodNotFoundException("File with fileId " + fileId + " not found"));
	}
	
	//delete all files method
	public void deleteAllNeedDocuments() {
		needdocumentrepo.deleteAll();
	}
	
	//delete single file by fileId
	public void deleteNeedDocument(int fileId) {
		/*
		 * NeedDocument needDocumtDlt = needdocumentrepo.getOne(fileId);
		 * needdocumentrepo.delete(needDocumtDlt);
		 */
		needdocumentrepo.delete(needdocumentrepo.getOne(fileId));
	}
	
	/*
	 * //get single file method by fileId public NeedDocument
	 * getNeedDocumentwithresponse(int fileId) { NeedDocument nd =
	 * needdocumentrepo.findById(fileId).orElseThrow(() -> new
	 * MethodNotFoundException("File with fileId " + fileId + " not found")); return
	 * createResponse(nd); }
	 */


}
