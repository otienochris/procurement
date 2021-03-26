package com.otienochris.procurement_management_system.controllers;

import java.util.List;

import com.otienochris.procurement_management_system.models.RFIDoc;
import com.otienochris.procurement_management_system.services.RFIDocStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class RFIDocController {

	@Autowired
	private RFIDocStorageService rdss;
	
	@GetMapping("/rfidocFile")
	public void get() {
		rdss.getFiles();
	}
	
	@PostMapping("/uploadrfidocFile")
	public String uploadMultipleFiles(MultipartFile[] files) {
		for(MultipartFile file :files) {
			rdss.saveFile(file);
		}
		return "redirect:/";
	}
	
	@GetMapping("/downloadrfidocFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		RFIDoc rfidoc = rdss.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(rfidoc.getRfiDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename = \"" + rfidoc.getRfiDocName() + "\"")
				.body(new ByteArrayResource(rfidoc.getRfiDocData()));
	}
	
	/*
	 * @GetMapping("/downloadrfidocFiles") public ResponseEntity<ByteArrayResource>
	 * downloadFiles(){ List<RFIDoc> rfidoc = rdss.getFiles(); return
	 * ResponseEntity.ok() .contentType(MediaType.parseMediaType(((RFIDoc)
	 * rfidoc).getRfiDocType())) .header(HttpHeaders.CONTENT_DISPOSITION,
	 * "attachment:filename = \"" + ((RFIDoc) rfidoc).getRfiDocName() + "\"")
	 * .body(new ByteArrayResource(((RFIDoc) rfidoc).getRfiDocData())); }
	 */
}
