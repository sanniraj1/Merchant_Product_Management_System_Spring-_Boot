package org.jsp.merchant_app.controller;

import org.jsp.merchant_app.dto.Merchant;
import org.jsp.merchant_app.dto.ResponseStructure;
import org.jsp.merchant_app.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantController {
	@Autowired
	private MerchantService service;

	@PostMapping("/merchants")
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant) {

		return service.saveMerchant(merchant);
	}
	@PutMapping("/merchants")
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {

		return service.updateMerchant(merchant);
	}
	@DeleteMapping("/merchants/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id) {

		return service.deleteById(id);
	}
	@GetMapping("/merchants/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable int id) {

		return service.findById(id);
	}
	@PostMapping("merchants/verify-by-phone")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam long phone,
			@RequestParam String password) {
		return service.verifyMerchant(phone, password);

	}
	@PostMapping("merchants/verify-by-email")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam String email,
			@RequestParam String password) {
return service.verifyMerchant(email, password);
	}
}
