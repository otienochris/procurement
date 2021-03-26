package com.otienochris.procurement_management_system.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.otienochris.procurement_management_system.models.AcquisitionCostDoc;
import com.otienochris.procurement_management_system.services.AcquisitionCostDocStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class AcquisitionCostDocController {

	@Autowired
	private AcquisitionCostDocStorageService acdss;
	
//	private static final Logger logger =  (Logger) LoggerFactory.getLogger(AcquisitionCostDocController.class);

	@GetMapping("/acquisitionCostDocFile")
	public void get() {
		acdss.getFiles();
	}

	@PostMapping("/uploadSingleAcquisitionCostDocFile")
	public AcquisitionCostDoc uploadSingleFile(MultipartFile file) {
		return acdss.saveFile(file);
	}

	@PostMapping("/uploadAcquisitionCostDocFile")
	public  List<AcquisitionCostDoc> uploadMultipleFiles(MultipartFile[] files) {
		/*
		 * for(MultipartFile file : files) { acdss.saveFile(file); } return
		 * "redirect:/";
		 */

		  return Arrays.asList(files) 
				  .stream() 
				  .map(file -> uploadSingleFile(file))
				  .collect(Collectors.toList());
		 
	}

	@GetMapping("/downloadAcquisitionCostDocFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId) {
		AcquisitionCostDoc acquisitioncostdoc = acdss.getFile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(acquisitioncostdoc.getAcquisitionCostDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment:filename=\"" + acquisitioncostdoc.getAcquisitionCostDocName() + "\"")
				.body(new ByteArrayResource(acquisitioncostdoc.getAcquisitionCostDocData()));
	}
	
	@GetMapping("/downloadallAcquisitionCostDocFile")
	public List<AcquisitionCostDoc> downloadall() {
		return acdss.getFiles();
	}
	
	@GetMapping("dwnldalldocs")
	public void getemall() {
		
	}
}
