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

import com.wcs.workshoprelations.dto.ProductDto;
import com.wcs.workshoprelations.entity.Category;
import com.wcs.workshoprelations.entity.Product;
import com.wcs.workshoprelations.repository.CategoryRepository;
import com.wcs.workshoprelations.repository.ProductRepository;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	//Create
		@PostMapping
		public Product create(@Valid @RequestBody ProductDto productDto) {
			Product product = new Product();
			product.setName(productDto.getName());
			product.setDescription(productDto.getDescription());
			product.setPrice(productDto.getPrice());
			product.setStock(productDto.getStock());
			
			List<Category> categories = new ArrayList<>();
		
			//On va chercher la categorie qui correspond à l'id category qu'on m'envoie. On le met dans notre nouveau tableau
			for (Long categoryId : productDto.getCategoryIds()) {
				Optional<Category> optCategory = categoryRepository.findById(categoryId);
				if(optCategory.isPresent()) {
					categories.add(optCategory.get());
				}
			}
			
			product.setCategories(categories);

			return productRepository.save(product);
		}
		
		//Get all
		@GetMapping
		public List<Product> getAll(){
			return productRepository.findAll();
		}
		
		//Get one
		@GetMapping("/{id}")
		public Product getById(@PathVariable(required = true) Long id) {
			Optional<Product> optProduct = productRepository.findById(id);
			
			if(optProduct.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
			return optProduct.get();
		}
		
		//Update
		@PutMapping("/{id}")
		public Product update(@PathVariable(required = true) Long id, @RequestBody ProductDto productDto) {
			Optional<Product> optProduct = productRepository.findById(id);
			
			if(optProduct.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
			Product productToUpdate = new Product();
			productToUpdate.setName(productDto.getName());
			productToUpdate.setDescription(productDto.getDescription());
			productToUpdate.setPrice(productDto.getPrice());
			productToUpdate.setStock(productDto.getStock());
			
			return productRepository.save(productToUpdate);	
		}

		//Delete
		@DeleteMapping("/{id}")
		public void deleteById(@PathVariable(required = true) Long id) {
			Boolean exist = productRepository.existsById(id);
			
			if(!exist) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
			productRepository.deleteById(id);
		}
}
