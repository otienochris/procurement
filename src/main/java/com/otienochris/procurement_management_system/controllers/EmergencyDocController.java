package com.groupwork.Explorers.Controllers;

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

import com.groupwork.Explorers.model.Docs.EmergencyDoc;
import com.groupwork.Explorers.service.EmergencyDocStorageService;



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
