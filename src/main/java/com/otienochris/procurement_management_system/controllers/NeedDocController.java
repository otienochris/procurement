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

import com.groupwork.Explorers.model.Docs.NeedDoc;
import com.groupwork.Explorers.service.NeedDocStorageService;

@Controller
public class NeedDocController {

	@Autowired
	private NeedDocStorageService ndss;
	
	@GetMapping("/needDocFile")
	public void getPage() {
		ndss.getFiles();
		
	}
	
	@PostMapping("/uploadNeedDocFiles")
	public void uploadMultiFiles(MultipartFile[] files) {
		for(MultipartFile file : files) {
			ndss.saveFile(file);
		}
		
	}
	
	@GetMapping("/downloadNeedDocFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		NeedDoc needdoc = ndss.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(needdoc.getNeedDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION , "attachment:filename=\"" + needdoc.getNeedDocName() + "\"")
				.body(new ByteArrayResource(needdoc.getNeedDocdata()));
	}
}
