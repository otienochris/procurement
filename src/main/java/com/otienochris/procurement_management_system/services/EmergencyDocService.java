package com.group4.procurement.service.newones;

import java.io.IOException;
import java.util.List;

import javax.el.MethodNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.group4.procurement.dao.newones.EmergencyDocRepo;
import com.group4.procurement.model.newones.documents.EmergencyDoc;

@Service
public class EmergencyDocService {

	@Autowired
	private EmergencyDocRepo emergencydocrepo;
	
	public List<EmergencyDoc> getAllFiles() {
		return emergencydocrepo.findAll();
	}
	
	public EmergencyDoc getFile(int fileid) {
		return emergencydocrepo.findById(fileid).orElseThrow(() -> new MethodNotFoundException("Emergency file with id " + fileid + "is not found"));
	}
	
	public EmergencyDoc savefile(MultipartFile file) throws IOException {
		String emergencydocName = StringUtils.cleanPath(file.getOriginalFilename());
		
		EmergencyDoc emergencydoc = new EmergencyDoc(emergencydocName, file.getContentType(), file.getBytes());
		return emergencydocrepo.save(emergencydoc);
	}
	
	public void deleteAllFiles() {
		emergencydocrepo.deleteAll();
	}
	
	public void deleteFile(int fileid) {
		emergencydocrepo.delete(emergencydocrepo.getOne(fileid));
	}
}
