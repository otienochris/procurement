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

import com.group4.procurement.model.newones.documents.AnalysisDoc;
import com.group4.procurement.service.newones.AnalysisDocService;

@RestController
@RequestMapping("/analysis/document")
public class AnalysisDocController {

	@Autowired
	private AnalysisDocService analysisdocservice;

	@GetMapping("/download/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId) {
		AnalysisDoc analysisdoc = analysisdocservice.getFile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(analysisdoc.getAnalysisDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment:filename=\"" + analysisdoc.getAnalysisDocName() + "\"")
				.body(new ByteArrayResource(analysisdoc.getAnalysisDocData()));
	}
}
