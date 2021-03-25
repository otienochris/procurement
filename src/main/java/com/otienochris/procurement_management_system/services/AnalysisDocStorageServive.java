package com.otienochris.procurement_management_system.services;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.otienochris.procurement_management_system.models.AnalysisDoc;
import com.otienochris.procurement_management_system.repositories.AnalysisDocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AnalysisDocStorageServive {

	@Autowired
	private AnalysisDocRepo adr;
	
	public AnalysisDoc saveFile(MultipartFile file) {
		String analysisdocname = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			AnalysisDoc analysisdoc = new AnalysisDoc(analysisdocname, file.getContentType(), file.getBytes());
			return adr.save(analysisdoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<AnalysisDoc> getFile(Integer fileId) {
		return adr.findById(fileId);
	}
	
	public List<AnalysisDoc> getFiles() {
		return adr.findAll();
	}
}
