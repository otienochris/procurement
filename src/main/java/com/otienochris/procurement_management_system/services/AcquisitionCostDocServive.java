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
public class AcquisitionCostDocServive {


	private AcquisitionCostDocRepo acquisitioncostdocrepo;
	
	public AcquisitionCostDoc getfile(int fileid) {
		return acquisitioncostdocrepo.findById(fileid).orElseThrow(() -> new MethodNotFoundException("File with file Id " + fileid + "is not found"));
	}
	
	public List<AcquisitionCostDoc> getAllFiles() {
		return acquisitioncostdocrepo.findAll();
	}
	
	public AcquisitionCostDoc savefile(MultipartFile file) throws IOException {
		String acquisitioncostdocName = StringUtils.cleanPath(file.getOriginalFilename());
		
		AcquisitionCostDoc acquisitionacostdoc = new AcquisitionCostDoc(acquisitioncostdocName, file.getContentType(), file.getBytes());
		return acquisitioncostdocrepo.save(acquisitionacostdoc);
	}
	
	public void deleteAllfiles() {
		acquisitioncostdocrepo.deleteAll();
	}
	
	public void deletefile(int fileid) {
		acquisitioncostdocrepo.delete(acquisitioncostdocrepo.getOne(fileid));
	}
}
