package com.otienochris.procurement_management_system.services;

import java.io.IOException;
import java.util.List;


import javax.el.MethodNotFoundException;

import com.otienochris.procurement_management_system.models.AcquisitionCostDoc;
import com.otienochris.procurement_management_system.repositories.AcquisitionCostDocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AcquisitionCostDocStorageService {

	@Autowired
	private AcquisitionCostDocRepo acdr;
	
	public AcquisitionCostDoc saveFile(MultipartFile file) {
		
		String acquisitiondocname = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			AcquisitionCostDoc acquisitioncostdoc = new AcquisitionCostDoc(acquisitiondocname, file.getContentType(), file.getBytes());
			return acdr.save(acquisitioncostdoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public AcquisitionCostDoc getFile(Integer fileId) {
		return acdr.findById(fileId)
				.orElseThrow(() -> new MethodNotFoundException("File noot found wit id " + fileId));
	}
	
	public List<AcquisitionCostDoc> getFiles() {
		return acdr.findAll();
	}
	

		
}
