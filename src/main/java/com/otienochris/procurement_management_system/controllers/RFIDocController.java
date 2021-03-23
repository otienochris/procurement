package com.group4.procurement.controllers.newones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.procurement.model.newones.documents.RFIDoc;
import com.group4.procurement.service.newones.RFIDocService;

@RestController
@RequestMapping("/RFI/document")
public class RFIDocController {
	
	@Autowired
	private RFIDocService rfidocservice;

	@GetMapping("/download/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId) {
		RFIDoc rfidoc = rfidocservice.getFile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(rfidoc.getRfiDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment:filename=\"" + rfidoc.getRfiDocName() + "\"")
				.body(new ByteArrayResource(rfidoc.getRfiDocData()));
	}
}
