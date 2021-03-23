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

import com.group4.procurement.model.newones.documents.AcquisitionCostDoc;
import com.group4.procurement.service.newones.AcquisitionCostDocServive;

@RestController
@RequestMapping("/acquisition/document")
public class AcquisitionCostDocController {
	

	private AcquisitionCostDocServive acquisitioncostdocservice;

	@GetMapping("/download/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId) {
		AcquisitionCostDoc acquisitioncostdoc = acquisitioncostdocservice.getfile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(acquisitioncostdoc.getAcquisitionCostDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment:filename=\"" + acquisitioncostdoc.getAcquisitionCostDocName() + "\"")
				.body(new ByteArrayResource(acquisitioncostdoc.getAcquisitionCostDocData()));
	}
}
