package com.groupwork.Explorers.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.groupwork.Explorers.Repo.AnalysisDocRepo;
import com.groupwork.Explorers.model.Docs.AnalysisDoc;

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
