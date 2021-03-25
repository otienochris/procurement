package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.models.EmergencyDoc;
import com.otienochris.procurement_management_system.services.EmergencyDocStorageService;
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
public class EmergencyDocController {

	@Autowired
	private EmergencyDocStorageService edss;
	
	@GetMapping("emergencyFile")
	public void getPages() {
		edss.getFiles();
	}
	
	@PostMapping("/uploadEmergencyFile")
	public String uploadMultipleFiles(MultipartFile[] files) {
		for(MultipartFile file : files) {
			edss.saveFile(file);
		}
		return "redirect:/";
	}
	
	@GetMapping("/downloadEmergencyFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		EmergencyDoc emergencydoc = edss.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(emergencydoc.getEmergencyDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename = \"" + emergencydoc.getEmergencyDocName() + "\"")
				.body(new ByteArrayResource(emergencydoc.getEmergencyDocData()));
	}
}
