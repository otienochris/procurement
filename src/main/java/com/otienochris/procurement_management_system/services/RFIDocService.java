package com.group4.procurement.service.newones;

import java.io.IOException;
import java.util.List;

import javax.el.MethodNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.group4.procurement.dao.newones.RFIDocRepo;
import com.group4.procurement.model.newones.documents.RFIDoc;

@Service
public class RFIDocService {

	@Autowired
	private RFIDocRepo rfidocrepo;
	
	public List<RFIDoc> getAllFiles() {
		return rfidocrepo.findAll();
	}
	
	public RFIDoc getFile(int fileid) {
		return rfidocrepo.findById(fileid).orElseThrow(() -> new MethodNotFoundException("RFI file with id " + fileid + "is not found"));
	}
	
	public RFIDoc savefile(MultipartFile file) throws IOException {
		String rfidocName = StringUtils.cleanPath(file.getOriginalFilename());
		
		RFIDoc rfidoc = new RFIDoc(rfidocName, file.getContentType(), file.getBytes());
		return rfidocrepo.save(rfidoc);
	}
	
	public void deleteAllFiles() {
		rfidocrepo.deleteAll();
	}
	
	public void deleteFile(int fileid) {
		rfidocrepo.delete(rfidocrepo.getOne(fileid));
	}
}
