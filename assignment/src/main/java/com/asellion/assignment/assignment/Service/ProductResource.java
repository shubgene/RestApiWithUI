package com.asellion.assignment.assignment.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.asellion.assignment.assignment.Entity.ProductEntity;
import com.asellion.assignment.assignment.Exception.ProductNotFoundException;

@RestController
@RequestMapping("/api/products")
public class ProductResource {
	
	@Autowired
	private ProductDaoService service;
	

	//retrieveAlluproduct
	@GetMapping
	public List<ProductEntity> getAll(){
		return service.findAll();
	}
	
	//retrieveSingleProduct Based on Id
	@GetMapping("/{id}")
	public ProductEntity getProduct(@PathVariable int id) throws ProductNotFoundException{
		Optional<ProductEntity> product=service.findById(id);
		if(!product.isPresent())
			throw new ProductNotFoundException("id-"+id);	
		/*
		 * +"/users" Resource<User> resource=new Resource<User>(user);
		 * //ControllerLinkBuilder is used to create link for method
		 * ControllerLinkBuilder linkto= linkTo(methodOn(this.getClass(), getAll()));
		 * resource.add(linkto.withRel("All_user"))
		 */;
		return product.get();
	}
	
	//Create product
	@PostMapping
	public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductEntity product) {
		System.out.print(product.getCurrentPrice());
		ProductEntity prd=service.save(product);
		//extension of HTTP entity with status code
		//URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prd.getId()).toUri();
		//ResponseEntity is part of HTTP Entity , use the created status and pass the information to client.
		return new ResponseEntity<Object>(product, HttpStatus.CREATED);
	}
	
	
	//Create product
	@PutMapping("/{id}")
	public ProductEntity updateProduct(@Valid @RequestBody ProductEntity product,@PathVariable int id) throws ProductNotFoundException {
			Optional<ProductEntity> prd=service.findById(id);
			if(!prd.isPresent())
				throw new ProductNotFoundException("id-"+id);	
			prd.get().setCurrentPrice(product.getCurrentPrice());
			prd.get().setName(product.getName());
			service.save(prd.get());
			return prd.get();
		}
	
		
			
				

	
}
