package com.wcs.workshoprelations.dto;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDto {

	@NotBlank
	@NotNull
	@Size(min=3, max=100)
	private String name;

	@NotNull
	@Min(value=0)
	@Column(name="display_order")
	private Integer displayOrder;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	
}
