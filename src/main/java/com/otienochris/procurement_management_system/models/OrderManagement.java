package com.group4.procurement.model.newones;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.group4.procurement.model.newones.documents.InvoicesDoc;
import com.sun.istack.NotNull;
import lombok.Data;


@Entity
@Data
public class OrderManagement {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ordermanagementId;
	private String status;
	private String goodsReceivedNote;
	private String goodsReturnShipment;
	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	private InvoicesDoc invoices;
	
	public OrderManagement() {
		super();
	}

	public OrderManagement(String status, String goodsReceivedNote, String goodsReturnShipment, InvoicesDoc invoices) {
		super();
		this.status = status;
		this.goodsReceivedNote = goodsReceivedNote;
		this.goodsReturnShipment = goodsReturnShipment;
		this.invoices = invoices;
	}

}
