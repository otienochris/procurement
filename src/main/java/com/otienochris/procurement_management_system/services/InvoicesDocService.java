package com.group4.procurement.service.newones;

import java.io.IOException;
import java.util.List;

import javax.el.MethodNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.group4.procurement.dao.newones.InvoicesDocRepo;
import com.group4.procurement.model.newones.documents.InvoicesDoc;

@Service
public class InvoicesDocService {

	@Autowired
	private InvoicesDocRepo invoicesdocrepo;
	
	public List<InvoicesDoc> getAllFiles() {
		return invoicesdocrepo.findAll();
	}
	
	public InvoicesDoc getFile(int fileid) {
		return invoicesdocrepo.findById(fileid).orElseThrow(() -> new MethodNotFoundException("Invoice file with id " + fileid + "is not found"));
	}
	
	public InvoicesDoc savefile(MultipartFile file) throws IOException {
		String invoicesdocName = StringUtils.cleanPath(file.getOriginalFilename());
		
		InvoicesDoc invoicesdoc = new InvoicesDoc(invoicesdocName, file.getContentType(), file.getBytes());
		return invoicesdocrepo.save(invoicesdoc);
	}
	
	public void deleteAllFiles() {
		invoicesdocrepo.deleteAll();
	}
	
	public void deleteFile(int fileid) {
		invoicesdocrepo.delete(invoicesdocrepo.getOne(fileid));
	}
}
