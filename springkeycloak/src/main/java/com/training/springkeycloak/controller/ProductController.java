package com.training.springkeycloak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.springkeycloak.dao.Product;
import com.training.springkeycloak.dao.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository repo;
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		return (List<Product>) repo.findAll();
	}
	
	@PostMapping("/save")
	public Product saveProduct(@RequestBody Product prd) {
		return repo.save(prd);
	}
	
}
