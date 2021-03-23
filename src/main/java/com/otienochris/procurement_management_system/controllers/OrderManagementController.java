package com.group4.procurement.controllers.newones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.group4.procurement.model.newones.OrderManagement;
import com.group4.procurement.response.newones.OrderManagementResponse;
import com.group4.procurement.service.newones.OrderManagementService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/order/document")
public class OrderManagementController {


	private OrderManagementService ordermanagementservice;

	@GetMapping("/{docid}")
	public ResponseEntity<OrderManagementResponse> getOdermanagement(@PathVariable("orderid") int id){
		return new ResponseEntity<>(ordermanagementservice.getOrderDoc(id), HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<OrderManagement>> getAllOrdermanagement(){
		return new ResponseEntity<>(ordermanagementservice.getAllOrderDoc(), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<OrderManagement> saveOrdermanagement(@RequestParam String status, @RequestParam String goodsreceivenote, @RequestParam String goodsreturnshipment, @RequestBody MultipartFile invoice){
		return new ResponseEntity<>(ordermanagementservice.saveDoc(status, goodsreceivenote, goodsreturnshipment, invoice), HttpStatus.OK);
	}
	
	@DeleteMapping("/{docid}")
	public ResponseEntity<?> deleteOrderManagement(@PathVariable int docId){
		ordermanagementservice.deleteDoc(docId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/deleteall")
	public ResponseEntity<?> deleteAllOrderManagement(){
		ordermanagementservice.deleteAllDoc();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
