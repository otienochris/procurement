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

import com.group4.procurement.model.newones.documents.InvoicesDoc;
import com.group4.procurement.service.newones.InvoicesDocService;

@RestController
@RequestMapping("/invoices/document")
public class InvoicesDocController {


	@Autowired
	private InvoicesDocService invoicesdocservice;

	@GetMapping("/download/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId) {
		InvoicesDoc invoicesdoc = invoicesdocservice.getFile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(invoicesdoc.getInvoicesDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment:filename=\"" + invoicesdoc.getInvoicesDocName() + "\"")
				.body(new ByteArrayResource(invoicesdoc.getInvoicesDocData()));
	}
}
