package com.wcs.workshoprelations.dto;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.wcs.workshoprelations.entity.Category;

public class ProductDto {
	@NotBlank
	@Size(min=2, max=255)
	private String name;

	@NotBlank
	@Size(min=2, max=255)
	private String description;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false) //valeur min = 0.1 (le 0.0 est exclu)
	@Digits (integer=3, fraction=2) //ex : on peut avoir jusqu'a 999(int),99(frac)€
	private Float price;

	@Min(value=0)
	@NotNull
	private Integer stock;
	
	@Size(min=1)
	private List<Long> categoryIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}


	
	
}
