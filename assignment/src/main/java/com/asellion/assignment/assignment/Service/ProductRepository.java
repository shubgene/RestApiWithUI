package com.asellion.assignment.assignment.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asellion.assignment.assignment.Entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

	
}
