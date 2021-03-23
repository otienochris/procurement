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


import com.group4.procurement.model.newones.documents.EmergencyDoc;

import com.group4.procurement.service.newones.EmergencyDocService;

@RestController
@RequestMapping("/emergency/document")
public class EmergencyDocController {

	@Autowired
	private EmergencyDocService emergencydocservice;

	@GetMapping("/download/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId) {
		EmergencyDoc emergencydoc = emergencydocservice.getFile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(emergencydoc.getEmergencyDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment:filename=\"" + emergencydoc.getEmergencyDocName() + "\"")
				.body(new ByteArrayResource(emergencydoc.getEmergencyDocData()));
	}
}
