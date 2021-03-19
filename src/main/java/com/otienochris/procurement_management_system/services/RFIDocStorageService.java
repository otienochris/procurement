package com.groupwork.Explorers.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.groupwork.Explorers.Repo.RFIDocRepo;
import com.groupwork.Explorers.model.Docs.RFIDoc;

@Service
public class RFIDocStorageService {

	@Autowired
	private RFIDocRepo rdr;
	
	public RFIDoc saveFile(MultipartFile file) {
		String rfidocname = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			RFIDoc rfidoc = new RFIDoc(rfidocname, file.getContentType(), file.getBytes());
			return rdr.save(rfidoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<RFIDoc> getFile(Integer fileId) {
		return rdr.findById(fileId);
	}
	
	public List<RFIDoc> getFiles() {
		return rdr.findAll();
	}	
}
