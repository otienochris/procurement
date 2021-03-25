package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.models.InvoicesDoc;
import com.otienochris.procurement_management_system.services.InvoicesDocStorageService;
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
public class InvoicesDocController {

	@Autowired
	private InvoicesDocStorageService idss;
	
	@GetMapping("/invoicesDocFile")
	public void get() {
		idss.getFiles();
	}
	
	@PostMapping("/uploadInvoicesDocFile")
	public String uploadMultipleFiles(MultipartFile[] files) {
		for(MultipartFile file : files) {
			idss.saveFile(file);
		}
		return "redirect:/";
	}
	
	@GetMapping("/downloadInvoicesDocFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		InvoicesDoc invoicesdoc = idss.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(invoicesdoc.getInvoicesDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename = \"" + invoicesdoc.getInvoicesDocName() + "\"")
				.body(new ByteArrayResource(invoicesdoc.getInvoicesDocData()));
	}
}
