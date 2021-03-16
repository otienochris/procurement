package com.group4.procurement1.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Supplier {
	@Id
	private int supplierId;
	private String supplierName;
	private String kraPin;
	private String businessType;
	private String address;
	private String status;
	
	
	
	public int getSupplierId() {return supplierId;}

	public void setSupplierId(int supplierId) {this.supplierId = supplierId;}

	public String getSupplierName() {return supplierName;}
	
	public void setSupplierName(String supplierName) {this.supplierName = supplierName;}
	
	public String getKraPin() {return kraPin;}
	
	public void setKraPin(String kraPin) {this.kraPin = kraPin;}
	
	public String getBusinessType() {return businessType;}
	
	public void setBusinessType(String businessType) {this.businessType = businessType;}
	
	public String getAddress() {return address;}
	
	public void setAddress(String address) {this.address = address;}
	
	public String getStatus() {return status;}
	
	public void setStatus(String status) {this.status = status;}

	@Override
	public String toString() {
		return "Suppliers [supplierId=" + supplierId + ", supplierName=" + supplierName + ", kraPin=" + kraPin
				+ ", businessType=" + businessType + ", address=" + address + ", status=" + status + "]";
	}


	
	

}
