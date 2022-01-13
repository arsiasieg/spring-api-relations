package com.wcs.workshoprelations.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wcs.workshoprelations.dto.CategoryDto;
import com.wcs.workshoprelations.entity.Category;
import com.wcs.workshoprelations.entity.Product;
import com.wcs.workshoprelations.repository.CategoryRepository;
import com.wcs.workshoprelations.repository.ProductRepository;


@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
//	@GetMapping("/{id}/product/{idProduct}")
//	public List<Category> categories(){
//		return categoryRepository.findAll();
//	}
	
	//Create
	@PostMapping
	public Category create(@Valid @RequestBody CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDisplayOrder(categoryDto.getDisplayOrder());

		return categoryRepository.save(category);
	}
	
	//Get all
	@GetMapping
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
	
	//Get one
	@GetMapping("/{id}")
	public Category getById(@PathVariable(required = true) Long id) {
		Optional<Category> optCategory = categoryRepository.findById(id);
		
		if(optCategory.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return optCategory.get();
	}
	
	//Update
	@PutMapping("/{id}")
	public Category update(@PathVariable(required = true) Long id, @RequestBody CategoryDto categoryDto) {
		Optional<Category> optCategory = categoryRepository.findById(id);
		
		if(optCategory.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		Category categoryToUpdate = new Category();
		categoryToUpdate.setName(categoryDto.getName());
		categoryToUpdate.setDisplayOrder(categoryDto.getDisplayOrder());

		
		return categoryRepository.save(categoryToUpdate);	
	}

	//Delete
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(required = true) Long id) {
		Boolean exist = categoryRepository.existsById(id);
		
		if(!exist) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		categoryRepository.deleteById(id);
	}
}
