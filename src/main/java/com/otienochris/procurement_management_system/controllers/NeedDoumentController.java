package com.group4.procurement.controllers.newones;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.group4.procurement.model.newones.documents.NeedDocument;
import com.group4.procurement.service.newones.NeedDocumentService;

@RestController
@RequestMapping("/need/document")
public class NeedDoumentController {

	/*
	 * inject service class
	 * upload/save file
	 * get files
	 * get file using id
	 * delete files
	 * delete file
	 */
	
	//inject service class
	@Autowired
	private NeedDocumentService needdocumentservices;
	
	//upload file
	@PostMapping("/upload")
	public ResponseEntity<NeedDocument> uploadFile(MultipartFile file, String description) throws IOException{
		return new ResponseEntity<>(needdocumentservices.saveUpload(file, description) , HttpStatus.CREATED);
	}
	
	//get files
	@GetMapping("/getfiles")
	public ResponseEntity<List<NeedDocument>> getFiles(){
		return new ResponseEntity<>(needdocumentservices.getAllNeedDocuments() , HttpStatus.OK);
	}
	
	//get file using id
	@GetMapping("/getfile/{fileId}")
	public ResponseEntity<NeedDocument> getFile(int fileId){
		return new ResponseEntity<>(needdocumentservices.getNeedDocument(fileId) , HttpStatus.OK);
	}
	
	//delete all files
	@DeleteMapping("/remove/files")
	public ResponseEntity<?> deleteFiles(){
		needdocumentservices.deleteAllNeedDocuments();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//delete single file using fileId
	@DeleteMapping("/remove/{fileId}")
	public ResponseEntity<?> deleteFile(@PathVariable("id") int fileId){
		needdocumentservices.deleteNeedDocument(fileId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/download/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId) {
		NeedDocument needdocument = needdocumentservices.getNeedDocument(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(needdocument.getNeedDocumentType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment:filename=\"" + needdocument.getNeedDocumentName() + "\"")
				.body(new ByteArrayResource(needdocument.getNeedDocumentData()));
	}
}
