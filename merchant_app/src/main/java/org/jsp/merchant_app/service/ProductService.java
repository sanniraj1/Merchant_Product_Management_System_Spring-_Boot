package org.jsp.merchant_app.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchant_app.Exception.IdNotFoundException;
import org.jsp.merchant_app.dao.MerchantDao;
import org.jsp.merchant_app.dao.ProductDao;
import org.jsp.merchant_app.dto.Merchant;
import org.jsp.merchant_app.dto.Product;
import org.jsp.merchant_app.dto.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private MerchantDao mdao;
	@Autowired
	private ProductDao pdao;
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id){
		Optional<Merchant> rm=mdao.findById(merchant_id);
		ResponseStructure<Product> structure =new ResponseStructure<>();
		if(rm.isPresent())
		{
			Merchant m=rm.get();
			m.getProducts().add(product);
			product.setMerchant(m);
			mdao.updateMerchant(m);
			pdao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("product added successfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product, int merchant_id){
		Optional<Merchant> rm=mdao.findById(merchant_id);
		ResponseStructure<Product> structure =new ResponseStructure<>();
		if(rm.isPresent())
		{
			Merchant m=rm.get();
			product.setMerchant(m);// assigning merchant for product
			pdao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("product update successfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
	
	
	public ResponseEntity<ResponseStructure<Product>> findById(int id)
	{
		ResponseStructure<Product> structure=new ResponseStructure<>();
		Optional<Product> rm=pdao.findById(id);
		if(rm.isPresent()) {
			structure.setData(rm.get());
			structure.setMessage("product found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(int merchant_id){
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		Optional<Merchant> rm=mdao.findById(merchant_id);
		if(rm.isPresent()){
			structure.setData(pdao.findByMerchantId(merchant_id));
			structure.setMessage("product found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
		}
		throw new IdNotFoundException(); 			
	}
}
