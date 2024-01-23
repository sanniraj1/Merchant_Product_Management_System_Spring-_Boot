package org.jsp.merchant_app.dao;

import java.util.Optional;

import org.jsp.merchant_app.dto.Merchant;
import org.jsp.merchant_app.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {

	@Autowired
	private MerchantRepository repository;

	public Merchant saveMerchant(Merchant merchant) {
		//System.out.println(repository);
		return repository.save(merchant);
	}

	public Merchant updateMerchant(Merchant merchant) {
		//System.out.println(repository);
		return repository.save(merchant);
	}

	public Optional<Merchant> findById(int id) {
		return repository.findById(id);

	}

	public boolean deleteById(int id) {
		Optional<Merchant> rm = findById(id);
		if (rm.isPresent()) {
			// repository.delete(rm.get());
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	public Optional<Merchant> verifyMerchant(long phone, String password) {
		return repository.verifyMerchant(phone, password);
	}
	
	public Optional<Merchant> verifyMerchant(String email, String password)
	{
		return repository.verifyMerchant(email, password);
	}
	

}
