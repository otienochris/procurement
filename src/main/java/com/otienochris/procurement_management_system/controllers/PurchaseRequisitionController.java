package com.otienochris.procurement_management_system.controllers;

import java.util.List;

import com.otienochris.procurement_management_system.models.PurchaseRequisition;
import com.otienochris.procurement_management_system.services.PurchaseRequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.procurement.response.newones.PurchaseRequisitionResponse;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/purchase/requisition")
public class PurchaseRequisitionController {

	private PurchaseRequisitionService purchaserequisitionservice;

	@GetMapping("/getall")
	public ResponseEntity<List<PurchaseRequisition>> getAllDoc(){
		return new ResponseEntity<>(purchaserequisitionservice.getAllDoc(), HttpStatus.OK);
	}
	
	@GetMapping("/{docid}")
	public ResponseEntity<PurchaseRequisitionResponse> getDoc(@PathVariable("purchaserequisitionid")int docid){
		return new ResponseEntity<>(purchaserequisitionservice.getDoc(docid), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<PurchaseRequisition> saveDoc(String description, MultipartFile needdocument, MultipartFile emergencydocument, MultipartFile acquisitiondocument, MultipartFile analysisdocument){
		return new ResponseEntity<>(purchaserequisitionservice.saveDoc(description, needdocument, emergencydocument, acquisitiondocument, analysisdocument), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteall")
	public ResponseEntity<?> deleteAllDoc(){
		purchaserequisitionservice.deleteAllDoc();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{docid}")
	public ResponseEntity<?> deleteDoc(@PathVariable("docid") int docid){
		purchaserequisitionservice.deleteDoc(docid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
