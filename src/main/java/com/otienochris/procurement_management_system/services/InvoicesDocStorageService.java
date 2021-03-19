package com.groupwork.Explorers.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.groupwork.Explorers.Repo.InvoicesDocRepo;
import com.groupwork.Explorers.model.Docs.InvoicesDoc;

@Service
public class InvoicesDocStorageService {

	@Autowired
	private InvoicesDocRepo idr;
	
	public InvoicesDoc saveFile(MultipartFile file) {
		String invoicesdocname = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			InvoicesDoc invoicesdoc = new InvoicesDoc(invoicesdocname, file.getContentType(), file.getBytes());
			return idr.save(invoicesdoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<InvoicesDoc> getFile(Integer fileId) {
		return idr.findById(fileId);
	}
	
	public List<InvoicesDoc> getFiles() {
		return idr.findAll();
	}
}
