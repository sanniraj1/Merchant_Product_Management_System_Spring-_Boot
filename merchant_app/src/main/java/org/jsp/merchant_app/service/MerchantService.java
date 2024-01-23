package org.jsp.merchant_app.service;

import java.util.Optional;

import org.jsp.merchant_app.Exception.IdNotFoundException;
import org.jsp.merchant_app.Exception.InvalidCredentialException;
import org.jsp.merchant_app.dao.MerchantDao;
import org.jsp.merchant_app.dto.Merchant;
import org.jsp.merchant_app.dto.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService 
{
	@Autowired
	private MerchantDao dao;

	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant( Merchant merchant) {
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		merchant=dao.saveMerchant(merchant);
		structure.setData(merchant);
		structure.setMessage("merchant registered successfully"+ merchant.getId());
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant( Merchant merchant) {
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		merchant=dao.updateMerchant(merchant);
		structure.setData(merchant);
		structure.setMessage("merchant updated successfully"+ merchant.getId());
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.ACCEPTED);

	}
	
	public ResponseEntity<ResponseStructure<String>> deleteById( int  id)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		boolean deleted=dao.deleteById(id);
		if(deleted)
		{
			structure.setData("merchant deleted");
			structure.setMessage("merchant found ");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);

		}
		structure.setData("merchant  not deleted");
		structure.setMessage("invalid merchant");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);

		
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> findById( int id)
	{
		Optional<Merchant>rm=dao.findById(id);
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		if(rm.isPresent())
		{
		structure.setData(rm.get());
		structure.setMessage("merchant found successfully");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);

		}
//		structure.setData(null);
//		structure.setMessage("invalid merchant");
//		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
//		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.NOT_FOUND);
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant( long phone , String password)
	{

		Optional<Merchant>rm=dao.verifyMerchant(phone, password);
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		if(rm.isPresent())
		{
		structure.setData(rm.get());
		structure.setMessage("merchant verify successfully");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);

		}
//		structure.setData(null);
//		structure.setMessage("invalid merchant");
//		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
//		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
		throw new InvalidCredentialException();


	}
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant( String email , String password)
	{
		Optional<Merchant>rm=dao.verifyMerchant(email, password);
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		if(rm.isPresent())
		{
		structure.setData(rm.get());
		structure.setMessage("merchant verify successfully");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);

		}
//		structure.setData(null);
//		structure.setMessage("invalid merchant");
//		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
//		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.NOT_FOUND);
		throw new InvalidCredentialException();

	}

}
