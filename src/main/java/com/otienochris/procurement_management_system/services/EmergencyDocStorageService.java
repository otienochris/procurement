package com.groupwork.Explorers.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.groupwork.Explorers.Repo.EmergencyDocRepo;
import com.groupwork.Explorers.model.Docs.EmergencyDoc;

@Service
public class EmergencyDocStorageService {

	@Autowired
	private EmergencyDocRepo edr;
	
	public EmergencyDoc saveFile(MultipartFile file) {
		String emergencydocname = StringUtils.cleanPath(file.getOriginalFilename());
		
		EmergencyDoc emergencydoc;
		try {
			emergencydoc = new EmergencyDoc(emergencydocname, file.getContentType(), file.getBytes());
			return edr.save(emergencydoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<EmergencyDoc> getFile(Integer fileId) {
		return edr.findById(fileId);
	}
	
	public List<EmergencyDoc> getFiles() {
		return edr.findAll();
	}
}
