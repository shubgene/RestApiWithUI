package com.asellion.assignment.assignment.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.asellion.assignment.assignment.Entity.ProductEntity;

@Service
public class ProductDaoService {

	@Autowired
	private ProductRepository productRepository;
	
	public ProductEntity save(ProductEntity product) {
		return productRepository.save(product);
	}
	
	public List<ProductEntity> findAll(){
		return productRepository.findAll();
	}
	
	public Optional<ProductEntity> findById(Integer id) {
		return productRepository.findById(id);
	}
	
	public ProductEntity update(Integer id,int currentPrice,String productname) {
		Optional<ProductEntity> pe=	findById(id);
		if(pe.isPresent()) {
			pe.get().setCurrentPrice(currentPrice);
			pe.get().setName(productname);
		}
		productRepository.save(pe.get());
		return pe.get();
	}
	
	
}
