package com.groupwork.Explorers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.groupwork.Explorers.model.OrderManagement;

public class OrderManagementService {
	@Autowired
	OrderManagement om;

	@RequestMapping("orderaccepted")
	public void acceptedOrder() {
		om.setStatus("accepted");
	}
	
	@RequestMapping("orderdenied")
	public void deniedOrder() {
		om.setStatus("denied");
	}
	
	@RequestMapping("grnapproved")
	public void approvedGRN() {
		om.setGoodsReceivedNote("approved");
	}
	
	@RequestMapping("grnrejected")
	public void rejectedGRN() {
		om.setGoodsReceivedNote("rejected");
	}
	
	@RequestMapping("grsapproved")
	public void approvedGRS() {
		om.setGoodsReturnShipment("approved");
	}
	
	@RequestMapping("grsrejected")
	public void rejectedGRS() {
		om.setGoodsReturnShipment("rejected");
	}	
	
	
}
