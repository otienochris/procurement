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

import com.groupwork.Explorers.model.Docs.AnalysisDoc;
import com.groupwork.Explorers.service.AnalysisDocStorageServive;

@Controller
public class AnalysisDocController {

	@Autowired
	private AnalysisDocStorageServive adss;
	
	@GetMapping("/analysisDocFile")
	public void get() {
		adss.getFiles();
	}
	
	@PostMapping("/uploadAnalysisDocFile")
	public String uploadMultipleFiles(MultipartFile[] files) {
		for(MultipartFile file : files) {
			adss.saveFile(file);
		}
		return "redirect:/";
	}
	
	@GetMapping("/downloadAnalysisDoc/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		AnalysisDoc analysisdoc = adss.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(analysisdoc.getAnalysisDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename = \"" + analysisdoc.getAnalysisDocName() + "\"")
				.body(new ByteArrayResource(analysisdoc.getAnalysisDocData()));
	}
}
