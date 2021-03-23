package com.group4.procurement.response.newones;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Builder;





@Builder
public class OrderManagementResponse {
	
	/*
	 * private OrderManagement ordermanagement; private int orderManagementid;
	 * 
	 * 
	 * 
	 * @Override public void getinvoicesDocUrl(String invoicesDocPath) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Override public void getid(int orderManagementid) { int ordermanagementid =
	 * ordermanagement.getOrdermanagementId(); this.orderManagementid =
	 * ordermanagementid; }
	 */
	
	
	  @GeneratedValue(strategy = GenerationType.IDENTITY) 
	  private int id; 
	  private String invoicesDocUrl;
	  
	  
	  
	  public OrderManagementResponse() { super(); }
	  
	  
	  public OrderManagementResponse(int id, String invoicesDocUrl) { 
		  super();
		  this.id = id; 
		  this.invoicesDocUrl = invoicesDocUrl; 
		  }
	  
	  public int getId() { return id; }
	  
	  public void setId(int id) { this.id = id; }
	  
	  public String getInvoicesDocUrl() { return invoicesDocUrl; }
	  
	  public void setInvoicesDocUrl(String invoicesDocUrl) { this.invoicesDocUrl = invoicesDocUrl; }
	 
	

}
