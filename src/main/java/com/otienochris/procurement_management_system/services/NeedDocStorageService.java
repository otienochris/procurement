package com.otienochris.procurement_management_system.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.otienochris.procurement_management_system.models.NeedDoc;
import com.otienochris.procurement_management_system.repositories.NeedDocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class NeedDocStorageService {

	@Autowired
	private NeedDocRepo ndr;
	
	public NeedDoc saveFile(MultipartFile file) {
		String needdocname = StringUtils.cleanPath(file.getOriginalFilename());
		
		
		try {
			NeedDoc needdoc = new NeedDoc(needdocname, file.getContentType(), file.getBytes());
			return ndr.save(needdoc);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Optional<NeedDoc> getFile(Integer fileId) {
		return ndr.findById(fileId);
	}
	
	public List<NeedDoc> getFiles(){
		return ndr.findAll();
	}
}
