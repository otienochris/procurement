package com.otienochris.procurement_management_system.services;


import java.util.List;
import java.util.NoSuchElementException;

import com.otienochris.procurement_management_system.models.*;
import com.otienochris.procurement_management_system.repositories.PurchaseRequisitionRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.group4.procurement.response.newones.PurchaseRequisitionResponse;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class PurchaseRequisitionService {

	private PurchaseRequisitionRepo purchaserequisitionrepo;
	
	
	
	public List<PurchaseRequisition> getAllDoc() {
		return purchaserequisitionrepo.findAll();
	}

	//please check on the return type
	public PurchaseRequisitionResponse getDoc(int DocId) {
		PurchaseRequisition purchaserequisition = purchaserequisitionrepo.findById(DocId).orElseThrow(() -> {
            throw new NoSuchElementException("The Purchase requisition with Id: " + DocId + " does not exist!");
        });
		
		return createResponse(purchaserequisition);
	}

	public PurchaseRequisition saveDoc(String description, MultipartFile needdocument, MultipartFile emergencydocument, MultipartFile acquisitiondocument, MultipartFile analysisdocument) {
		PurchaseRequisition purchaserequisition = null;
		description = purchaserequisition.getPrDescription();
		needdocument = (MultipartFile) purchaserequisition.getNeedDocument();
		emergencydocument = (MultipartFile) purchaserequisition.getEmergencyDocument();
		acquisitiondocument = (MultipartFile) purchaserequisition.getAcquisitionCostDocument();
		analysisdocument = (MultipartFile) purchaserequisition.getAnalysisDocument();

		purchaserequisition = new PurchaseRequisition(description, (NeedDocument) needdocument, (EmergencyDoc) emergencydocument, (AcquisitionCostDoc) acquisitiondocument, (AnalysisDoc) analysisdocument);
		
		return purchaserequisitionrepo.save(purchaserequisition);
	}
	
	public void deleteAllDoc() {
		purchaserequisitionrepo.deleteAll();
	}
	
	public void deleteDoc(int docId) {
		purchaserequisitionrepo.delete(purchaserequisitionrepo.getOne(docId));
	}

	public PurchaseRequisitionResponse createResponse(PurchaseRequisition purchaserequisition) {
        
        NeedDocument needDocument = purchaserequisition.getNeedDocument();
        AcquisitionCostDoc acquisitioncostDoc = purchaserequisition.getAcquisitionCostDocument();
        AnalysisDoc analysisDoc = purchaserequisition.getAnalysisDocument();
        EmergencyDoc emergencyDoc = purchaserequisition.getEmergencyDocument();
        
        String needDocumentName = StringUtils.cleanPath(needDocument.getNeedDocumentName());
        String acquisitioncostDocName = StringUtils.cleanPath(acquisitioncostDoc.getAcquisitionCostDocName());
        String analysisDocName = StringUtils.cleanPath(analysisDoc.getAnalysisDocName());
        String emergencyDocName = StringUtils.cleanPath(emergencyDoc.getEmergencyDocName());
        
        //please update path after creating controllers
        String needDocumentPath = ServletUriComponentsBuilder.fromCurrentContextPath()
        		.path("/need/document/download/{fileId}")
        		.path(needDocumentName)
        		.toUriString();
        String acquisitioncostDocPath = ServletUriComponentsBuilder.fromCurrentContextPath()
        		.path("/acquisition/document/download/{fileId}")
        		.path(acquisitioncostDocName)
        		.toUriString();
        String analysisDocPath = ServletUriComponentsBuilder.fromCurrentContextPath()
        		.path("/analysis/document/download/{fileId}")
        		.path(analysisDocName)
        		.toUriString();
        String emergencyDocPath = ServletUriComponentsBuilder.fromCurrentContextPath()
        		.path("/emergency/document/download/{fileId}")
        		.path(emergencyDocName)
        		.toUriString();


         
		return PurchaseRequisitionResponse.builder()
				.id(purchaserequisition.getPrId())
				.needDocumentUrl(needDocumentPath)
				.acquisitionCostDocUrl(acquisitioncostDocPath)
				.analysisDocUrl(analysisDocPath)
				.emergencyDocUrl(emergencyDocPath)
				.build();
	}
}
