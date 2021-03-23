package com.group4.procurement.service.newones;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.group4.procurement.dao.newones.OrderManagementRepo;
import com.group4.procurement.model.newones.OrderManagement;
import com.group4.procurement.model.newones.documents.InvoicesDoc;
import com.group4.procurement.response.newones.*;



@Service
public class OrderManagementService {


	private OrderManagementRepo ordermanagementrepo;
	
	public List<OrderManagement> getAllOrderDoc() {
		return ordermanagementrepo.findAll();
	}

	public OrderManagementResponse getOrderDoc(@RequestBody @Validated int orderDocId){

			OrderManagement ordermanagement = ordermanagementrepo.findById(orderDocId).orElseThrow(() -> {throw new NoSuchElementException("The id " + orderDocId + "to this order management doest exist");});
		return OrderManagementResponse(ordermanagement);
	}


	public OrderManagement saveDoc(String status,
								   String goodsreceivednote,
								   String goodsreturnshipment,
								   MultipartFile invoice) {
		OrderManagement ordermanagement = null;

		status = ordermanagement.getStatus();
		goodsreceivednote = ordermanagement.getGoodsReceivedNote();
		goodsreturnshipment = ordermanagement.getGoodsReturnShipment();
		invoice = (MultipartFile) ordermanagement.getInvoices();

		ordermanagement = new OrderManagement(status, goodsreceivednote, goodsreturnshipment, (InvoicesDoc) invoice);

		return ordermanagementrepo.save(ordermanagement);
	}
	
	
	public void deleteAllDoc() {
		ordermanagementrepo.deleteAll();
	}
	
	
	public void deleteDoc(int docId) {
		ordermanagementrepo.delete(ordermanagementrepo.getOne(docId));
	}
	


	public OrderManagementResponse OrderManagementResponse(OrderManagement ordermanagement) {
		InvoicesDoc invoicesdoc = ordermanagement.getInvoices();
		
		String invoicesDocName = StringUtils.cleanPath(invoicesdoc.getInvoicesDocName());
		
		String invoicesDocPath = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/invoices/document/download/")
				.path(invoicesDocName)
				.toUriString();
		
		
		
		  return OrderManagementResponse.builder() 
				  .id(ordermanagement.getOrdermanagementId()) 
				  .invoicesDocUrl(invoicesDocPath)
				  .build();
		 
		
		
	}


}
