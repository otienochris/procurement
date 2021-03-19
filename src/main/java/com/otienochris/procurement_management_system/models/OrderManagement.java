package com.groupwork.Explorers.model;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class OrderManagement {

	@Id
	private int orderId;
	private String status;
	private String goodsReceivedNote;
	private String goodsReturnShipment;
	

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGoodsReceivedNote() {
		return goodsReceivedNote;
	}
	public void setGoodsReceivedNote(String goodsReceivedNote) {
		this.goodsReceivedNote = goodsReceivedNote;
	}
	public String getGoodsReturnShipment() {
		return goodsReturnShipment;
	}
	public void setGoodsReturnShipment(String goodsReturnShipment) {
		this.goodsReturnShipment = goodsReturnShipment;
	}


}
