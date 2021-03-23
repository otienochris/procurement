package com.group4.procurement.service.newones;

import java.io.IOException;
import java.util.List;

import javax.el.MethodNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.group4.procurement.dao.newones.AnalysisDocRepo;
import com.group4.procurement.model.newones.documents.AnalysisDoc;

@Service
public class AnalysisDocService {

	@Autowired
	private AnalysisDocRepo analysisdocrepo;
	
	public List<AnalysisDoc> getAllFiles() {
		return analysisdocrepo.findAll();
	}
	
	public AnalysisDoc getFile(int fileid) {
		return analysisdocrepo.findById(fileid).orElseThrow(() -> new MethodNotFoundException("Analysis file with id " + fileid + "is not found"));
	}
	
	public AnalysisDoc savefile(MultipartFile file) throws IOException {
		String analysisdocName = StringUtils.cleanPath(file.getOriginalFilename());
		
		AnalysisDoc analysisdoc = new AnalysisDoc(analysisdocName, file.getContentType(), file.getBytes());
		return analysisdocrepo.save(analysisdoc);
	}
	
	public void deleteAllFiles() {
		analysisdocrepo.deleteAll();
	}
	
	public void deleteFile(int fileid) {
		analysisdocrepo.delete(analysisdocrepo.getOne(fileid));
	}
}